package com.command;

import com.config.ConfigurationManager;
import com.config.Configurations;

public class ConfigurationCommand implements Command {

    private final ConfigurationManager configurationManager;

    public ConfigurationCommand(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    @Override
    public String execute(String argument) {
        Configurations configuration = Configurations.valueOf(argument.toUpperCase());
        return configurationManager.changeConfiguration(configuration.getConfiguration());
    }
}
