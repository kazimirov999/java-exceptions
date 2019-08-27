package com.comand;

import com.exception.OperationException;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandListener implements NativeKeyListener {

    private static final int EXIT_KEY_CODE = 1;
    private static final int EXECUTE_COMMAND_KEY_CODE = 28;
    private static final String EXIT_COMMAND = "exit";

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

        if(nativeKeyEvent.getKeyCode() == EXIT_KEY_CODE){

            sendCommand(EXIT_COMMAND);

        }else if(nativeKeyEvent.getKeyCode() == EXECUTE_COMMAND_KEY_CODE){

            sendCommand(new Scanner(System.in).nextLine());

        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    public void run() throws NativeHookException {

        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new CommandListener());

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);

    }

    private void sendCommand(String data){

        Command command = new Command();

        try {

            command.execute(data);

        } catch (OperationException e) {

            System.out.println(e.getMessage());

        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
