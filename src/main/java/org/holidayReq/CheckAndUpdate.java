package org.holidayReq;

import java.util.Scanner;

public class CheckAndUpdate {

    UserInteractions userInteractions = new UserInteractions(new Scanner(System.in).useDelimiter("\n"));

    // gets input from the user, compares it to the given pattern and logs the given message when there is a mismatch.
    public String askForInputAgain(Scanner scanner, String pattern, String message) {
        String input = scanner.next();
        while (!input.matches(pattern)) {
            userInteractions.userPrompt(message);
            input = scanner.next();
        }
        return input;
    }

    // all features that need checking and updating have a method.
    public String employeeNumber(Scanner scanner) {
        return askForInputAgain(scanner, "^[0-9]{6}$", "\nInvalid entry. Employee numbers have six digits. Try again.\n");
    }

    public String date(Scanner scanner) {
        return askForInputAgain(scanner, "^\\d{2}\\/\\d{2}\\/\\d{4}$", "\nInvalid format. Try again.");
    }

    public String login(Scanner scanner) {
        return askForInputAgain(scanner, "password", "\nIncorrect password entered.\n");
    }

    public String selection(Scanner scanner) {
        return askForInputAgain(scanner, "[1-3]{1}", "\nPlease select a valid option.\n");
    }

    public String approveOrDeclineSelection(Scanner scanner) {
        return askForInputAgain(scanner, "[1-2]{1}", "\nPlease select a valid option.\n");
    }

    // makes sure that the correct range and type of input is received.
    public double hours(Scanner scanner) {
        double userInput = scanner.nextDouble();
        while (!(userInput > 0.0) && !(userInput <= 7.5)) {
            userInteractions.userPrompt("\nPlease enter a valid duration.\n");
            userInput = scanner.nextDouble();
        }
        return userInput;
    }
}