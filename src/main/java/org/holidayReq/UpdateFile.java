package org.holidayReq;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class UpdateFile extends ReadFromFile {

    String approveOrDecline(int userInput) {
        String adminResponse = "";
        switch (userInput) {
            case 1 -> adminResponse = "- APPROVED";
            case 2 -> adminResponse = "- DECLINED";
            default -> {
                App.display("\nInvalid input. Please try again.\n");
            }
        }
        return adminResponse;
    }

    public void reformatFile() {
        ArrayList<String> formattedLines = new ArrayList<>();
        // read from file
        getFileContent()
                .forEach(request -> formattedLines.add(request.replaceAll("\\BName", "\nName")));
        try {
            FileWriter fileWriter = new FileWriter("HolidayReq.txt", false);
            fileWriter.write(fileContentToString(formattedLines));
            fileWriter.close();
        } catch (IOException error) {
            App.statusReport("Error writing to file.");
        }
    }

    public void holidayStatus(int selectedHolidayOption, int approveOrDeclineOption) {
        ArrayList<String> fileContent = getFileContent();
        String selectedRequest = fileContent.get(selectedHolidayOption).trim();
        // must use replaceAll when using regex to match patterns
        String updatedRequest = selectedRequest.replaceAll("-\\s\\w+(?:\\s\\w+)?$", approveOrDecline(approveOrDeclineOption)).trim();
        String fileContentString = fileContentToString(fileContent);
        String updatedFileContent = fileContentString.replace(selectedRequest, updatedRequest);
        try {
            FileWriter fileWriter = new FileWriter("HolidayReq.txt", false);
            fileWriter.write(updatedFileContent);
            fileWriter.close();
        } catch (IOException error) {
            App.statusReport("Error writing to file.");
        }
    }

    String fileContentToString(ArrayList<String> fileContent) {
        return fileContent.stream()
                .reduce("", (curr, next) -> curr.trim().concat("\n" + next.trim()));
    }
}