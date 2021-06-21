package com.acul.gui;

import com.acul.math.Matrix4f;
import com.acul.math.Vector3f;
import com.acul.utils.AssetUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private final int programId;
    private Map<String, Integer> locCache = new HashMap<String, Integer>();

    private static final String shaderLocation = "/shaders/";

    public Shader(String name) {

        programId = glCreateProgram();
        String vertexShader = readShader(shaderLocation + name + ".vs");
        if (vertexShader != null) {
            int vertexShaderId = glCreateShader(GL_VERTEX_SHADER);
            glShaderSource(vertexShaderId, vertexShader);
            glCompileShader(vertexShaderId);
            if (glGetShaderi(vertexShaderId, GL_COMPILE_STATUS) != 1) {
                System.err.println("Failed to compile vertex shader!");
                System.err.println(glGetShaderInfoLog(vertexShaderId));
                return;
            }
            glAttachShader(programId, vertexShaderId);
            glDeleteShader(vertexShaderId);
            glBindAttribLocation(programId, 0, "vertices");
        }
        String fragmentShader = readShader(shaderLocation + name + ".fs");
        if (fragmentShader != null) {
            int fragmentShaderId = glCreateShader(GL_FRAGMENT_SHADER);
            glShaderSource(fragmentShaderId, fragmentShader);
            glCompileShader(fragmentShaderId);
            if (glGetShaderi(fragmentShaderId, GL_COMPILE_STATUS) != 1) {
                System.err.println("Failed to compile fragment shader!");
                System.err.println(glGetShaderInfoLog(fragmentShaderId));
                return;
            }
            glAttachShader(programId, fragmentShaderId);
            glDeleteShader(fragmentShaderId);
        }

        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) != 1) {
            return;
        }
        glValidateProgram(programId);
    }

    public void activate() {
        glUseProgram(programId);
    }

    public void deactivate() {
        glUseProgram(0);
    }

    public void setVar1i(String varName, int value) {
        glUniform1i(getVarLocation(varName), value);
    }

    public void setVar1f(String varName, float value) {
        glUniform1f(getVarLocation(varName), value);
    }

    public void setVar2f(String varName, float value1, float value2) {
        glUniform2f(getVarLocation(varName), value1, value2);
    }

    public void setVar3f(String varName, Vector3f vector) {
        glUniform3f(getVarLocation(varName), vector.getX(), vector.getY(), vector.getZ());
    }

    public void setVarMatrix4f(String varName, Matrix4f matrix) {
        glUniformMatrix4fv(getVarLocation(varName), false, matrix.toBuffer());
    }

    private int getVarLocation(String varName) {
        if (locCache.containsKey(varName))
            return locCache.get(varName);
        int loc = glGetUniformLocation(programId, varName);
        if (loc == -1)
            System.err.println("Could not find shader variable " + varName + "!");
        else
            locCache.put(varName, loc);
        return loc;
    }

    private String readShader(String filename) {
        byte[] data = AssetUtils.load(filename);
        if (data != null) {
            return new String(data, StandardCharsets.UTF_8) + '\n';
        }
        return null;
    }
}
