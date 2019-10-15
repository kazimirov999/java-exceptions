package com.config;

public class ConfigurationManager {
    private Configuration configuration;
    private boolean isWork = true;

    public ConfigurationManager(Configuration configuration) {
        this.configuration = configuration;
    }

    public String changeConfiguration(Configuration newConfiguration) {
        configuration.save();
        configuration = newConfiguration;
        return "Configuration changed";
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }
}
