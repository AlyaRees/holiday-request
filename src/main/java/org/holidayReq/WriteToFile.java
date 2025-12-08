package org.holidayReq;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile extends HandlesFile {

    public void save(HolidayRequest request) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write(request.fileContents());
            fileWriter.close();
        } catch (IOException error) {
            App.statusReport("Error writing to file.");
        }
    }
}