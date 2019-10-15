package com.exception;

public class ValueNotFoundException extends OperationException {
    public ValueNotFoundException(String message){
        super(message);
    }
}
