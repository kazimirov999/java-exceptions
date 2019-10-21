import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        OperationManager operationManager = new OperationManager(10, new HashMap<>());
        Scanner scanner1 = new Scanner(System.in);
        Configuration configuration = new Configuration();
        while (true) {
            System.out.println("Enter need command: ");
            try {
                Operation operation = Operation.valueOf(scanner1.nextLine().toUpperCase());
                operation.callOperation(operationManager, configuration);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (OperationException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Very scary exception");
            }
        }
    }
}
