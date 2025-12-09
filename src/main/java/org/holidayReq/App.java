package org.holidayReq;

import java.util.ArrayList;

public class App {

    UserInteractions userInteractions = new UserInteractions();
    ReadFromFile reader = new ReadFromFile();
    WriteToFile writer = new WriteToFile();
    UpdateFile updateFile = new UpdateFile();
    Validate validate = new Validate();

    // better to use error handling here rather than logic that handles invalid input
    // logic -> conditionals and loops
    // I used -> error handling and recursion
    // logic -> if index != 0 OR index > number of entries OR index < number of entries, handle error and repeat process. What if user enters a minus number? Try-catch handles this. This would be harder to achieve with logic.

    private String selectHoliday(int index) {
        String selectedRequest = "";
        try {
            selectedRequest = reader.getHolidayRequest(index);
        } catch (Exception e) {
            statusReport("Please select from the provided options. " + e);
            index = userInteractions.getUserInputInt();
            selectHoliday(index);
        }
        return selectedRequest;
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
        list.forEach(item -> display(item));
    }

    // private access modifiers are used for these option interaction methods on  in the App class as they will only be called within this class.
    private void optionOneInteraction() {

        userInteractions.userPrompt("\nEnter your full name:\n");
        String userFullName = userInteractions.getUserInputStr();

        userInteractions.userPrompt("\nEnter your employee number:\n");

        // check and update employee number.
        String employeeNum = validate.employeeNumber(userInteractions.customScanner);

        userInteractions.userPrompt("\nEnter holiday you want to book:\n(Use the format DD/MM/YYYY)\n\nDate from:\n");

        String startDate = validate.date(userInteractions.customScanner);

        userInteractions.userPrompt("\nDate to:\n");
        String endDate = validate.date(userInteractions.customScanner);

        userInteractions.userPrompt("\nYou want to book from: " + startDate + " to " + endDate + "\nCorrect? (Y/N)\n");
        String areDatesCorrect = userInteractions.getUserInputStr();

        while (areDatesCorrect.equalsIgnoreCase("N")) {
            userInteractions.userPrompt("\nEnter holiday you want to book:\n(Use the format DD/M/YYYY)\n\nDate from:\n");
            startDate = userInteractions.getUserInputStr();

            userInteractions.userPrompt("\nDate to:\n");
            endDate = userInteractions.getUserInputStr();
            userInteractions.userPrompt("\nYou want to book from: " + startDate + " to " + endDate + "\nCorrect? (Y/N)\n");

            areDatesCorrect = userInteractions.getUserInputStr();
        }

        if (areDatesCorrect.equalsIgnoreCase("Y")) {
            HolidayRequest request = new HolidayRequest(userFullName, employeeNum, startDate, endDate);
            writer.save(request.fileContents());
            updateFile.reformatFile();
            statusReport("Details saved.");

        } else {
            statusReport("Invalid input.");
        }
    }

    private void optionThreeInteraction() {

        userInteractions.userPrompt("\nEnter admin password: \n");
        validate.login(userInteractions.customScanner);
        statusReport("\nLogin successful.");

        display("\nSelect holiday to review:\n");
        // Gets the file content, puts it into an array list and adds number IDs
        ArrayList<String> fileContent = reader.getFileContent();
        displayElements(addNumberIDs(fileContent));

        // User selects a holiday request, and it is then displayed.
        int requestIndex = userInteractions.getUserInputInt();
        String selectedRequest = selectHoliday(requestIndex);
        display("\nYou selected:\n");
        display(selectedRequest);

        // The option to approve or decline the request
        display("\n1 - Approve\n2 - Decline");
        int selectedApproveOrDecline = validate.selection(userInteractions.customScanner);
        // The selected request is updated and displayed
        updateFile.updateHolidayStatus(selectedApproveOrDecline, requestIndex);
        display("\nThe following request has been updated:\n");
        display(reader.getHolidayRequest(selectedApproveOrDecline));
    }

    public void run() {

        userInteractions.userPrompt("\nSelect (1) or (2)\n\n 1 - Book holiday\n " +
                "2 - Check holiday approval status\n " +
                "3 - Approve holiday (admin only)\n");

        // The switch statement tightens the constraints for user input (the choice should only be 1, 2 or 3)

        switch (userInteractions.getUserInputInt()) {
            case 1 -> optionOneInteraction();
            case 2 -> {
                statusReport("\nHoliday approval status:\n");
                displayElements(reader.getFileContent());
            }
            case 3 -> optionThreeInteraction();
            default -> {
                statusReport("\nPlease select a valid option.");
                run();
            }
        }
        userInteractions.closeScanner();

        // userInteractions.customScanner.next(); -> should throw an error because we're trying to use the scanner after closing it!
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