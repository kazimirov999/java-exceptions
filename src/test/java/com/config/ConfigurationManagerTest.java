package com.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConfigurationManagerTest {

    @Test
    public void shouldChangeConfiguration() {
        Configuration configuration = mock(Configuration.class);
        Configuration newConfiguration = mock(Configuration.class);
        ConfigurationManager configurationManager = new ConfigurationManager(configuration);
        configurationManager.changeConfiguration(newConfiguration);
        verify(configuration).save();

        assertEquals(newConfiguration, configurationManager.getConfiguration());
    }

}
