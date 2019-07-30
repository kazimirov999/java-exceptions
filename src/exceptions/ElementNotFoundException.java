package exceptions;

public class ElementNotFoundException extends OperationException{
    public ElementNotFoundException(String message){
        super(message);
    }
}
