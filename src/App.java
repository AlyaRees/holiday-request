import java.util.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class App {

    static String fileName = "HolidayReq.txt";

    static void userPrompt(String message) {
    System.out.println(message);
}

    static void statusReport(String message) {
    System.out.println(message);
}

    static void display(String message) {
    System.out.println(message);
}

    static String checkAndUpdateDate(String message, String date, Scanner scanner) {
        while (!isValidFormat(date)) {
            askForDateAgain(message);
            date = scanner.next();
        }
           return date;
    }

    static void askForDateAgain(String message) {
                userPrompt("\nInvalid format. Try again.");
                userPrompt(message);
    }

    static boolean isValidFormat(String date) {
     Pattern dateFormat = Pattern.compile("^\\d{2}\\/\\d{2}\\/\\d{4}$");
       Matcher checkFormat = dateFormat.matcher(date);

       return checkFormat.matches();
    }

    static void saveDetails(String name, String number, String startDate, String endDate) {
           try (FileWriter fileWriter = new FileWriter(fileName, true)) {
           fileWriter.write("Name: " + name + "\nEmployee Number: " + number + "\nDate: " + startDate + " " + endDate + "\n");
           fileWriter.close();
           statusReport("Successfully wrote to file.");
        } catch (IOException error) {
            statusReport("Error writing to file.");
            error.printStackTrace();
        }
    }

    static void getRequestedHoliday() {

        File fileObject = new File(fileName);
        ArrayList<String> dates = new ArrayList<>();

        try (Scanner fileReader = new Scanner(fileObject)) {
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                if (data.contains("Date:")) {
                    data = data.replace("Date: ", "");
                    dates.add(data);
                }
            }
            // converts the dates arraylist to a stream
            Stream<String[]> allDates = dates.stream()
            .map(line -> line.split(" "));

            allDates.forEach(line -> display("From " + line[0] + " to " + line[1] + " - PENDING APPROVAL" + "\n")); 
            fileReader.close();

        } catch (FileNotFoundException error) {
            statusReport("No holiday has been submitted.\n");
        }
    }

    static void optionOneInteraction(Scanner scanner) {
        userPrompt("\nEnter your full name:\n");
            String userFullname = scanner.next();

            userPrompt("\nEnter your employee number:\n");
            String employeeNum = scanner.next();

            userPrompt("\nEnter holiday you want to book:\n(Use the format DD/MM/YY)\n\nDate from:\n");
            String startDate = scanner.next();

            startDate = checkAndUpdateDate("\n\nDate from:\n", startDate, scanner);

            userPrompt("\nDate to:\n");
            String endDate = scanner.next();

            endDate = checkAndUpdateDate("\nDate to:\n", endDate, scanner);

            userPrompt("\nYou want to book from: " + startDate + " to " + endDate + "\nCorrect? (Y/N)\n");
            String areDatesCorrect = scanner.next();

            while (areDatesCorrect.equalsIgnoreCase("N")) {
            userPrompt("\nEnter holiday you want to book:\n(Use the format DD/M/YYYY)\n\nDate from:\n");
            startDate = scanner.next();

            userPrompt("\nDate to:\n");
            endDate = scanner.next();
            userPrompt("\nYou want to book from: " + startDate + " to " + endDate + "\nCorrect? (Y/N)\n");

            areDatesCorrect = scanner.next();
    }

    if (areDatesCorrect.equalsIgnoreCase("Y")) {
            saveDetails(userFullname, employeeNum, startDate, endDate);
            statusReport("Details saved.");
        
        } else {
            statusReport("Invalid input.");
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
