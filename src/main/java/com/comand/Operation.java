package com.comand;

import com.config.Configuration;
import com.config.ConfigurationManager;
import com.exception.OutOfBoundsUrlsException;
import com.utils.Keys;
import com.utils.Values;

import java.io.IOException;

public enum Operation{

    ADD{
        @Override
        public Object doOperation(String argument) throws OutOfBoundsUrlsException{
            operationsManager = configurationManager.getOperationsManager();

            return operationsManager.add(Values.fromString(argument));
        }

    }, CHECK{
        @Override
        public Object doOperation(String argument) throws OutOfBoundsUrlsException{
            operationsManager = configurationManager.getOperationsManager();
            return operationsManager.check(Values.fromString(argument));
        }

    }, GET{
        public Object doOperation(String argument) throws OutOfBoundsUrlsException{
            operationsManager = configurationManager.getOperationsManager();
            return operationsManager.getUrlById(Keys.fromString(argument));
        }

    }, DELETE{
        @Override
        public Object doOperation(String argument) throws OutOfBoundsUrlsException{
            operationsManager = configurationManager.getOperationsManager();
            return operationsManager.deleteById(Keys.fromString(argument));
        }

    }, CLEAN{
        @Override
        public Object doOperation(String argument) throws OutOfBoundsUrlsException{
            operationsManager = configurationManager.getOperationsManager();
            return  operationsManager.cleanAll();

        }
    }, CONFIG{
        @Override
        public Object doOperation(String argument){
            configurationManager.changeConfiguration(Configuration.valueOf(argument.toUpperCase()));
            return  "com.config.Configuration changed to " + configurationManager.getConfiguration();
        }
    }, EXIT{
        @Override
        public Object doOperation(String argument){
            configurationManager.saveState();
            System.exit(0);
            return null;
        }
    };



    private static ConfigurationManager configurationManager = new ConfigurationManager();
    protected OperationsManager<?, ?> operationsManager;

    public abstract Object doOperation(String argument) throws IOException, ClassNotFoundException;

}
