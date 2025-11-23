import java.util.Scanner;

public class UserInteractions {

    Scanner customScanner = new Scanner(System.in).useDelimiter("\n");

    void closeScanner() {
        customScanner.close();
    }

    static void userPrompt(String message) {
        System.out.println(message);
    }

    int getUserInputInt() {
        return customScanner.nextInt();
    }

    String getUserInputString() {
        return customScanner.next();
    }
}