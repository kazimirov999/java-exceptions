package com.company;

public class Urn extends Value {
    public final static String name = "urn";
    public String NID;
    public String NSS;
    Urn(String NID,String NSS){
        this.NID = NID;
        this.NSS = NSS;
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getNSS() {
        return NSS;
    }

    public void setNSS(String NSS) {
        this.NSS = NSS;
    }

    @Override
    public String toString() {
        return "Urn{" +
                "Name='" + name + '\'' +
                ", NID='" + NID + '\'' +
                ", NSS='" + NSS + '\'' +
                '}';
    }

    public static Urn fromString(String s) {
        return new Urn("","");
    }
}
