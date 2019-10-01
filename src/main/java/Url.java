import org.apache.commons.validator.UrlValidator;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;


public class Url implements Serializable {
    private String schema;
    private String authority;
    private String path;
    private String query;
    private String allUrl;


    public Url(String schema, String authority, String path, String query, String allUrl) {
        this.schema = schema;
        this.authority = authority;
        this.path = path;
        this.query = query;
        this.allUrl = allUrl;
    }

    public static Url createUrl(String urlToString) throws MalformedURLException {
        return new Url("", "", " ", "", "");
    }

    @Override
    public String toString() {
        return "Url{" +
                "allUrl='" + allUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return Objects.equals(schema, url.schema) &&
                Objects.equals(authority, url.authority) &&
                Objects.equals(path, url.path) &&
                Objects.equals(query, url.query) &&
                Objects.equals(allUrl, url.allUrl);
    }
}
