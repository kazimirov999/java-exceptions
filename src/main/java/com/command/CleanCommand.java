package com.command;

import com.comand.OperationsManager;
import com.config.Configuration;

public class CleanCommand implements Command {
    private final Configuration configuration;

    public CleanCommand(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String execute(String argument) {
        OperationsManager operationsManager = configuration.getOperationManager();

        return operationsManager.cleanAll().toString();
    }
}
