import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OperationManagerTest {
    Map <?,?> map;
    OperationManager operationManager;

    @BeforeEach
        public void setUp() {
        map = new HashMap<>();
        operationManager = new OperationManager <>(10, map);
    }

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")

    void shouldAddLinkAndReturnKey(String config, Link link, Class<?> key)  {
        Configuration.setType(config);
        Object expected =  operationManager.add(link);
        assertTrue(key.isInstance(expected));
    }

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")

    void shouldNotAddLinkWhenOutOfLength(String config, Link link, Class<?> key)  {
        operationManager= new OperationManager(1, map);
        Configuration.setType(config);
        operationManager.add(link);
        assertThrows(OutOfLengthException.class, ()->operationManager.add(link));
    }


    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")

    void shouldReturnKeyByLink(String config, Link link)  {
       Configuration.setType(config);
       Object expected = operationManager.add(link);

       assertEquals(operationManager.check(link), expected);
    }
    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void shouldReturnExceptionWhenNotFoundLink(String config, Link link) {
        Configuration.setType(config);

        assertThrows(ValueNotFoundExcetpion.class, ()->operationManager.check(link));
    }

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")

    void shouldReturnLinkByKey(String config, Link link, Class<?> key)  {
        Configuration.setType(config);
        Object expected = operationManager.add(link);

        assertEquals(operationManager.get(expected), link);
    }
    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void shouldReturnExceptionWhenNotFoundKey(String config, Link link, Class<?> key) {
        Configuration.setType(config);

        assertThrows(ValueNotFoundExcetpion.class, ()->operationManager.get(key));
    }
    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")

    void shouldDeleteLinkByKey(String config, Link link, Class<?> key)  {
        Configuration.setType(config);
        Object expected = operationManager.add(link);

        assertEquals(operationManager.delete(expected), link);
    }

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void shouldReturnExceptionWhenNotFoutdKey(String config, Link link, Class<?> key) {
        Configuration.setType(config);

        assertThrows(ValueNotFoundExcetpion.class, ()->operationManager.delete(key));
    }

    private static List<Arguments> provideStringsForIsBlank() {
        return Stream.of(
               Arguments.of("urn", mock(Urn.class), Long.class),
                Arguments.of("url", mock(Url.class), UUID.class))
                .collect(Collectors.toList());
    }
}