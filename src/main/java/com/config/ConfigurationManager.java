package com.config;

import com.comand.OperationsManager;
import com.utils.State;

public class ConfigurationManager {
    private static Configuration configuration;
    private OperationsManager<?, ?> operationsManager;
    private State state;

    {
        state = new State();
        configuration = Configuration.URL;
        operationsManager = state.restore(configuration.getPath());
    }



    public void changeConfiguration(Configuration newConfiguration){

        state.save(operationsManager, configuration.getPath());
        configuration = newConfiguration;
        operationsManager =  state.restore(configuration.getPath());

    }

    public void saveState() {
        state.save(operationsManager, configuration.getPath());
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public OperationsManager<?, ?> getOperationsManager() {
        return operationsManager;
    }
}
