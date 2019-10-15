package com.comand;

import com.command.AddCommand;
import com.command.Command;
import com.config.ConfigurationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommandLineListenerTest {
    private Path inputPath = Paths.get("D:\\IntellijProjects\\java-exceptions\\src\\test\\resources\\inputCommand.txt");
    private CommandExecutor commandExecutor;
    private  Command command;
    private InputParser inputParser;
    private ConfigurationManager configurationManager;
    private CommandLineListener commandLineListener;

    @BeforeEach
    public void init() throws FileNotFoundException {

        File file = inputPath.toFile();
        InputStream inputStream = new FileInputStream(file);
        System.setIn(inputStream);

        command = mock(Command.class);
        inputParser = mock(InputParser.class);
        configurationManager = mock(ConfigurationManager.class);
        commandExecutor = mock(CommandExecutor.class);
    }

    @Test
    public void shouldProcessOneCommand() throws IOException {

        String input = "add https://google.com";
        String argument = "https://google.com";
        String result = "Command successfully processed";

        Files.writeString(inputPath, input, StandardOpenOption.TRUNCATE_EXISTING);

        commandLineListener = new CommandLineListener(commandExecutor, inputParser, configurationManager);

        when(configurationManager.isWork()).thenReturn(true);
        when(commandExecutor.execute(any(AddCommand.class), any(String.class))).thenReturn(result);
        when(inputParser.getArgument()).thenReturn(argument);
        when(inputParser.getCommand()).thenReturn(command);

        commandLineListener.start();

        verify(commandExecutor).execute(command, argument);
    }

    @Test
    public void shouldProcessListCommand() throws IOException {

        String input = "add https://googlel.com\n" +
                "add  https://gmail.com\n" +
                "check  https://gmail.com\n" +
                "add  https://github.com\n" +
                "clean\n" ;

        int amountCommand = input.split("\n").length;
        Files.writeString(inputPath, input, StandardOpenOption.TRUNCATE_EXISTING);

        commandLineListener = new CommandLineListener(commandExecutor, inputParser, configurationManager);

        String result = "Command successfully processed";
        String argument = "https://google.com";

        when(commandExecutor.execute(any(Command.class), any(String.class))).thenReturn(result);
        when(configurationManager.isWork()).thenReturn(true);
        when(inputParser.getArgument()).thenReturn(argument);
        when(inputParser.getCommand()).thenReturn(command);

        commandLineListener.start();

        verify(commandExecutor, times(amountCommand)).execute(any(Command.class), any(String.class));
    }

    @Test
    public void shouldFinishWorkWhenConfigurationManagerDoesNotWork(){
        commandLineListener = new CommandLineListener(commandExecutor, inputParser, configurationManager);

        when(configurationManager.isWork()).thenReturn(false);

        commandLineListener.start();
        verify(commandExecutor, never()).execute(any(), any());
    }
}