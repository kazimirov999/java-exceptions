package com.comand;

import com.exception.OperationException;

import java.util.Scanner;

public class CommandLineListener {

    private final Command command;

    public CommandLineListener(Command command) {
        this.command = command;
    }

    public Object sendCommand(String data){

        try {

            return command.execute(data);

        } catch (OperationException e) {

            return e.getMessage() ;

        }catch (Exception e){
            throw new RuntimeException();
        }

    }

    public void start(){
        String data = new Scanner(System.in).nextLine();
        System.out.println(sendCommand(data));
        start();
    }
}
