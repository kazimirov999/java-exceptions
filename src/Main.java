import exeption.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        OperationsManager operationsManager = new OperationsManager(10, new HashMap<>());

        System.out.println((Operation.ADD + "(URL) " + Operation.GET + "(UUID) " + Operation.CHECK + "(URL) "
                + Operation.DELETE + "(UUID) " + Operation.CLEAN+"()").toLowerCase());

        while (true){
            try {

                String command = scanner.nextLine();
                command = command.trim().replaceAll(" +", " ");

                Operation operation = getOperation(command);
                String argument = getArgument(command);

                Object result = operation.doOperation(argument, operationsManager);

                System.out.println(result);

            }catch (OperationException e){

                System.out.println(e.getMessage());

            }catch (Exception e){

                throw new Error();

            }
        }

    }
    private static Operation getOperation(String command){
        try {
            return Operation.valueOf(command.split(" ")[0].toUpperCase());
        }catch (IllegalArgumentException e){
            throw new CommandNotFoundException("Command not found. " + e.getMessage(), e);
        }
    }

    private static String getArgument(String command){
        long amountArgument = command.chars().filter(ch -> ch == ' ').count();
        if(amountArgument == 0){
            String emptyArgument = "";
            return emptyArgument;
        }else if(amountArgument == 1){
            String argument = command.split(" ")[1];
            return argument;
        }else {
            throw new exeption.IllegalArgumentException("Max number of argument 1");
        }
    }
}

