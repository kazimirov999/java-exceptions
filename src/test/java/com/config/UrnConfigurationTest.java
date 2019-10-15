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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UrnConfigurationTest {
    private UrnConfiguration configuration;

    @BeforeAll
    public void init() throws IOException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("D:\\output\\urns.txt")));
        objectOutputStream.writeObject(new OperationsManager(10, new HashMap<>(), new ArrayList<>(), new ArrayList<>()));

    }

    @Test
    public void shouldRestoreOperationManager() {
        configuration = new UrnConfiguration("D:\\output\\urns.txt");

        assertNotNull(configuration.getOperationManager());
    }

    @Test
    public void shouldRestoreNotExistingFile() {
        assertThrows(ValueNotFoundException.class, () -> new UrnConfiguration("D:\\output\\"), "Invalid path");
    }

    @Test
    public void shouldGetRandomKey() {
        OperationsManager operationsManager = mock(OperationsManager.class);
        configuration = new UrnConfiguration(operationsManager, "D:\\output\\urns.txt");

        assertDoesNotThrow(() -> Long.valueOf(configuration.getRandomKey()));
    }

    @Test
    public void shouldGetLinkFromString() {
        OperationsManager operationsManager = mock(OperationsManager.class);
        configuration = new UrnConfiguration(operationsManager, "D:\\output\\urns.txt");

        String urn = "urn:ibsn:124312";

        assertNotNull(configuration.linkFromString(urn));
    }

    @Test
    public void shouldGetLinkFromNotValidString() {
        OperationsManager operationsManager = mock(OperationsManager.class);
        configuration = new UrnConfiguration(operationsManager, "D:\\output\\urns.txt");

        String urn = "urn:sdg";

        assertThrows(IllegalArgumentException.class, () -> configuration.linkFromString(urn), "Argument is not url");
    }

    @Test
    public void shouldSaveConfiguration() {
        OperationsManager operationsManager = new OperationsManager(10, new HashMap<>(), new ArrayList<>(), new ArrayList<>());
        configuration = new UrnConfiguration(operationsManager, "D:\\output\\out.txt");

        assertDoesNotThrow(configuration::save);
    }

    @Test
    public void shouldDoesNotSaveWhenFileIsNotExisting() {
        OperationsManager operationsManager = new OperationsManager(10, new HashMap<>(), new ArrayList<>(), new ArrayList<>());
        configuration = new UrnConfiguration(operationsManager, "D:\\output\\");

        assertThrows(ValueNotFoundException.class, configuration::save, "Invalid path");
    }
}
