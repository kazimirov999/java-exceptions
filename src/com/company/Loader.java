package com.company;

import exceptions.CommandNotFoundException;

import java.io.IOException;
import java.util.Scanner;


public class Loader {

    private OperationManager<?,? super Value> operationManager;
    private Configurator configurator;
    private final static String URL_PATH = "D:\\java\\urlList.txt";
    private final static String URN_PATH = "D:\\java\\urnList.txt";

    public Loader(Configurator configurator) throws IOException, ClassNotFoundException {
        this.configurator = configurator;
        restoreStateOperation();
    }

    public void restoreStateOperation(){
        operationManager = configurator.getOperationManager();
    }

    public void exit() throws IOException {
        configurator.saveConfigurator(operationManager);
        System.out.println("Changes was stored");
        System.exit(0);
    }

    public Object launchOperation(String v) throws IOException {
        Object result;
        try {
            Scanner scanner = new Scanner(System.in);
            v = v.trim();
            Operation operation = Operation.valueOf(v);
            switch (operation) {
                case ADD: {
                    result = operationManager.add(configurator.returnArg(operation));
                    break;
                }

                case GET: {
                    result = operationManager.get(configurator.returnArg(operation));
                    break;
                }

                case CHECK: {
                    result = operationManager.check(configurator.returnArg(operation));
                    break;
                }
                case DELETE: {
                    result = operationManager.delete(configurator.returnArg(operation));
                    break;
                }
                case CLEAN_ALL: {
                    result = operationManager.cleanAll();
                    break;
                }

                case EXIT: {
                    exit();
                }

                default: {
                    result = null;
                }
            }
        } catch (java.lang.IllegalArgumentException e) {
            throw new CommandNotFoundException("Not found a command.", e);
        }
        return result;
    }

}
