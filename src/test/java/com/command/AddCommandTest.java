package com.command;

import com.comand.OperationsManager;
import com.config.Configuration;
import com.link.Link;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AddCommandTest {
    @Test
    public void shouldProcessAddCommand() {
        Configuration configuration = mock(Configuration.class);
        OperationsManager operationsManager = mock(OperationsManager.class);

        AddCommand addCommand = new AddCommand(configuration);

        String key = "key";
        Link link = mock(Link.class);
        String argument = "https://google.com";

        when(configuration.getOperationManager()).thenReturn(operationsManager);
        when(configuration.getRandomKey()).thenReturn(key);
        when(configuration.linkFromString(argument)).thenReturn(link);

        addCommand.execute(argument);
        verify(operationsManager).add(link, key);
    }
}
