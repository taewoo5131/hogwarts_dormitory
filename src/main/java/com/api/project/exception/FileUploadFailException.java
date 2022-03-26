package com.api.project.exception;

public class FileUploadFailException extends Exception {
    public FileUploadFailException() {
        super();
    }

    public FileUploadFailException(String message) {
        super(message);
    }
}
