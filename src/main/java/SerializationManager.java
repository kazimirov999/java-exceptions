
import java.io.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;

public class SerializationManager implements Serializable {

    OperationManager operatoinManager;

    public SerializationManager(OperationManager operatoinManager) {
        this.operatoinManager = new OperationManager(10, new HashMap());
    }
    protected void writeFile(){

        try {
            operatoinManager.saveTime.add(operatoinManager.findingTime());
            FileOutputStream fileOutputStream = new FileOutputStream("operationManager.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(operatoinManager);
            objectOutputStream.writeObject(operatoinManager.saveTime);
            objectOutputStream.writeObject(operatoinManager.readTime);
            objectOutputStream.writeInt(OperationManager.amountUrl);
            System.out.println("File has been written");
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }
    protected void readFile(){
        try {
            FileInputStream fileInputStream = new FileInputStream("operationManager.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            System.out.println("File has been deserialization");
            operatoinManager = (OperationManager) objectInputStream.readObject();

            operatoinManager.saveTime = (List<Instant>) objectInputStream.readObject();

            operatoinManager.readTime = (List<Instant>) objectInputStream.readObject();
            OperationManager.amountUrl = objectInputStream.readInt();
            operatoinManager.readTime.add(operatoinManager.findingTime());

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException ioe) {
            ioe.getMessage();
            return;
        } catch (ClassNotFoundException e) {
            e.getMessage();
            return;
        }
    }
}
