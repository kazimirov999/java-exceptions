package exceptions;

public class CommandNotFoundException extends OperationException {
    public CommandNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
}
