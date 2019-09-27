package com.utils;

import com.config.Configuration;
import com.config.ConfigurationManager;
import com.values.URL;
import com.values.URN;

public class Values {
    private static ConfigurationManager configurationManager = new ConfigurationManager();

    public static<T> T fromString(String argument){
        Configuration configuration = configurationManager.getConfiguration();

        if(configuration.equals(Configuration.URL)){
            return (T) URL.fromString(argument);
        }else{
            return (T) URN.fromString(argument);
        }
    }
}
