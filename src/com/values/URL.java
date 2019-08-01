package com.values;

import com.exception.IllegalArgumentException;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

        String uriRegex = "^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?";
        Pattern pattern = Pattern.compile(uriRegex);
        Matcher matcher = pattern.matcher(url);

        if(matcher.find() && matcher.group(1) != null) {

            return new URL(matcher.group(1), matcher.group(2),
                    matcher.group(3), matcher.group(4), matcher.group(9), matcher.group());

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
