package com.company;

import java.io.*;

public class SerializingObject {

    public OperationManager readFromFile(String path) throws IOException,ClassNotFoundException{
        ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(path));
        OperationManager operationManager = (OperationManager) objectIn.readObject();
        objectIn.close();
        return operationManager;
    }

    public void writeToFile(OperationManager urlOperation,String path)throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeObject(urlOperation);
        oos.close();
    }
}
