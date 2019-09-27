package com.utils;

import com.config.Configuration;
import com.config.ConfigurationManager;

import java.util.Random;
import java.util.UUID;

public class Keys {
    private static ConfigurationManager configurationManager = new ConfigurationManager();
    public static <T> T generateKey(){
        Configuration configuration =  configurationManager.getConfiguration();

        if(configuration.equals(Configuration.URL)){
            return (T) UUID.randomUUID();
        }else {
            return (T) (Long)new Random().nextLong();
        }
    }
    public static <T> T fromString(String stringKey){
        Configuration configuration =  configurationManager.getConfiguration();

        if(configuration.equals(Configuration.URL)){
            return (T) UUID.fromString(stringKey);
        }else {
            return (T) Long.valueOf(stringKey);
        }
    }

}
