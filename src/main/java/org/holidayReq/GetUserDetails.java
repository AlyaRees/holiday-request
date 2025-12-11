package org.holidayReq;

import java.util.Scanner;

public class GetUserDetails {

    protected String startDate;
    protected String endDate;
    protected String date;

    UserInteractions userInteractions = new UserInteractions(new Scanner(System.in).useDelimiter("\n"));
    CheckAndUpdate checkAndUpdate = new CheckAndUpdate();

    public String getUserName() {
        userInteractions.userPrompt("\nEnter your full name:\n");
        return userInteractions.getUserInputStr();
    }

    public String getEmployeeNumber() {
        userInteractions.userPrompt("\nEnter your employee number:\n");
        // check and update employee number.
        return checkAndUpdate.employeeNumber(userInteractions.customScanner);
    }

    public String getStartDate() {
        userInteractions.userPrompt("\nEnter the date of absence you want to book:\n(Use the format DD/MM/YYYY)\n\nDate from:\n");
        this.startDate = checkAndUpdate.date(userInteractions.customScanner);
        return startDate;
    }

    public String getEndDate() {
        userInteractions.userPrompt("\nDate to:\n");
        this.endDate = checkAndUpdate.date(userInteractions.customScanner);
        return endDate;
    }

    public String areEnteredDatesCorrect() {
        userInteractions.userPrompt("\nYou want to book from: " + startDate + " to " + endDate + "\nCorrect? (Y/N)\n");
        return userInteractions.getUserInputStr();
    }

    public double getHours() {
        userInteractions.userPrompt("\nHow many working hours will you be absent?\n(0.5 for 30 minutes, 1 for 1 hour)\n");
        return checkAndUpdate.hours(userInteractions.customScanner);
    }

    public String getDate() {
        userInteractions.userPrompt("\nEnter the date you're late on:\n(Use the format DD/M/YYYY)\n");
        this.date = checkAndUpdate.date(userInteractions.customScanner);
        return userInteractions.getUserInputStr();
    }

    public String isDateCorrect() {
        userInteractions.userPrompt("\nDate: " + date + " correct?\n");
        return userInteractions.getUserInputStr();
    }
}

