package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;


public enum Configurator {
    URL {
        {
            operationManager = getOperation(URL_PATH);
        }
        @Override
        protected OperationManager<UUID, Url> createOM() {
            return new OperationManager<>(10,new ArrayList<>(),new ArrayList<>(),new HashMap<>());
        }

        @Override
        public<T> T returnArg(Operation operation){

            if(operation.getTypeArgs() != 1) {
                return (T) UUID.fromString(new Scanner(System.in).nextLine());
            }else {
                return (T) Url.fromString(new Scanner(System.in).nextLine());
            }
        }
        @Override
        public void saveConfigurator(OperationManager operationManager) throws IOException {
            new SerializingObject().writeToFile(operationManager, URL_PATH);
        }
    },
    URN {
        {
            operationManager = getOperation(URN_PATH);
        }
        @Override
        protected OperationManager<Long, Urn> createOM() {
            return new OperationManager<>(10,new ArrayList<>(),new ArrayList<>(),new HashMap<>());
        }
        @Override
        public<T> T returnArg(Operation operation){

            if(operation.getTypeArgs() != 1) {
                return (T) (Long)Long.parseLong(new Scanner(System.in).nextLine());
            }else {
                return (T) Urn.fromString(new Scanner(System.in).nextLine());
            }
        }

        @Override
        public void saveConfigurator(OperationManager operationManager) throws IOException {
            new SerializingObject().writeToFile(operationManager, URN_PATH);
        }
    };

    protected OperationManager operationManager;

    protected String URL_PATH = "D:\\java\\urlList.txt";
    protected String URN_PATH = "D:\\java\\urnList.txt";


    public OperationManager getOperationManager(){
        return this.operationManager;
    }

    protected OperationManager getOperation(String path)  {
        if(new File(path).length() == 0){
            return this.createOM();
        }else{
            try {
                return new SerializingObject().readFromFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected abstract OperationManager<?,?> createOM();
    public abstract <T> T returnArg(Operation operation);
    public abstract void saveConfigurator(OperationManager operationManager) throws IOException;
}
