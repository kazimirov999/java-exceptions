import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class UrnTest {

    @Test
    void shouldReturnUrnByString() {
        String string = "urn:ripemd160:93f1cb4a43643136d730a3b94b0ebcec66928c02";
        Urn exepted = Urn.checkUrn(string);

        assertEquals(Urn.checkUrn(string), exepted);
    }
}