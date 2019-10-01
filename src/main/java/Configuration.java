import java.util.Scanner;

public class Configuration {

    private static String type;


    public Configuration () throws ValueNotFoundExcetpion {
        System.out.println("Enter configuration ");
        Scanner scanner = new Scanner(System.in);
        type = scanner.nextLine().toUpperCase();
        if (type.equals("URN")) {
            System.out.println("Your type is URN");
        } else if (type.equals("URL")) {
            System.out.println("Your type is URL");
        } else {
            throw new ValueNotFoundExcetpion("Input correct type");
        }
    }
    public static String getType() {
        return type;
    }

}
