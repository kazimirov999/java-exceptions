package com.exception;

public class CommandNotFoundException extends OperationException {
    public CommandNotFoundException(String message){
        super(message);
    }
}
