package com.company;

import exceptions.OperationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        UrlOperation urlOperation = new UrlOperation(10, new HashMap<>());
        Scanner scan = new Scanner(System.in);

        while(true) {
            try {
                System.out.println("Enter what you will do with your list: ");
                String task = scan.nextLine().toUpperCase();
                System.out.println(urlOperation.launchOperation(task));
            }catch (OperationException exception){
                System.out.println(exception.getMessage());
            }catch (Exception exception){
                throw new Error();
            }
        }
    }
}
