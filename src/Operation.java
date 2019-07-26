import exeption.OutOfBoundsUrlsException;
import exeption.ValueNotFoundException;

import java.util.UUID;

public enum Operation {
    ADD{
        @Override
        public <T> Object doOperation(T o, OperationsManager operationsManager) throws OutOfBoundsUrlsException {
            return operationsManager.add((String) o);
        }
    }, CHECK{
        @Override
        public <T> Object doOperation(T o, OperationsManager operationsManager) throws ValueNotFoundException {
           return operationsManager.check((String) o);
        }
    }, GET{
        @Override
        public <T> Object doOperation(T o, OperationsManager operationsManager) throws ValueNotFoundException{
            return operationsManager.getUrlById(UUID.fromString((String) o));
        }
    }, DELETE{
        @Override
        public <T> Object doOperation(T o, OperationsManager operationsManager)  throws ValueNotFoundException{
            return operationsManager.deleteById(UUID.fromString((String) o));
        }
    }, CLEAN{
        @Override
        public <T> Object doOperation(T o, OperationsManager operationsManager) {
            return operationsManager.cleanAll();
        }
    };

    public abstract <T> Object doOperation(T o, OperationsManager operationsManager);

}
