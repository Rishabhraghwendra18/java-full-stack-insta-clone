package com.instagram.server.requestResponse;

public class CommonResponse {
    private String message;
    private boolean error;
    private int statusCode;

    public CommonResponse(String message, boolean error, int statusCode) {
        this.message = message;
        this.error = error;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
