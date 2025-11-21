import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileHandling {

    static String fileName = "HolidayReq.txt";

    void saveDetails(String name, String number, String startDate, String endDate) {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write("Name: " + name + "\nEmployee Number: " + number + "\nDate: " + startDate + " " + endDate + "\n");
            fileWriter.close();
            App.statusReport("Successfully wrote to file.");
        } catch (IOException error) {
            App.statusReport("Error writing to file.");
            error.printStackTrace();
        }
    }

    void getRequestedHoliday() {

        File fileObject = new File(fileName);
        ArrayList<String> dates = new ArrayList<>();

        try (Scanner fileReader = new Scanner(fileObject)) {
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                if (data.contains("Date:")) {
                    data = data.replace("Date: ", "");
                    dates.add(data);
                }
            }
            // converts the dates arraylist to a stream
            Stream<String[]> allDates = dates.stream()
                    .map(line -> line.split(" "));

            allDates.forEach(line -> App.display("From " + line[0] + " to " + line[1] + " - PENDING APPROVAL" + "\n"));
            fileReader.close();

        } catch (FileNotFoundException error) {
            App.statusReport("No holiday has been submitted.\n");
        }
    }
}
