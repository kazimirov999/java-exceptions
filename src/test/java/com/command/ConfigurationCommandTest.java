package com.command;

import com.config.ConfigurationManager;
import com.config.UrlConfiguration;
import com.config.UrnConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConfigurationCommandTest {
    private ConfigurationManager configurationManager;
    private ConfigurationCommand configurationCommand;

    @BeforeEach
    public void init() {
        configurationManager = mock(ConfigurationManager.class);
        configurationCommand = new ConfigurationCommand(configurationManager);
    }

    @Test
    public void shouldChangeToUrlConfiguration() {
        String result = configurationCommand.execute("url");

        verify(configurationManager).changeConfiguration(any(UrlConfiguration.class));
    }

    @Test
    public void shouldChangeToUrnConfiguration() {
        String result = configurationCommand.execute("urn");

        verify(configurationManager).changeConfiguration(any(UrnConfiguration.class));
    }
}
