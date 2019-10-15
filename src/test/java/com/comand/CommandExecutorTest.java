package com.comand;

import com.command.Command;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CommandExecutorTest {

    @Test
    public void shouldExecuteCommand() {

        Command command = mock(Command.class);
        when(command.execute(any(String.class))).thenReturn(any());
        CommandExecutor commandExecutor = new CommandExecutor();

        String argument = "https://google.com";

        commandExecutor.execute(command, argument);

        verify(command, times(1)).execute(argument);
    }


}
