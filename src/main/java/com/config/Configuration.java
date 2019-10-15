package com.config;

import com.comand.OperationsManager;
import com.exception.ValueNotFoundException;
import com.link.Link;

import java.io.*;

public interface Configuration {
    String getPath();

    OperationsManager getOperationManager();

    Link linkFromString(String link);

    String getRandomKey();

    default void save() {
        File file = new File(getPath());
        OperationsManager operationsManager = getOperationManager();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(operationsManager);

        } catch (IOException e) {
            throw new ValueNotFoundException("Invalid path");
        }
    }

    default OperationsManager restore() {
        File file = new File(getPath());

        try {

            ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(file));
            OperationsManager operationsManager = (OperationsManager) outputStream.readObject();

            return operationsManager;
        } catch (IOException | ClassNotFoundException e) {
            throw new ValueNotFoundException("Invalid path");
        }
    }
}
