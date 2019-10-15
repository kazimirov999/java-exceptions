package com.command;

import com.comand.OperationsManager;
import com.config.Configuration;
import com.link.Link;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CleanCommandTest {
    @Test
    public void shouldProcessCleanCommand() {
        Configuration configuration = mock(Configuration.class);
        OperationsManager operationsManager = mock(OperationsManager.class);

        CleanCommand cleanCommand = new CleanCommand(configuration);


        Map<String, Link> expected = Map.of();
        when(configuration.getOperationManager()).thenReturn(operationsManager);
        when(operationsManager.cleanAll()).thenReturn(expected);

        String result = cleanCommand.execute("");
        assertEquals(expected.toString(), result);
    }
}
