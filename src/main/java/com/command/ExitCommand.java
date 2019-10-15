package com.command;

import com.config.Configuration;
import com.config.ConfigurationManager;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExitCommand implements Command {
    private final ConfigurationManager configurationManager;

    public ExitCommand(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }
    @Override
    public String execute(String argument) {
        configurationManager.setWork(false);
        configurationManager.getConfiguration().save();
        return "All changes is save";
    }
}
