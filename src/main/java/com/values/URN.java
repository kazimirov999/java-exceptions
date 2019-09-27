package com.values;

import org.apache.commons.validator.routines.RegexValidator;

import java.io.Serializable;

public class URN  implements Serializable {

    private String nid;
    private String nss;
    private String allName;

    public URN(String nid, String nss, String allName) {
        this.nid = nid;
        this.nss = nss;
        this.allName = allName;
    }

    public static URN fromString(String urn){

        RegexValidator regexValidator
                = new RegexValidator("^urn:([a-z0-9][a-z0-9-]{0,31}):(([a-z0-9()+,\\-.:=@;$_!*']|%[0-9a-f]{2})+$)");

        if(regexValidator.isValid(urn)) {
            String[] urnArgs = regexValidator.match(urn);

            return new URN(urnArgs[0], urnArgs[1], urn);
        }
        else{

            throw new IllegalArgumentException("Argument is not urn");

        }
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
