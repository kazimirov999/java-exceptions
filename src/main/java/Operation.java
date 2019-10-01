import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.UUID;

public enum Operation {
    ADD,
    CHECK,
    GET,
    DELETE,
    CLEAN,
    EXIT,
    CONTINUE;

    private <T> T valueFromString(String string) throws MalformedURLException {
        if (Configuration.getType().equals("URN")){
            return (T) Urn.checkUrn(string);
        }
        else {
            return (T) Url.createUrl(string);
        }
    }
    private <T> T keyFromString(String string){
        if (Configuration.getType().equals("URN")){
            return (T) (Long)Long.parseLong(string);
        }
        else {
            return (T) UUID.fromString(string);
        }
    }


    public void callOperation(OperationManager operatoinManager, Configuration configuration) throws MalformedURLException {
        SerializationManager serializationManager = new SerializationManager(operatoinManager);
        Scanner scanner = new Scanner(System.in);
        switch (this) {

            case ADD: {
                String argument = scanner.nextLine();
                operatoinManager.add(valueFromString(argument));
                break;
            }
            case GET: {
                String argument = scanner.nextLine();
                operatoinManager.get(keyFromString(argument));
                break;
            }
            case CHECK: {
                String argument = scanner.nextLine();
                operatoinManager.check(valueFromString(argument));
                break;
            }
            case DELETE: {
                String argument = scanner.nextLine();
                operatoinManager.delete(keyFromString(argument));
                break;
            }
            case CLEAN: {
                operatoinManager.clean();
                break;
            }
            case CONTINUE:{
                serializationManager.readFile();
                break;
            }
            case EXIT:{
                serializationManager.writeFile();
                operatoinManager.exitTheProgram();
                break;
            }
        }
    }
}

