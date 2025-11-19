import java.util.Scanner;

public class UserInteractions {

    Scanner scanner = new Scanner(System.in);

    static void userPrompt(String message) {
        System.out.println(message);
    }

    int getUserInputInt() {
        int chosenOption = scanner.nextInt();
        return chosenOption;
    }

    String getUserInputString() {
        String userInput = scanner.next();
        return userInput;
    }
}