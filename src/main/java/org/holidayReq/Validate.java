package org.holidayReq;

import java.util.Scanner;

public class Validate {

    UserInteractions userInteractions = new UserInteractions();

    String checkAndUpdateDate(String message, String date) {
        while (!isValidFormat(date)) {
            askForDateAgain(message);
            date = userInteractions.getUserInputStr();
        }
        return date;
    }

    public String checkAndUpdate(Scanner scanner) {
        String employeeNum = scanner.next();
        while (!validateFormat(employeeNum)) {
            userInteractions.userPrompt("\nInvalid entry. Employee numbers have six digits. Try again.\n");
            employeeNum = scanner.next();
        }
        return employeeNum;
    }

    void askForDateAgain(String message) {
        userInteractions.userPrompt("\nInvalid format. Try again.");
        userInteractions.userPrompt(message);
    }

    public boolean isValidFormat(String date) {
        return date.matches("^\\d{2}\\/\\d{2}\\/\\d{4}$");
    }
    public boolean validateFormat(String employeeNum) {
        return employeeNum.matches("^[0-9]{6}$");
    }
}