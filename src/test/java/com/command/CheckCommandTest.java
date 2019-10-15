package com.command;

import com.comand.OperationsManager;
import com.config.Configuration;
import com.link.Link;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CheckCommandTest {
    @Test
    public void shouldProcessCheckCommand() {
        Configuration configuration = mock(Configuration.class);
        OperationsManager operationsManager = mock(OperationsManager.class);

        CheckCommand checkCommand = new CheckCommand(configuration);

        String key = "key";
        Link link = mock(Link.class);
        String argument = "https://google.com";

        when(configuration.getOperationManager()).thenReturn(operationsManager);
        when(configuration.linkFromString(argument)).thenReturn(link);

        when(operationsManager.check(link)).thenReturn(key);

        String result = checkCommand.execute(argument);
        verify(operationsManager).check(link);

        assertEquals(key, result);
    }
}
