package com.utils;

import com.comand.OperationsManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class State {

    public void save(OperationsManager<?, ?> value, String path) {

        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream( new FileOutputStream(path))) {
            objectOutputStream.writeObject(value);
        }catch (IOException e){
            throw new RuntimeException();
        }

    }

    public  OperationsManager<?, ?> restore(String path){

        if(new File(path).length() != 0) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {

                return (OperationsManager<?, ?>) objectInputStream.readObject();

            } catch (IOException | ClassNotFoundException e) {

                throw new RuntimeException();

            }
        }else {
            return new OperationsManager<>(10, new HashMap<>(), new ArrayList<>(), new ArrayList<>());
        }
    }

}
