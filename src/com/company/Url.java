package com.company;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Objects;

public class Url extends Value implements Serializable {

    private static final long serialVersionUID = 42L;
    private String protocol;
    private String authoraty;
    private String path;
    private String query;
    private String ref;



    public Url(String protocol,String authoraty,String path,String query,String ref){
        this.protocol = protocol;
        this.authoraty = authoraty;
        this.path = path;
        this.query = query;
        this.ref = ref;
    }


    public static Url fromString(String s) {
        java.net.URL newUrl = null;
        try {
            newUrl = new java.net.URL(s);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Not correct URL, try again.");
        }
        return new Url(newUrl.getProtocol(),newUrl.getAuthority()
                ,newUrl.getPath(),newUrl.getQuery(),newUrl.getRef());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return Objects.equals(protocol, url.protocol) &&
                Objects.equals(authoraty, url.authoraty) &&
                Objects.equals(path, url.path) &&
                Objects.equals(query, url.query) &&
                Objects.equals(ref, url.ref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(protocol, authoraty, path, query, ref);
    }

    @Override
    public String toString() {
        return "Url{" +
                "protocol='" + protocol + '\'' +
                ", authoraty='" + authoraty + '\'' +
                ", path='" + path + '\'' +
                ", query='" + query + '\'' +
                ", ref='" + ref + '\'' +
                '}';
    }
}
