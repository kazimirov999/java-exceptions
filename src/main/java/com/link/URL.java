package com.link;


import java.io.Serializable;

public class URL implements Link, Serializable {

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
