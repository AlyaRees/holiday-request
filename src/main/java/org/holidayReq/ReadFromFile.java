package org.holidayReq;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile {

    ArrayList<String> dates = new ArrayList<>();
    Scanner fileReader = null;

    public ArrayList<String> getFileContent() {
        try {
            File fileObject = new File("HolidayReq.txt");
            // The scanner reads from the file object
            fileReader = new Scanner(fileObject);
            while (fileReader.hasNextLine()) {
                dates.add(fileReader.nextLine().trim());
            }
            // The catch block is run if the file doesn't exist or is empty
        } catch (FileNotFoundException e) {
            App.statusReport("No holiday has been submitted.\n" + e);
            // close the file reader if it's been used.
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
        return dates;
    }

    public String getHolidayRequest(int index) {
        return getFileContent().get(index - 1);
    }
}