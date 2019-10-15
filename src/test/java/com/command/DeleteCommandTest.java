package com.command;

import com.comand.OperationsManager;
import com.config.Configuration;
import com.link.Link;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteCommandTest {
    @Test
    public void shouldProcessDeleteCommand() {
        Configuration configuration = mock(Configuration.class);
        OperationsManager operationsManager = mock(OperationsManager.class);

        DeleteCommand deleteCommand = new DeleteCommand(configuration);

        String key = "key";
        Link link = mock(Link.class);
        String expected = "delete successful";
        when(configuration.getOperationManager()).thenReturn(operationsManager);
        when(operationsManager.deleteById(key)).thenReturn(expected);

        String result = deleteCommand.execute(key);
        assertEquals(expected, result);
    }
}
