import java.util.Random;
import java.util.Scanner;

public class UserInteractions {

    Scanner customScanner = new Scanner(System.in).useDelimiter("\n");

    static void userPrompt(String message) {
        System.out.println(message);
    }

    int getUserInputInt() {
        int chosenOption = customScanner.nextInt();
        return chosenOption;
    }

    String getUserInputString() {
        String userInput = customScanner.next();
        return userInput;
    }
}