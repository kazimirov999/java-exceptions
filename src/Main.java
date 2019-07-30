import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OperatoinManager url = new OperatoinManager(10, new HashMap<>());
        Scanner scanner1 = new Scanner(System.in);
        while (true) {
            System.out.println("Enter need command: ");
            try {
                Operation operation = Operation.valueOf(scanner1.nextLine().toUpperCase());
                operation.callOperation(scanner1, url);
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
            catch (OperationException e){
                System.out.println(e.getMessage());
            }
            catch (Exception e){
                throw new Error();
            }

        }
    }
}
