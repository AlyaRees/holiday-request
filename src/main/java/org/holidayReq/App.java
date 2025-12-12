package org.holidayReq;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    // EVERYTHING'S working at this point!

    UserInteractions userInteractions = new UserInteractions(new Scanner(System.in).useDelimiter("\n"));
    ReadFromFile reader = new ReadFromFile();
    WriteToFile writer = new WriteToFile();
    UpdateFile updateFile = new UpdateFile();
    CheckAndUpdate checkAndUpdate = new CheckAndUpdate();
    UserDetails user = new UserDetails();

    private static String getContent(Absence holidayRequest) {
        return holidayRequest.fileContents();
    }

    private String selectHoliday(int index) {
        String selectedRequest = "";
        try {
            selectedRequest = reader.getFileContent().get(index);
        } catch (Exception e) {
            statusReport("Please select from the provided options.");
            index = userInteractions.getUserInputInt();
            selectHoliday(index);
        }
        return selectedRequest;
    }

    public int getCorrectIndex(int userInputInt) {
        return userInputInt - 1;
    }

    public ArrayList<String> addNumberIDs(ArrayList<String> list) {
        int index = 0;
        do {
            try {
                list.set(index, (index + 1) + " - " + list.get(index));
                index++;
            } catch (IndexOutOfBoundsException e) {
                System.exit(1);
            }
        } while (index < list.size());
        return list;
    }

    public static void statusReport(String message) {
        System.out.println(message);
    }

    static void display(String message) {
        System.out.println(message);
    }

    static void displayElements(ArrayList<String> list) {
        // logs each element in an array to the console
        list.forEach(item -> display(item));
    }

    private void selectRequestToBook() {
        userInteractions.userPrompt("\nWhat absence would you like to request?\n");
        display("1 - Holiday\n" + "2 - Sickness\n" + "3 - Lateness to work\n");
        int bookingSelectedOption = Integer.parseInt(checkAndUpdate.selection(userInteractions.customScanner));
        switch (bookingSelectedOption) {
            case 1 -> bookHoliday();
            case 2 -> bookSickness();
            case 3 -> bookLateness();
        }
    }

    private void bookHoliday() {

        user.setEmployeeName();
        user.setEmployeeNumber();
        user.setStartDate();
        user.setEndDate();

        String areDatesCorrect = user.areEnteredDatesCorrect();

        while (areDatesCorrect.equalsIgnoreCase("N")) {
            user.setStartDate();
            user.setEndDate();
            areDatesCorrect = user.areEnteredDatesCorrect();
        }

        if (areDatesCorrect.equalsIgnoreCase("Y")) {
            Absence holidayRequest = new Holiday(user.getEmployeeName(), user.getEmployeeNumber(), user.getStartDate(), user.getEndDate());
            writer.save(getContent(holidayRequest));
            updateFile.reformatFile();
            statusReport("Details saved.");

        } else {
            statusReport("Invalid input.");
        }
    }

    private void bookLateness() {

        user.setEmployeeName();
        user.setEmployeeNumber();
        String date = user.getDate();

        String yesOrNo = user.isDateCorrect();

        while (yesOrNo.equalsIgnoreCase("N")) {
            userInteractions.userPrompt("\nEnter the date you're late on:\n(Use the format DD/M/YYYY)\n");
            date = userInteractions.getUserInputStr();

            userInteractions.userPrompt("\nDate: " + date + " Correct? (Y/N)\n");

            yesOrNo = userInteractions.getUserInputStr();
        }

        userInteractions.userPrompt("\nEnter your reason for absence: \n");
        String reason = userInteractions.getUserInputStr();

        double hours = user.getHours();

        userInteractions.userPrompt("\n" + hours + " hours, correct?\n");
        String isEntryCorrect = userInteractions.getUserInputStr();

        while (isEntryCorrect.equalsIgnoreCase("N")) {
            userInteractions.userPrompt("\nHow many working hours will you be absent?\n(0.5 for 30 minutes, 1 for 1 hour)\n");
            hours = checkAndUpdate.hours(userInteractions.customScanner);

            userInteractions.userPrompt("\n" + hours + "hours, correct?\n");

            isEntryCorrect = userInteractions.getUserInputStr();
        }

        if (isEntryCorrect.equalsIgnoreCase("Y")) {
            Absence lateness = new Lateness(user.getEmployeeName(), user.getEmployeeNumber(), date, hours, reason);
            writer.save(lateness.fileContents());
            updateFile.reformatFile();
            statusReport("Details saved.");

        } else {
            statusReport("Invalid input.");
        }
    }

    private void bookSickness() {

        user.setEmployeeName();
        user.setEmployeeNumber();
        user.setStartDate();
        user.setEndDate();

        userInteractions.userPrompt("\nEnter your reason for absence: \n");
        String reason = userInteractions.getUserInputStr();

        String areDatesCorrect = user.areEnteredDatesCorrect();

        while (areDatesCorrect.equalsIgnoreCase("N")) {
            user.setStartDate();
            user.setEndDate();
            areDatesCorrect = user.areEnteredDatesCorrect();
        }

        if (areDatesCorrect.equalsIgnoreCase("Y")) {
            Absence sickLeaveRequest = new SickLeave(user.getEmployeeName(), user.getEmployeeNumber(), user.startDate, user.endDate, reason);
            writer.save(sickLeaveRequest.fileContents().trim());
            updateFile.reformatFile();
            statusReport("Details saved.");

        } else {
            statusReport("Invalid input.");
        }
    }

    private void adminReviewRequests() {

        userInteractions.userPrompt("\nEnter admin password: \n");
        checkAndUpdate.login(userInteractions.customScanner);
        statusReport("\nLogin successful.");

        display("\nSelect holiday to review:\n");
        // Gets the file content, puts it into an array list and adds number IDs
        ArrayList<String> fileContent = reader.getFileContent();
        displayElements(addNumberIDs(fileContent));

        // User selects a holiday request, and it is then displayed.
        // makes sure the correct number is used for indexing into the array of absence requests
        // ie the first request is numbered '1', which corresponds to the first element in the array, indexed at 0
        int selectedHolidayOption = getCorrectIndex(userInteractions.getUserInputInt());
        String selectedRequest = selectHoliday(selectedHolidayOption);
        display("\nYou selected:\n");
        display(selectedRequest);

        // The option to approve or decline the request
        display("\n1 - Approve\n2 - Decline");
        int selectedApproveOrDecline = checkAndUpdate.selectionInt(userInteractions.customScanner);
        // The selected request is updated and displayed
        updateFile.holidayStatus(selectedHolidayOption, selectedApproveOrDecline);
        display("\nThe following request has been updated:\n");
        display(reader.getFileContent().get(selectedHolidayOption));
    }

    public void run() {

        userInteractions.userPrompt("\nSelect (1) or (2)\n\n 1 - Request absence\n " + "2 - Check request approval status\n " + "3 - Approve an absence request (admin only)\n");

        int selectedOption = Integer.parseInt(checkAndUpdate.selection(userInteractions.customScanner));

        switch (selectedOption) {
            case 1 -> selectRequestToBook();
            case 2 -> {
                statusReport("\nHoliday approval status:\n");
                displayElements(reader.getFileContent());
            }
            case 3 -> adminReviewRequests();
        }
        userInteractions.closeScanner();
    }
}

// first issue -> scanner doesn't work the way I thought it did...
// keeps printing the selected number when the code has finished executing.
// turns out I didn't notice a line of code printing out the user input at the end... oops!
// issue with scanner using all whitespace as delimiter. When a name with whitespace was entered (e.g. Alya Rees) it would skip to the next line. This was avoided by explicitly declaring that the delimiter is a newline.
// extra features to add. Input validation (e.g. regex to check correct date format).
// issue with getting code tangled up!
// issue - didn't stop to read and understand code! commit message - Refactored checkAndUpdateDate 1dad081
// change the date format to dd/mm/yyyy
// Struggled to solve a problem. Stopped and went through the call stack of the code, beginning with the entry point (Main method, app.run, selected option etc.) Read through and contemplated on each part. It works is the bare minimum. Does it actually make sense? Could it be better?
// Idea -> Add a validate input base class. Subclasses could be for employee number, dates etc