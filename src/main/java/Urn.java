import org.apache.commons.validator.routines.RegexValidator;

import java.io.Serializable;
import java.util.Objects;

public class Urn implements Serializable {
    private String authority;
    private String path;


    public Urn(String authority, String path) {
        this.authority = authority;
        this.path = path;

    }

    public static Urn checkUrn(String string) {
        RegexValidator regexValidator = new RegexValidator("^urn:([a-z0-9][a-z0-9-]{0,31}):(([a-z0-9()+,\\-.:=@;$_!*']|%[0-9a-f]{2})+$)");
        Urn urn = null;
        if (!regexValidator.isValid(string)) {
            System.out.println(" It is not URN!");
        }
        else {
            String [] urnArgs = regexValidator.match(string);
            urn = new Urn(urnArgs[0],urnArgs[1]);
            System.out.println("It is URN " + urn);
        }
        return urn;
    }

    @Override
    public String toString() {
        return "Urn{" +
                "authority='" + authority + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Urn urn1 = (Urn) o;
        return Objects.equals(authority, urn1.authority) &&
                Objects.equals(path, urn1.path);
    }
}