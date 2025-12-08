package org.holidayReq;

import java.util.Scanner;

public class HandleValidation {

    abstract class Validate {

        protected String regex;

        public boolean isValid(String input) {
            return input.matches(this.regex);
        }

        public String askForInputAgain(Scanner scanner) {
            scanner.useDelimiter("\n");
            String input = scanner.next();
            while (!isValid(input)) {
                userInteractions.userPrompt("\nInvalid format. Try again.");
                input = scanner.next();
            }
            return input;
        }
    }

    public class EmployeeNumber extends Validate {
        public EmployeeNumber() {
            this.regex = "^[0-9]{6}$";
        }
    }

    public class Date extends Validate {
        public Date() {
            this.regex = "^\\d{2}\\/\\d{2}\\/\\d{4}$";
        }
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

    UserInteractions userInteractions = new UserInteractions();

    public int selection(int userInputInt) {
        while (userInputInt != 1 || userInputInt != 2) {
            userInteractions.userPrompt("\nSelect from the provided options only.\n");
            userInputInt = userInteractions.getUserInputInt();
        }
        return userInputInt;
    }
}

/*

    User error messages ->

    Employee number
    "\nInvalid entry. Employee numbers have six digits. Try again.\n"

    Date
    "\nInvalid format. Try again."
 */