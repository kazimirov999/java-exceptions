package com.comand;

import com.exception.OutOfBoundsUrlsException;
import com.exception.ValueNotFoundException;
import com.link.Link;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OperationManagerTest {
    private OperationsManager operationsManager;
    private Link link;
    private String key;

    @BeforeEach
    public void init(){
        operationsManager = new OperationsManager(10, new HashMap<>(), new ArrayList<>(), new ArrayList<>());
        link = mock(Link.class);
        key = UUID.randomUUID().toString();
    }

    @Test
    public void shouldAddLink(){
        String result = operationsManager.add(link, key);
        assertEquals(key, result);
    }

    @Test
    public void shouldOutOfBoundMapOfLinks(){
        operationsManager = new OperationsManager(1, new HashMap<>(), new ArrayList<>(), new ArrayList<>());

        operationsManager.add(link, key);

        assertThrows(OutOfBoundsUrlsException.class, ()->operationsManager.add(link, key), "Max size for map of com.links is 1");
    }

    @Test
    public void shouldGetLinkByKey(){
        operationsManager.add(link, key);
        Link result = operationsManager.getUrlById(key);
        assertEquals(link, result);
    }
    @Test
    public void shouldNotFoundLinkWhenKeyIsFake(){
        String fakeKey = UUID.randomUUID().toString();

        operationsManager.add(link, key);

        assertThrows(ValueNotFoundException.class, ()->operationsManager.getUrlById(fakeKey), "Key is not found in map");
    }

    @Test
    public void shouldGetKeyByLink(){
        operationsManager.add(link, key);
        String result = operationsManager.check(link);
        assertEquals(key, result);
    }
    @Test
    public void shouldNotFoundKeyWhenLinkIsFake(){
        Link fakeLink = mock(Link.class);

        operationsManager.add(link, key);

        assertThrows(ValueNotFoundException.class, ()->operationsManager.check(fakeLink), "Value is not found in map");
    }

    @Test
    public void shouldDeleteLink(){
        operationsManager.add(link, key);
        String result = operationsManager.deleteById(key);
        assertTrue(result.contains(key));
    }
    @Test
    public void shouldDoesNotDeleteLinkWhenKeyIsFake(){
        String fakeKey = UUID.randomUUID().toString();

        operationsManager.add(link, key);

        assertThrows(ValueNotFoundException.class, ()->operationsManager.deleteById(fakeKey), "Key is not found in map");
    }

    @Test
    public void shouldCleanAllLinks(){
        Map<String, Link> map = new HashMap<>();
        map.put(key, link);
        operationsManager.add(link, key);

        Map<String, Link> result = operationsManager.cleanAll();

        assertEquals(map, result);
    }
    @Test
    public void shouldDoesNotThrowExceptionWhenMapOfLinksIsEmpty(){
        Map<String, Link> result = operationsManager.cleanAll();
        assertTrue(result.isEmpty());
    }
}
