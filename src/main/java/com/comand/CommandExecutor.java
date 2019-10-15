package com.comand;

import com.command.Command;

public class CommandExecutor {

    public String execute(Command command, String argument) {
        return command.execute(argument);
    }

}
