package com.command;

import com.comand.OperationsManager;
import com.config.Configuration;


public class GetCommand implements Command {
    private final Configuration configuration;

    public GetCommand(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String execute(String argument) {
        OperationsManager operationsManager = configuration.getOperationManager();
        return operationsManager.getUrlById(argument).toString();
    }
}
