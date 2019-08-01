package com;

import com.comand.CommandListener;
import org.jnativehook.NativeHookException;

public class Main {

    public static void main(String[] args) throws NativeHookException {
        CommandListener commandListener = new CommandListener();
        commandListener.run();

    }
}

