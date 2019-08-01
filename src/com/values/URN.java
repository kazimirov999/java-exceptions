package com.values;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String urnRegex = "^urn:([a-z0-9][a-z0-9-]{0,31}):(([a-z0-9()+,\\-.:=@;$_!*']|%[0-9a-f]{2})+$)";

        Pattern pattern = Pattern.compile(urnRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(urn);

        if(matcher.matches()) {

            return new URN(matcher.group(1), matcher.group(2), matcher.group());

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
