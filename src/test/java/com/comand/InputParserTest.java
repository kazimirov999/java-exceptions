package com.comand;

import com.command.AddCommand;
import com.command.CleanCommand;
import com.config.ConfigurationManager;
import com.exception.CommandNotFoundException;
import com.exception.ValueNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InputParserTest {
    private InputParser inputParser;
    private ConfigurationManager configurationManager;

    @BeforeEach
    public void init() {
        inputParser = new InputParser();
        configurationManager = mock(ConfigurationManager.class);
    }

    @Test
    public void shouldParseCommandWithParameter() {
        String input = "add https://google.com";
        inputParser.parse(input, configurationManager);

        assertTrue(inputParser.getCommand() instanceof AddCommand);
        assertEquals("https://google.com", inputParser.getArgument());
    }

    @Test
    public void shouldDoesNotParseWhenCommandDoesNotExist() {
        String input = "generate url";

        assertThrows(CommandNotFoundException.class,
                () -> inputParser.parse(input, configurationManager),
                "Command not found.");
    }

    @Test
    public void shouldDoesNotParseWhenCommandHasArgumentButInputWithoutArgument() {
        String input = "add";

        assertThrows(ValueNotFoundException.class,
                () -> inputParser.parse(input, configurationManager),
                "Enter argument");
    }

    @Test
    public void shouldParseWhenCommandAddInputWithoutArgument() {
        String input = "clean";
        inputParser.parse(input, configurationManager);

        assertTrue(inputParser.getCommand() instanceof CleanCommand);
        assertEquals("", inputParser.getArgument());
    }
}
