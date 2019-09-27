package com.comand;

import com.exception.CommandNotFoundException;
import java.io.IOException;
import java.util.stream.Stream;

public class Command {

    private String argument;
    private Operation operation;


    public Object execute(String command) throws IOException, ClassNotFoundException {
        operation = getOperation(command);
        argument = getArgument(command);

        return operation.doOperation(argument);
    }


    private Operation getOperation(String command){
        try {

            return Operation.valueOf(command.split(" ")[0].toUpperCase());

        }catch (IllegalArgumentException e){

            throw new CommandNotFoundException("com.comand.Command not found. " + e.getMessage(), e);

        }
    }

    private String getArgument(String command){
        return  Stream.of(command.split(" ")).skip(1).
                findAny().orElse("");
    }


}
