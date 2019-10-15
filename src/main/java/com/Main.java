package com;

import com.comand.CommandExecutor;
import com.comand.CommandLineListener;
import com.comand.InputParser;
import com.config.ConfigurationManager;
import com.config.Configurations;

public class Main {

    public static void main(String[] args) {

        ConfigurationManager configurationManager = new ConfigurationManager(Configurations.URL.getConfiguration());
        CommandExecutor commandExecutor = new CommandExecutor();
        InputParser inputParser = new InputParser();
        CommandLineListener commandLineListener = new CommandLineListener(commandExecutor, inputParser, configurationManager);

        commandLineListener.start();
    }

}
