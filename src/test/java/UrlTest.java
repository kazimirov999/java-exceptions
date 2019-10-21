import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class UrlTest {

    @Test
    void shouldReturnUrlByString() throws MalformedURLException {
        String string = "https://www.google.com.ua";
        Url exepted = Url.createUrl(string);

        assertEquals(Url.createUrl(string), exepted);
    }
}