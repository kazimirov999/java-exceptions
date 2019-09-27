package com.values;

import com.exception.IllegalArgumentException;
import org.apache.commons.validator.routines.RegexValidator;

import java.io.Serializable;


public class URL implements Serializable{

    private String schema;
    private String authority;
    private String path;
    private String query;
    private String fragment;
    private String allName;

    public URL(String schema, String authority, String path, String query, String fragment, String allName) {
        this.schema = schema;
        this.authority = authority;
        this.path = path;
        this.query = query;
        this.fragment = fragment;
        this.allName = allName;
    }

    public static  URL fromString(String url){

        RegexValidator regexValidator = new RegexValidator("^(([^:/?#]+):)(//([^/?#]*))([^?#]*)(\\?([^#]*))?(#(.*))?");

        if(regexValidator.isValid(url)) {

            String[] urlArgs = regexValidator.match(url);
            return new URL(urlArgs[1], urlArgs[2], urlArgs[3], urlArgs[4], urlArgs[8], url);

        }
        else{

            throw new IllegalArgumentException("Argument is not url");
        }
    }

    @Override
    public String toString() {
        return allName;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof URL) {
            URL c = (URL) o;
            if (this.allName.equals(c.allName)) {
                return true;
            }
        }
        return false;
    }
}
