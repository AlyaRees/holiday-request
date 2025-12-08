package org.holidayReq;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class HandlesFile {
    protected String fileName = "HolidayReq.txt";
}

class UpdateFile extends ReadFromFile {

    public void reformatFile() {
        ArrayList<String> formattedLines = new ArrayList<>();
        // Instantiating a class inside its child... suspicious...
        ReadFromFile fileReader = new ReadFromFile();
        // read from file
        fileReader.getFileContent()
                .forEach(request -> formattedLines.add(request.replaceAll("\\BName", "\nName")));
        try {
            FileWriter fileWriter = new FileWriter(fileName, false);
            fileWriter.write(updatedFileContent(formattedLines));
            fileWriter.close();
        } catch (IOException error) {
            App.statusReport("Error writing to file.");
        }
    }

    void updateHolidayStatus(int selectedOption, int userSelection) {

        HolidayInteractions holidayInteraction = new HolidayInteractions();
        // User selects holiday request by its index.
        // Holiday requests are read from the file and put into an array and indexed into.
        // Makes more sense to turn file contents into an array, add the placement numbers for each request and use an ID key value pair and array.get(ID)? - No. This will cause problems further down the line. When adding new entries, the file will need to be read from first and then the correct number given for that new entry based on the pre-existing ones. Array already allows access to array elements using numbers with indexing, there's no need to add it again.
        String selectedRequest = App.getHolidayRequest(selectedOption);
        // Reads from file, turns contents into an arraylist and then converts this into a string.
        String fileContent = updatedFileContent(getFileContent());

        // replaces old approval status (at the end of the line after the '-') with new one based on the users selection
        String updatedLine = selectedRequest.replaceAll("-\\s\\w+(?:\\s\\w+)?$", holidayInteraction.approveOrDecline(userSelection));
        // Replace the originally selected request with the newly updated one in the file contents
        String updatedContent = fileContent.replaceAll(selectedRequest, updatedLine);

        try {
            FileWriter fileWriter = new FileWriter(fileName, false);
            // Write the newly updated contents to the file.
            fileWriter.write(updatedContent + "\n");
            fileWriter.close();
        } catch (IOException error) {
            App.statusReport("Error writing to file.");
        }
    }

    String updatedFileContent(ArrayList<String> fileContent) {
        return String.join("\n", fileContent);
    }
}