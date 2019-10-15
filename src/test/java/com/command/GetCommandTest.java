package com.command;

import com.comand.OperationsManager;
import com.config.Configuration;
import com.link.Link;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetCommandTest {
    @Test
    public void shouldProcessGetCommand() {
        Configuration configuration = mock(Configuration.class);
        OperationsManager operationsManager = mock(OperationsManager.class);

        GetCommand getCommand = new GetCommand(configuration);

        String key = "key";
        Link link = mock(Link.class);

        when(configuration.getOperationManager()).thenReturn(operationsManager);
        when(operationsManager.getUrlById(key)).thenReturn(link);

        String result = getCommand.execute(key);
        assertEquals(link.toString(), result);
    }
}
