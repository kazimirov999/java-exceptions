package com.comand;

import com.command.Command;
import com.config.ConfigurationManager;
import com.exception.CommandNotFoundException;
import com.exception.ValueNotFoundException;

import java.util.Optional;

public class InputParser {

    private static final String DEFAULT_ARGUMENT = "";
    private String argument;
    private Command command;


    public void parse(String input, ConfigurationManager configurationManager) {

        Operation operation = parseOperation(input);
        command = operation.getCommand(configurationManager);

        parseArgument(input, operation);
    }

    private void parseArgument(String input, Operation operation) {
        if (operation.hasArgument()) {
            argument = Optional.of(input)
                    .filter(s -> s.split(" ").length > 1)
                    .map(s -> s.split(" ")[1])
                    .orElseThrow(() -> new ValueNotFoundException("Enter argument"));
        } else {
            argument = DEFAULT_ARGUMENT;
        }
    }

    private Operation parseOperation(String input) {
        try {
            return Operation.valueOf(input.split(" ")[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CommandNotFoundException("Command not found.");
        }
    }

    public Command getCommand() {
        return command;
    }

    public String getArgument() {
        return argument;
    }
}
