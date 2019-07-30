import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public enum Operation {
    ADD,
    CHECK,
    GET,
    DELETE,
    CLEAN_ALL;

    public  void callOperation(Scanner scanner, OperatoinManager operatoinManager) {
         scanner = new Scanner(System.in);
        switch (this) {
            case ADD:
                operatoinManager.add(scanner.nextLine());
                break;
            case GET:
                operatoinManager.getById(UUID.fromString(scanner.nextLine()));
                break;
            case CHECK:
                operatoinManager.checkByUrl(scanner.nextLine());
                break;
            case DELETE:
                operatoinManager.deleteById(UUID.fromString(scanner.nextLine()));
                break;
            case CLEAN_ALL:
                operatoinManager.clean();
                break;
        }
    }
    }
