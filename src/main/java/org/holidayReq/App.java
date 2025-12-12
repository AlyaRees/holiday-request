package org.holidayReq;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    // By using a newline as the delimiter for my scanner I'm specifying that the input ends on the next newline character (when the user hits return).
    UserInteractions userInteractions = new UserInteractions(new Scanner(System.in).useDelimiter("\n"));
    ReadFromFile reader = new ReadFromFile();
    WriteToFile writer = new WriteToFile();
    UpdateFile updateFile = new UpdateFile();
    CheckAndUpdate checkAndUpdate = new CheckAndUpdate();
    UserDetails user = new UserDetails();

    // tries to fetch a request from the file.
    private String selectRequest(int index) {
        String selectedRequest;
        try {
            selectedRequest = reader.getFileContent().get(index);
        } catch (Exception e) {
            statusReport("Please select from the provided options.");
            index = getCorrectIndex(userInteractions.getUserInputInt());
            selectedRequest = selectRequest(index);
        }
        return selectedRequest;
    }

    public int getCorrectIndex(int userInputInt) {
        userInputInt = userInputInt - 1;
        return userInputInt;
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

    // logs each element in an array to the console
    static void displayElements(ArrayList<String> list) {
        list.forEach(item -> display(item));
    }


    private void selectRequestToBook() {
        userInteractions.userPrompt("\nWhat absence would you like to request?\n");
        display("1 - Holiday\n" + "2 - Sickness\n" + "3 - Lateness to work\n");
        int bookingSelectedOption = Integer.parseInt(checkAndUpdate.selection(userInteractions.scanner));
        switch (bookingSelectedOption) {
            case 1 -> bookHoliday();
            case 2 -> bookSickness();
            case 3 -> bookLateness();
        }
    }

    private void bookHoliday() {

        // sets all employee details.
        user.setEmployeeName();
        user.setEmployeeNumber();
        user.setStartDate();
        user.setEndDate();

        // returns a "y" for "yes" or "n" for "no".
        String areDatesCorrect = user.areEnteredDatesCorrect();

        // updates entered dates while they are incorrect.
        while (areDatesCorrect.equalsIgnoreCase("N")) {
            user.setStartDate();
            user.setEndDate();
            areDatesCorrect = user.areEnteredDatesCorrect();
        }

        // saves the request to the file.
        if (areDatesCorrect.equalsIgnoreCase("Y")) {
            Absence holidayRequest = new Holiday(user.getEmployeeName(), user.getEmployeeNumber(), user.getStartDate(), user.getEndDate());
            writer.save(holidayRequest.details());
            updateFile.reformatFile();
            statusReport("Details saved.");

        } else {
            statusReport("Invalid input.");
        }
    }

    private void bookLateness() {

        user.setEmployeeName();
        user.setEmployeeNumber();
        user.setDate();

        // only requires and checks one date (lateness shouldn't exceed more than 7.5 hours (a working day).
        String yesOrNo = user.getCorrectDate();

        // updates date if entered incorrectly.
        while (yesOrNo.equalsIgnoreCase("N")) {
            user.setDate();
            yesOrNo = user.getCorrectDate();
        }

        // collects the reason and hours late
        user.setReason();
        user.setHours();

        // Has the user input the correct hours? "yes" or "no".
        String isEntryCorrect = user.getCorrectHours();

        while (isEntryCorrect.equalsIgnoreCase("N")) {
            user.setHours();
            isEntryCorrect = user.getCorrectHours();
        }

        // if the correct details have been entered, a request object is used to save them to the file in string format.
        if (isEntryCorrect.equalsIgnoreCase("Y")) {
            Absence lateness = new Lateness(user.getEmployeeName(), user.getEmployeeNumber(), user.getDate(), user.getHours(), user.getReason());
            writer.save(lateness.details());
            updateFile.reformatFile();
            statusReport("Details saved.");

        } else {
            statusReport("Invalid input.");
        }
    }

    private void bookSickness() {

        // employee details are collected
        user.setEmployeeName();
        user.setEmployeeNumber();
        user.setStartDate();
        user.setEndDate();
        user.setReason();

        String areDatesCorrect = user.areEnteredDatesCorrect();

        // makes sure correct details are input. Loops back and asks user to enter them again if they are incorrect.
        while (areDatesCorrect.equalsIgnoreCase("N")) {
            user.setStartDate();
            user.setEndDate();
            areDatesCorrect = user.areEnteredDatesCorrect();
        }

        // saves details to the file.
        if (areDatesCorrect.equalsIgnoreCase("Y")) {
            Absence sickLeaveRequest = new SickLeave(user.getEmployeeName(), user.getEmployeeNumber(), user.getStartDate(), user.getEndDate(), user.getReason());
            writer.save(sickLeaveRequest.details().trim());
            updateFile.reformatFile();
            statusReport("Details saved.");

        } else {
            statusReport("Invalid input.");
        }
    }

    private void adminReviewRequests() {

        userInteractions.userPrompt("\nEnter admin password: \n");
        // gets password input from the user and checks this against required password.
        checkAndUpdate.login(userInteractions.scanner);
        statusReport("\nLogin successful.");

        display("\nSelect holiday to review:\n");
        // Gets the file content, puts it into an array list and adds number IDs
        ArrayList<String> fileContent = reader.getFileContent();
        displayElements(addNumberIDs(fileContent));

        // User selects a holiday request, and it is then displayed.

        int selectedHolidayOption = userInteractions.getUserInputInt();
        // makes sure the correct number is used for indexing into the array of absence requests
        // ie the first request is numbered '1', which corresponds to the first element in the array, indexed at 0
        selectedHolidayOption = selectedHolidayOption - 1;
        String selectedRequest = selectRequest(selectedHolidayOption);
        display("\nYou selected:\n");
        display(selectedRequest);

        // The option to approve or decline the request
        display("\n1 - Approve\n2 - Decline");
        // checkAndUpdate makes sure the selected option is valid and in range.
        int selectedApproveOrDecline = Integer.parseInt(checkAndUpdate.approveOrDeclineSelection(userInteractions.scanner));
        // The selected request is updated and displayed
        updateFile.updateRequestStatus(selectedHolidayOption, selectedApproveOrDecline);
        display("\nThe following request has been updated:\n");
        // indexes into the array containing each request and retrieves the selected one by the entered number.
        display(reader.getFileContent().get(selectedHolidayOption));
    }

    public void run() {

        userInteractions.userPrompt("\nSelect (1) or (2)\n\n 1 - Request absence\n " + "2 - Check request approval status\n " + "3 - Approve an absence request (admin only)\n");

        // gets input from the user, uses a regular expression tp check whether it's within the selection range
        int selectedOption = Integer.parseInt(checkAndUpdate.selection(userInteractions.scanner));

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