package com.comand;

import com.command.*;
import com.config.ConfigurationManager;


public enum Operation {

    ADD {
        @Override
        public Command getCommand(ConfigurationManager configurationManager) {
            return new AddCommand(configurationManager.getConfiguration());
        }

        @Override
        public boolean hasArgument() {
            return true;
        }

    }, CHECK {
        @Override
        public Command getCommand(ConfigurationManager configurationManager) {
            return new CheckCommand(configurationManager.getConfiguration());
        }

        @Override
        public boolean hasArgument() {
            return true;
        }

    }, GET {
        @Override
        public Command getCommand(ConfigurationManager configurationManager) {
            return new GetCommand(configurationManager.getConfiguration());
        }

        @Override
        public boolean hasArgument() {
            return true;
        }

    }, DELETE {
        @Override
        public Command getCommand(ConfigurationManager configurationManager) {
            return new DeleteCommand(configurationManager.getConfiguration());
        }

        @Override
        public boolean hasArgument() {
            return true;
        }

    }, CLEAN {
        @Override
        public Command getCommand(ConfigurationManager configurationManager) {
            return new CleanCommand(configurationManager.getConfiguration());
        }

        @Override
        public boolean hasArgument() {
            return false;
        }
    }, CONFIG {
        @Override
        public Command getCommand(ConfigurationManager configurationManager) {
            return new ConfigurationCommand(configurationManager);
        }

        @Override
        public boolean hasArgument() {
            return true;
        }
    }, EXIT {
        @Override
        public Command getCommand(ConfigurationManager configurationManager) {
            return new ExitCommand(configurationManager);
        }

        @Override
        public boolean hasArgument() {
            return false;
        }
    };

    public abstract Command getCommand(ConfigurationManager configurationManager);

    public abstract boolean hasArgument();
}
