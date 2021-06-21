package com.acul.gui;

public class InitFailureException extends Exception {
    public InitFailureException() {
    }

    public InitFailureException(String s) {
        super(s);
    }

    public InitFailureException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InitFailureException(Throwable throwable) {
        super(throwable);
    }

    public InitFailureException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
