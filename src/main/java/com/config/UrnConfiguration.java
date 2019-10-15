package com.config;

import com.comand.OperationsManager;
import com.link.URN;
import org.apache.commons.validator.routines.RegexValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class UrnConfiguration implements Configuration {
    private String urnsPath;
    private OperationsManager operationsManager;

    public UrnConfiguration(String path) {
        this.urnsPath = path;
        this.operationsManager = restore();
    }

    public UrnConfiguration(OperationsManager operationsManager, String path) {
        this.urnsPath = path;
        this.operationsManager = operationsManager;
    }

    @Override
    public String getPath() {
        return urnsPath;
    }

    @Override
    public OperationsManager getOperationManager() {
        return operationsManager;

    }

    @Override
    public URN linkFromString(String urn) {
        RegexValidator regexValidator = new RegexValidator("^urn:([a-z0-9][a-z0-9-]{0,31}):(([a-z0-9()+,\\-.:=@;$_!*']|%[0-9a-f]{2})+$)");
        if (regexValidator.isValid(urn)) {
            String[] urlArgs = regexValidator.match(urn);
            return new URN(urlArgs[0], urlArgs[1], urn);
        } else {
            throw new IllegalArgumentException("Argument is not urn");
        }
    }

    @Override
    public String getRandomKey() {
        return Long.valueOf(new Random().nextLong()).toString();
    }
}
