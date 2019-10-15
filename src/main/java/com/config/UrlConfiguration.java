package com.config;

import com.comand.OperationsManager;
import com.link.URL;
import org.apache.commons.validator.routines.RegexValidator;

import java.util.UUID;

public class UrlConfiguration implements Configuration {
    private String urlsPath;
    private OperationsManager operationsManager;

    public UrlConfiguration(String path) {
        this.urlsPath = path;
        this.operationsManager = restore();
    }

    public UrlConfiguration(OperationsManager operationsManager, String path) {
        this.urlsPath = path;
        this.operationsManager = operationsManager;
    }

    @Override
    public URL linkFromString(String url) {
        RegexValidator regexValidator = new RegexValidator("^(([^:/?#]+):)(//([^/?#]*))([^?#]*)(\\?([^#]*))?(#(.*))?");
        if (regexValidator.isValid(url)) {
            String[] urlArgs = regexValidator.match(url);
            return new URL(urlArgs[1], urlArgs[2], urlArgs[3], urlArgs[4], urlArgs[8], url);
        } else {
            throw new IllegalArgumentException("Argument is not url");
        }
    }

    @Override
    public String getRandomKey() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getPath() {
        return urlsPath;
    }

    @Override
    public OperationsManager getOperationManager() {
        return operationsManager;

    }
}
