import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;


class OperationTest {
    OperationManager operationManager;
    Url url;
    Urn urn;
    Configuration configuration;
    UUID uuid;
    Long key;


    @BeforeEach
    void setUp(){
        operationManager = mock(OperationManager.class);
        url = mock(Url.class);
        urn = mock(Urn.class);
        configuration = mock(Configuration.class);
        key = 1l;
        uuid = new UUID(key, key);
    }

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void shouldCallOperationAdd(String config, String link) throws MalformedURLException {
       Configuration.setType(config.toUpperCase());
       System.setIn(new ByteArrayInputStream(link.getBytes()));
       Operation operation = Operation.valueOf("add".toUpperCase());
       operation.callOperation(operationManager,configuration);

       verify(operationManager, times(1)).add(any());

   }
    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void shouldCallOperationCheck(String config, String link) throws MalformedURLException {
        Configuration.setType(config.toUpperCase());
        System.setIn(new ByteArrayInputStream(link.getBytes()));
        Operation operation = Operation.valueOf("check".toUpperCase());
        operation.callOperation(operationManager,configuration);

        verify(operationManager, times(1)).check(any());

    }

    @Test
    void shouldCallOperationGetForUrl() throws MalformedURLException {
        Configuration.setType("url".toUpperCase());
        String data = uuid.toString();
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Operation operation = Operation.valueOf("get".toUpperCase());
        operation.callOperation(operationManager,configuration);

        verify(operationManager, times(1)).get(any(UUID.class));

    }
    @Test
    void shouldCallOperationGetForUrn() throws MalformedURLException {
        Configuration.setType("urn".toUpperCase());
        String data = key.toString();
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Operation operation = Operation.valueOf("get".toUpperCase());
        operation.callOperation(operationManager,configuration);

        verify(operationManager, times(1)).get(any(Long.class));

    }

    @Test
    void shouldCallOperationDeleteForUrl() throws MalformedURLException {
        Configuration.setType("url".toUpperCase());
        String data = uuid.toString();
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Operation operation = Operation.valueOf("delete".toUpperCase());
        operation.callOperation(operationManager,configuration);

        verify(operationManager, times(1)).delete(any(UUID.class));

    }
    @Test
    void shouldCallOperationDeleteForUrn() throws MalformedURLException {
        Configuration.setType("urn".toUpperCase());
        String data = key.toString();
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Operation operation = Operation.valueOf("delete".toUpperCase());
        operation.callOperation(operationManager,configuration);

        verify(operationManager, times(1)).delete(any(Long.class));

    }
    private static List<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of("urn", "urn:snefru128:646b932fee2529db11d05425cff21978"),
                Arguments.of("url", "https://google.com"))
                .collect(Collectors.toList());
    }
}