package com.instagram.server.exceptions;

public class MissingFieldException extends RuntimeException{
    public MissingFieldException(String message){
        super(message);
    }
}
