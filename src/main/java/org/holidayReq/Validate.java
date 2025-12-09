package org.holidayReq;

import java.util.Scanner;

public class Validate {

    UserInteractions userInteractions = new UserInteractions();
    protected String pattern;
    protected String errMessage;

    public boolean isValid(String input) {
        return input.matches(this.pattern);
    }

    public String askForInputAgain(Scanner scanner) {
        scanner.useDelimiter("\n");
        String input = scanner.next();
        while (!isValid(input)) {
            userInteractions.userPrompt(this.errMessage);
            input = scanner.next();
        }
        return input;
    }

    public String employeeNumber(Scanner scanner) {
        this.pattern = "^[0-9]{6}$";
        this.errMessage = "\nInvalid entry. Employee numbers have six digits. Try again.\n";
        return askForInputAgain(scanner);
    }

    public String date(Scanner scanner) {
        this.pattern = "^\\d{2}\\/\\d{2}\\/\\d{4}$";
        this.errMessage = "\nInvalid format. Try again.";
        return askForInputAgain(scanner);
    }

    public String login(Scanner scanner) {
        this.pattern = "password";
        this.errMessage = "\nIncorrect password entered.\n";
        return askForInputAgain(scanner);
    }

    /*

    Commonality capture ->

    - all could take one argument - Done!
    - all could contain error messages
    - all update the variable
    - all have a condition and a loop

- the thing the variable must conform to (regex or type)
- if th inputted thing is valid then return it

     */

    public int selection(Scanner scanner) {
       int userInput = scanner.nextInt();
       while (!(userInput == 1 || userInput == 2)) {
           userInteractions.userPrompt("\nPlease select a valid option.");
           userInput = scanner.nextInt();
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