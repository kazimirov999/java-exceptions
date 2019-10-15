package com.link;


import java.io.Serializable;

public class URN  implements Link, Serializable {

    private String nid;
    private String nss;
    private String allName;

    public URN(String nid, String nss, String allName) {
        this.nid = nid;
        this.nss = nss;
        this.allName = allName;
    }


    @Override
    public String toString() {
        return allName;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof URN) {
            URN c = (URN) o;
            if (this.allName.equals(c.allName)) {
                return true;
            }
        }
        return false;
    }
}
