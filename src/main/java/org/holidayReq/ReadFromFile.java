package org.holidayReq;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile {

    public ArrayList<String> getFileContent() {

        ArrayList<String> dates = new ArrayList<>();

        try (BufferedReader bufferReader = new BufferedReader(
                new FileReader("HolidayReq.txt")
        )) {
            while (bufferReader.ready()) {
                dates.add(bufferReader.readLine());
            }
        }
        // The catch block is run if the file doesn't exist or is empty
        catch (IOException e) {
            App.statusReport("No holiday has been submitted.\n" + e);
        }
        return dates;
    }
}