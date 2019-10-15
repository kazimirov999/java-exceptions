package com.config;

import com.comand.OperationsManager;
import com.exception.ValueNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UrlConfigurationTest {
    private UrlConfiguration configuration;

    @BeforeAll
    public void init() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("D:\\output\\urls.txt")));
        objectOutputStream.writeObject(new OperationsManager(10, new HashMap<>(), new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    public void shouldRestoreOperationManager() {
        configuration = new UrlConfiguration("D:\\output\\urls.txt");
        assertNotNull(configuration.getOperationManager());
    }

    @Test
    public void shouldRestoreFromNotExistingFile() {
        assertThrows(ValueNotFoundException.class, () -> new UrlConfiguration("D:\\output\\"), "Invalid path");
    }

    @Test
    public void shouldGetRandomKey() {
        OperationsManager operationsManager = mock(OperationsManager.class);
        configuration = new UrlConfiguration(operationsManager, "D:\\output\\urls.txt");

        assertDoesNotThrow(() -> UUID.fromString(configuration.getRandomKey()));
    }

    @Test
    public void shouldGetLinkFromString() {
        OperationsManager operationsManager = mock(OperationsManager.class);
        configuration = new UrlConfiguration(operationsManager, "D:\\output\\urls.txt");
        String url = "https://google.com";

        assertNotNull(configuration.linkFromString(url));
    }

    @Test
    public void shouldGetLinkFromNotValidString() {
        OperationsManager operationsManager = new OperationsManager(10, new HashMap<>(), new ArrayList<>(), new ArrayList<>());
        configuration = new UrlConfiguration(operationsManager, "D:\\output\\urls.txt");
        String url = "https/google.com";

        assertThrows(IllegalArgumentException.class, () -> configuration.linkFromString(url), "Argument is not url");
    }

    @Test
    public void shouldSaveConfiguration() {
        OperationsManager operationsManager = new OperationsManager(10, new HashMap<>(), new ArrayList<>(), new ArrayList<>());
        configuration = new UrlConfiguration(operationsManager, "D:\\output\\urls.txt");
        assertDoesNotThrow(configuration::save);
    }

    @Test
    public void shouldDoesNotSaveWhenFileIsNotExisting() {
        OperationsManager operationsManager = mock(OperationsManager.class);
        configuration = new UrlConfiguration(operationsManager, "D:\\output\\");

        assertThrows(ValueNotFoundException.class, configuration::save, "Invalid path");
    }
}
