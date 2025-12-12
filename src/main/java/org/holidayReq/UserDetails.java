package org.holidayReq;

import java.util.Scanner;

public class UserDetails {

    protected String startDate;
    protected String endDate;
    protected String date;
    private String employeeName;
    private String employeeNumber;

    UserInteractions userInteractions = new UserInteractions(new Scanner(System.in).useDelimiter("\n"));
    CheckAndUpdate checkAndUpdate = new CheckAndUpdate();

    public void setEmployeeName() {
        userInteractions.userPrompt("\nEnter your full name:\n");
        this.employeeName = userInteractions.getUserInputStr();
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
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

    public void setEmployeeNumber() {
        userInteractions.userPrompt("\nEnter your employee number:\n");
        // check and update employee number.
        this.employeeNumber = checkAndUpdate.employeeNumber(userInteractions.customScanner);
    }

    public void setStartDate() {
        userInteractions.userPrompt("\nEnter the date of absence you want to book:\n(Use the format DD/MM/YYYY)\n\nDate from:\n");
        this.startDate = checkAndUpdate.date(userInteractions.customScanner);
    }

    public void setEndDate() {
        userInteractions.userPrompt("\nDate to:\n");
        this.endDate = checkAndUpdate.date(userInteractions.customScanner);
    }
}

