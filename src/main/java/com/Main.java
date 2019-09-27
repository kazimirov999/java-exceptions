package com;

import com.comand.Command;
import com.comand.CommandLineListener;

public class Main {

    public static void main(String[] args) {
        Command command = new Command();
        CommandLineListener commandListener = new CommandLineListener(command);
        commandListener.start();

    }
}
