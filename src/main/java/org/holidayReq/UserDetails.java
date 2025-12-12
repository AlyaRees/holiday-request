package org.holidayReq;

import java.util.Scanner;

public class UserDetails {

    UserInteractions userInteractions = new UserInteractions(new Scanner(System.in).useDelimiter("\n"));
    CheckAndUpdate checkAndUpdate = new CheckAndUpdate();

    private String startDate;
    private String endDate;
    private String date;
    private String employeeName;
    private String employeeNumber;
    private String reason;
    private double hours;

    // All the getter and setter operations for collecting the employee details to be written to the file.

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
        return hours;
    }

    public String getDate() {
        return date;
    }

    public String getCorrectDate() {
        userInteractions.userPrompt("\nDate: " + date + " correct?\n");
        return userInteractions.getUserInputStr();
    }

    public void setEmployeeNumber() {
        userInteractions.userPrompt("\nEnter your employee number:\n");
        // check and update employee number.
        this.employeeNumber = checkAndUpdate.employeeNumber(userInteractions.scanner);
    }

    public void setStartDate() {
        userInteractions.userPrompt("\nEnter the date of absence you want to book:\n(Use the format DD/MM/YYYY)\n\nDate from:\n");
        this.startDate = checkAndUpdate.date(userInteractions.scanner);
    }

    public void setEndDate() {
        userInteractions.userPrompt("\nDate to:\n");
        this.endDate = checkAndUpdate.date(userInteractions.scanner);
    }

    public void setDate() {
        userInteractions.userPrompt("\nEnter the date you're late on:\n(Use the format DD/M/YYYY)\n");
        this.date = checkAndUpdate.date(userInteractions.scanner);
    }

    public void setReason() {
        userInteractions.userPrompt("\nEnter your reason for absence: \n");
        this.reason = userInteractions.getUserInputStr();
    }

    public String getReason() {
        return reason;
    }

    public void setHours() {
        userInteractions.userPrompt("\nHow many working hours will you be absent?\n(0.5 for 30 minutes, 1 for 1 hour)\n");
        this.hours = checkAndUpdate.hours(userInteractions.scanner);
    }

    public String getCorrectHours() {
        userInteractions.userPrompt("\n" + hours + " hours, correct?\n");
        return userInteractions.getUserInputStr();
    }
}

