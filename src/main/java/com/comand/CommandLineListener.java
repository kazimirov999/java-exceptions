package com.comand;

import com.config.ConfigurationManager;


import java.util.Scanner;

public class CommandLineListener {

    private final CommandExecutor commandExecutor;
    private final InputParser inputParser;
    private final ConfigurationManager configurationManager;
    private final Scanner scanner = new Scanner(System.in);

    public CommandLineListener(CommandExecutor commandExecutor, InputParser inputParser, ConfigurationManager configurationManager) {
        this.commandExecutor = commandExecutor;
        this.inputParser = inputParser;
        this.configurationManager = configurationManager;
    }

    public void start() {
        while (continueWork()) {
            String input = scanner.nextLine();
            inputParser.parse(input, configurationManager);

            System.out.println(commandExecutor.execute(inputParser.getCommand(), inputParser.getArgument()));
        }
    }

    private boolean continueWork() {
        boolean s = scanner.hasNextLine();
        return configurationManager.isWork() && scanner.hasNextLine();
    }
}
