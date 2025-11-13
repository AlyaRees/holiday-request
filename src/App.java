import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    static Scanner scanner = new Scanner(System.in);

    static String checkAndUpdateDate(String message, String date) {
        while (!isValidFormat(date)) {
            askForDateAgain(message);
            date = scanner.next();
        }
           return date;
    }

    static void askForDateAgain(String message) {
                log("\nInvalid format. Try again.");
                log(message);
    }

    static boolean isValidFormat(String date) {
     Pattern dateFormat = Pattern.compile("^\\d{2}\\/\\d{2}\\/\\d{4}$");
       Matcher checkFormat = dateFormat.matcher(date);

       return checkFormat.matches();
    }

    static void saveDetails(String name, String number, String startDate, String endDate) {
           try (FileWriter fileWriter = new FileWriter("HolidayReq.txt")) {
           fileWriter.write(name + "\n" + number + "\n" + startDate + " " + endDate);
           fileWriter.close();
           log("Successfully wrote to file.");
        } catch (IOException error) {
            System.out.println("Error writing to file.");
            error.printStackTrace();
        }
    }

    static void log(String message) {
        System.out.println(message);
    }

    static void logToUser(String message) {
        System.out.println(message);
    }

    static void optionOneInteraction() {
        logToUser("\nEnter your full name:\n");
            String userFullname = scanner.next();

            logToUser("\nEnter your employee number:\n");
            String employeeNum = scanner.next();

            logToUser("\nEnter holiday you want to book:\n(Use the format DD/MM/YY)\n\nDate from:\n");
            String startDate = scanner.next();

            startDate = checkAndUpdateDate("\n\nDate from:\n", startDate);

            logToUser("\nDate to:\n");
            String endDate = scanner.next();

            endDate = checkAndUpdateDate("\nDate to:\n", endDate);

            logToUser("\nYou want to book from: " + startDate + " to " + endDate + "\nCorrect? (Y/N)\n");
            String areDatesCorrect = scanner.next();

            while (areDatesCorrect.equalsIgnoreCase("N")) {
            logToUser("\nEnter holiday you want to book:\n(Use the format DD/M/YY)\n\nDate from:\n");
            startDate = scanner.next();

            logToUser("\nDate to:\n");
            endDate = scanner.next();
            logToUser("\nYou want to book from: " + startDate + " to " + endDate + "\nCorrect? (Y/N)\n");

            areDatesCorrect = scanner.next();
    }

    if (areDatesCorrect.equalsIgnoreCase("Y")) {
            saveDetails(userFullname, employeeNum, startDate, endDate);
            logToUser("Details saved.");
        
        } else {
            logToUser("Invalid input.");
        }
}
    public static void main(String[] args) {

        scanner.useDelimiter("\n"); // Set delimiter to newline to capture full lines including spaces

        logToUser("\nSelect (1) or (2)\n\n 1 - Would you like to book holiday?\n 2 - Would you like to check holiday approval status?\n");
        
        int chosenOption = scanner.nextInt();

        if (chosenOption == 1) {
            optionOneInteraction();
            } else {
            logToUser("\nHoliday approval status:\n\nNo holiday has been submitted.\n");
        }
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
