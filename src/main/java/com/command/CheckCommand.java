package com.command;

import com.comand.OperationsManager;
import com.config.Configuration;
import com.link.Link;

public class CheckCommand implements Command {
    private final Configuration configuration;

    public CheckCommand(Configuration configuration) {
        this.configuration = configuration;
    }

    public String execute(String argument) {
        OperationsManager operationsManager = configuration.getOperationManager();
        Link link = configuration.linkFromString(argument);

        return operationsManager.check(link);
    }
}
