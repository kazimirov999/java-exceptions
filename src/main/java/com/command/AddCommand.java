package com.command;

import com.comand.OperationsManager;
import com.config.Configuration;
import com.link.Link;

public class AddCommand implements Command {

    private final Configuration configuration;

    public AddCommand(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public String execute(String argument) {
        OperationsManager operationsManager = configuration.getOperationManager();

        Link link = configuration.linkFromString(argument);
        String key = configuration.getRandomKey();

        return operationsManager.add(link, key);
    }
}
