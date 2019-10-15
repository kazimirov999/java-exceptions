package com.command;

import com.config.Configuration;
import com.config.ConfigurationManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ExitCommandTest {
    @Test
    public void shouldProcessExitCommand() {
        ConfigurationManager configurationManager = mock(ConfigurationManager.class);
        Configuration configuration = mock(Configuration.class);

        ExitCommand exitCommand = new ExitCommand(configurationManager);

        when(configurationManager.getConfiguration()).thenReturn(configuration);

        String result = exitCommand.execute("");

        verify(configuration).save();
        verify(configurationManager).setWork(false);
        assertEquals("All changes is save", result);
    }
}
