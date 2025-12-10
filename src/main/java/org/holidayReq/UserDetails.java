package org.holidayReq;

public class UserDetails {

    protected String startDate;
    protected String endDate;

    UserInteractions userInteractions = new UserInteractions();
    Validate validate = new Validate();

    public String getUserName() {
        userInteractions.userPrompt("\nEnter your full name:\n");
        return userInteractions.getUserInputStr();
    }

    public String getEmployeeNumber() {
        userInteractions.userPrompt("\nEnter your employee number:\n");
        // check and update employee number.
        return validate.employeeNumber(userInteractions.customScanner);
    }

    public String getStartDate() {
        userInteractions.userPrompt("\nEnter the date of absence you want to book:\n(Use the format DD/MM/YYYY)\n\nDate from:\n");
        this.startDate = validate.date(userInteractions.customScanner);
        return startDate;
    }

    public String getEndDate() {
        userInteractions.userPrompt("\nDate to:\n");
        this.endDate = validate.date(userInteractions.customScanner);
        return endDate;
    }

    public String checkDates() {
        userInteractions.userPrompt("\nYou want to book from: " + startDate + " to " + endDate + "\nCorrect? (Y/N)\n");
        return userInteractions.getUserInputStr();
    }

    public double getHours() {
        userInteractions.userPrompt("\nHow many working hours will you be absent?\n(0.5 for 30 minutes, 1 for 1 hour)\n");
        return validate.hours(userInteractions.customScanner);
    }
}
