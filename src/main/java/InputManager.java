import java.util.Scanner;

public class InputManager {
    Scanner scanner;
    public InputManager() {
        scanner = new Scanner(System.in);
    }
    public String GetInput() {
        return scanner.next();
    }
}
