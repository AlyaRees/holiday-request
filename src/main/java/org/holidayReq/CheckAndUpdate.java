package org.holidayReq;

import java.util.Scanner;

import static org.holidayReq.App.statusReport;

public class CheckAndUpdate {

    UserInteractions userInteractions = new UserInteractions(new Scanner(System.in).useDelimiter("\n"));

    public String askForInputAgain(Scanner scanner, String pattern, String message) {
        String input = scanner.next();
        while (!input.matches(pattern)) {
            userInteractions.userPrompt(message);
            input = scanner.next();
        }
        return input;
    }

    public String employeeNumber(Scanner scanner) {
        return askForInputAgain(scanner, "^[0-9]{6}$", "\nInvalid entry. Employee numbers have six digits. Try again.\n");
    }

    public String date(Scanner scanner) {
        return askForInputAgain(scanner, "^\\d{2}\\/\\d{2}\\/\\d{4}$", "\nInvalid format. Try again.");
    }

    public String login(Scanner scanner) {
        return askForInputAgain(scanner, "password", "\nIncorrect password entered.\n");
    }

    /*

    Commonality capture ->

    - all could take one argument - Done!
    - all could contain error messages
    - all update the variable
    - all have a condition and a loop

- the thing the variable must conform to (regex or type)
- if th inputted thing is valid then return it

    public String askForInputAgain(Scanner scanner, String pattern, String message) {
        String input = scanner.next();
        while (!input.matches(pattern)) {
            userInteractions.userPrompt(message);
            input = scanner.next();
        }
        return input;
    }
     */


    public int selection(Scanner scanner) {
        int userInput = scanner.nextInt();
        while (!(userInput == 1 || userInput == 2)) {
            userInteractions.userPrompt("\nPlease select a valid option.");
            userInput = scanner.nextInt();
        }
        return userInput;
    }

    public double hours(Scanner scanner) {
        double userInput = scanner.nextDouble();
        while (!(userInput > 0.0) && !(userInput <= 7.5)) {
            userInteractions.userPrompt("\nPlease enter a valid duration.\n");
            userInput = scanner.nextDouble();
        }
        return userInput;
    }
}
/*

    User error messages ->

    Employee number
    "\nInvalid entry. Employee numbers have six digits. Try again.\n"

    Date
    "\nInvalid format. Try again."
 */