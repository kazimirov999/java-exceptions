import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SerializationManagerTest {
    OperationManager operationManager;
    SerializationManager serializationManager;

    @BeforeEach
    void setUp(){
        operationManager = mock(OperationManager.class);
        serializationManager = new SerializationManager(operationManager);
    }
    @Test
    void shouldWriteFile() {
        serializationManager.writeFile();

        assertDoesNotThrow(()-> serializationManager.writeFile());
    }

    @Test
    void shouldReadFile() {
         serializationManager.readFile();

         assertDoesNotThrow(()-> serializationManager.readFile());
    }
}