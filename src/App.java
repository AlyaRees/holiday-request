public class App {

    UserInteractions userInteractions = new UserInteractions();
    ReadFromFile reader = new ReadFromFile();
    WriteToFile writer = new WriteToFile();
    DateHandling dateHandling = new DateHandling();

    static void statusReport(String message) {
        System.out.println(message);
    }

    static void display(String message) {
        System.out.println(message);
    }

    private void optionOneInteraction() {

        userInteractions.userPrompt("\nEnter your full name:\n");
        String userFullName = userInteractions.getUserInputString();


        userInteractions.userPrompt("\nEnter your employee number:\n");
        String employeeNum = userInteractions.getUserInputString();

        userInteractions.userPrompt("\nEnter holiday you want to book:\n(Use the format DD/MM/YY)\n\nDate from:\n");
        String startDate = userInteractions.getUserInputString();

        startDate = dateHandling.checkAndUpdateDate("\n\nDate from:\n", startDate);

        userInteractions.userPrompt("\nDate to:\n");
        String endDate = userInteractions.getUserInputString();

        endDate = dateHandling.checkAndUpdateDate("\nDate to:\n", endDate);

        userInteractions.userPrompt("\nYou want to book from: " + startDate + " to " + endDate + "\nCorrect? (Y/N)\n");
        String areDatesCorrect = userInteractions.getUserInputString();

        while (areDatesCorrect.equalsIgnoreCase("N")) {
            userInteractions.userPrompt("\nEnter holiday you want to book:\n(Use the format DD/M/YYYY)\n\nDate from:\n");
            startDate = userInteractions.getUserInputString();

            userInteractions.userPrompt("\nDate to:\n");
            endDate = userInteractions.getUserInputString();
            userInteractions.userPrompt("\nYou want to book from: " + startDate + " to " + endDate + "\nCorrect? (Y/N)\n");

            areDatesCorrect = userInteractions.getUserInputString();
        }

        if (areDatesCorrect.equalsIgnoreCase("Y")) {
            writer.saveDetails(userFullName, employeeNum, startDate, endDate);
            statusReport("Details saved.");

        } else {
            statusReport("Invalid input.");
        }
    }

    private void optionThreeInteraction() {

        userInteractions.userPrompt("\nEnter admin password: \n");
        String enteredPassword = userInteractions.getUserInputString();

        String password = "password";

        if (!enteredPassword.equals(password)) {
            statusReport("\nIncorrect password entered.\n");
        }
    }

    void run() {

        userInteractions.userPrompt("\nSelect (1) or (2)\n\n 1 - Book holiday\n " +
                "2 - Check holiday approval status\n " +
                "3 - Approve holiday (admin only)\n");

        // replace with switch statement to tighten the constraints for user input here (should be 1 or 2 only)

        switch (userInteractions.getUserInputInt()) {
            case 1:
                optionOneInteraction();
                break;
            case 2:
                statusReport("\nHoliday approval status:\n");
                reader.getRequestedHoliday();
                break;
            case 3:
                optionThreeInteraction();
                break;
            default:
                statusReport("\nPlease select a valid option.");
                break;
        }
        userInteractions.closeScanner();

        // userInteractions.customScanner.next(); -> should throw an error because we're trying to use the scanner after closing it!
    }
}

// first issue -> scanner doesn't work the way I thought it did... 
// keeps printing the selected number when the code has finished executing.
// turns out I didn't notice a line of code printing out the user input at the end... oops!
// issue with scanner using all whitespace as delimiter. When a name with whitespace was entered (eg Alya Rees) it would skip to the next line. This was avoided by explicitly declaring that the delimiter is a newline. 
// extra features to add. Input validation (eg regex to check correct date format).
// issue with getting code tangled up!
// issue - didn't stop to read and understand code! commit message - Refactored checkAndUpdateDate 1dad081
// change the date format to dd/mm/yyyy
