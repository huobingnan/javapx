package io.bryan.jvmabi.reader;

public class ReadByteCodeException extends RuntimeException {
    public ReadByteCodeException() {}

    public ReadByteCodeException(String message) { super(message);}

    public ReadByteCodeException(String message, Throwable cause) { super(message, cause); }

    public ReadByteCodeException(Throwable cause) { super(cause); }

    public ReadByteCodeException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
