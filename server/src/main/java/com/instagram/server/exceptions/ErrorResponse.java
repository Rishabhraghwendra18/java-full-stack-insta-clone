package com.instagram.server.exceptions;

public class ErrorResponse {
    private String message;
    private boolean isError;
    private int status;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.isError = true;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
