package com.company;

import exceptions.OperationException;

import java.io.*;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;


public class Main {
    static Loader starter;



    public static String getCustomStackTrace(Throwable throwable) {
        StringBuilder result = new StringBuilder( "\n        BOO-BOO: " );
        result.append(throwable.toString());
        String NL = "\n";
        String spaces = "                   ";
        result.append(NL);
        Stream.of(throwable.getStackTrace()).forEach(line -> result.append(spaces + line + NL));
        result.append("  ");

        return result.toString();
    }

    public static void main(String[] args) throws IOException,ClassNotFoundException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter configuration:url or urn.");
        starter = new Loader(Configurator.valueOf(scan.nextLine().toUpperCase()));
        Handler fh = new FileHandler("D:\\java\\logs.log");
        Logger logger = Logger.getLogger("");
        logger.addHandler(fh);
        logger.setLevel(Level.ALL);

        while(true) {
            try {
                System.out.println("Enter what you will do with your list or enter 'EXIT' to close program.");
                System.out.println("Your choice: ");
                String task = scan.nextLine();
                System.out.println(starter.launchOperation(task.toUpperCase()));
            }catch (OperationException | IOException exception){
                System.out.println(exception.getMessage());
            }catch (Exception exception){
                String og = getCustomStackTrace(exception);
                logger.info(og);
            }
        }

    }
}
