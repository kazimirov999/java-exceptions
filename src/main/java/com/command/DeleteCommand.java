package com.command;

import com.comand.OperationsManager;
import com.config.Configuration;


public class DeleteCommand implements Command {

    private final Configuration configuration;

    public DeleteCommand(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String execute(String argument) {
        OperationsManager operationsManager = configuration.getOperationManager();
        return operationsManager.deleteById(argument);
    }
}
