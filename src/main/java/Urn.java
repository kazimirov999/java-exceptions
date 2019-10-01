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
        return new Urn("", "");
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
