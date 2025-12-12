import org.holidayReq.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AppTest {

    // deletes the file before and after each test, should it already exist to maintain good isolation between tests.
    @Before
    public void deleteFileBefore() {

        Path pathOfFile = Paths.get("HolidayReq.txt");
        try {
            Files.deleteIfExists(pathOfFile);
            // Catches an input/output exception should one occur. This indicates the failure or interruption of an input/output operation.
        } catch (IOException e) {
            app.statusReport("" + e);
        }
    }

    static App app = new App();

    @Test

    public void testLatenessRequest() {

        Absence latenessRequest = new Lateness("Homer Simpson", "112233", "11/11/1111", 2, "doctors appointment");
        WriteToFile writer = new WriteToFile();
        ReadFromFile reader = new ReadFromFile();

        writer.save(latenessRequest.fileContents());

        assertEquals("Request: Lateness - Name: Homer Simpson Employee Number: 112233 Date: 11/11/1111 Hours: 2.0 Reason: doctors appointment - PENDING APPROVAL", reader.getFileContent().get(0));
        assertEquals(1, reader.getFileContent().size());
    }

    @Test

    public void setUpForManualTesting() {
        WriteToFile writer = new WriteToFile();
        Holiday holidayRequest = new Holiday("Homer Simpson", "112233", "11/11/1111", "22/22/2222");
        Holiday holidayRequest2 = new Holiday("Jerry Smith", "556677", "11/11/1111", "22/22/2222");

        writer.save(holidayRequest.fileContents());
        writer.save(holidayRequest2.fileContents());


    }

    @Test

    public void testHolidayStatus() {
        UpdateFile updateFile = new UpdateFile();
        WriteToFile writer = new WriteToFile();
        ReadFromFile reader = new ReadFromFile();
        Holiday holidayRequest = new Holiday("Homer Simpson", "112233", "11/11/1111", "22/22/2222");
        Holiday holidayRequest2 = new Holiday("Jerry Smith", "556677", "11/11/1111", "22/22/2222");
        int approve = 1;
        int decline = 2;
        int secondEntry = 2;
        int firstEntry = 1;

        // getFileContent puts each line in the file into an array.
        // To begin with the file should be empty.
        assertEquals(0, reader.getFileContent().size());

        // one entry is saved to the file.
        writer.save(holidayRequest.fileContents());
        assertEquals(1, reader.getFileContent().size());
        // a second one.
        writer.save(holidayRequest2.fileContents());
        assertEquals(2, reader.getFileContent().size());

        ArrayList<String> result = reader.getFileContent();

        assertEquals("Request: Holiday - Name: Homer Simpson Employee Number: 112233 Date: 11/11/1111 22/22/2222 - PENDING APPROVAL", result.get(0));
        assertEquals("[Request: Holiday - Name: Homer Simpson Employee Number: 112233 Date: 11/11/1111 22/22/2222 - PENDING APPROVAL, Request: Holiday - Name: Jerry Smith Employee Number: 556677 Date: 11/11/1111 22/22/2222 - PENDING APPROVAL]", result.toString());

        // gets the correct number to index into the array
        // (ie, for the user 1 corresponds to the first element, but for accessing arrays 0 should be used to get the first element).
        firstEntry = firstEntry - 1;
        secondEntry = secondEntry - 1;

        // approve first entry
        updateFile.holidayStatus(firstEntry, approve);
        // decline second
        updateFile.holidayStatus(secondEntry, decline);
        assertEquals(2, result.size());

        assertEquals("Request: Holiday - Name: Homer Simpson Employee Number: 112233 Date: 11/11/1111 22/22/2222 - APPROVED", reader.getFileContent().get(firstEntry));
        assertEquals("Request: Holiday - Name: Jerry Smith Employee Number: 556677 Date: 11/11/1111 22/22/2222 - DECLINED", reader.getFileContent().get(secondEntry));

    }

    @Test

    public void testGetFileContent() {

        ReadFromFile reader = new ReadFromFile();
        WriteToFile writer = new WriteToFile();
        Holiday holidayRequest = new Holiday("Homer Simpson", "112233", "11/11/1111", "22/22/2222");
        Holiday holidayRequest2 = new Holiday("Jerry Smith", "556677", "11/11/1111", "22/22/2222");

        assertEquals(new ArrayList<String>(), reader.getFileContent());
        assertEquals(0, reader.getFileContent().size());

        // writes to a new file
        writer.save(holidayRequest.fileContents());
        assertEquals(1, reader.getFileContent().size());
        writer.save(holidayRequest2.fileContents());
        //assertEquals(2, reader.getFileContent().size());

        // gets the information from the file and puts it in an ArrayList

        // checks the information added earlier is in the file
        assertEquals("Request: Holiday - Name: Homer Simpson Employee Number: 112233 Date: 11/11/1111 22/22/2222 - PENDING APPROVAL", reader.getFileContent().get(0));
        assertEquals("[Request: Holiday - Name: Homer Simpson Employee Number: 112233 Date: 11/11/1111 22/22/2222 - PENDING APPROVAL," +
                " Request: Holiday - Name: Jerry Smith Employee Number: 556677 Date: 11/11/1111 22/22/2222 - PENDING APPROVAL]", reader.getFileContent().toString());
        assertEquals(2, reader.getFileContent().size());

    }

    @Test

    public void testAddNumberIDs() {

        ArrayList<String> testList = new ArrayList<>();

        // Arrange
        testList.add("Name: Alya Rees Employee Number: 332244 Date: 11/11/1111 22/22/2222 - PENDING APPROVAL");
        testList.add("Name: Homer Simpson Employee Number: 112233 Date: 11/11/1111 22/22/2222 - DECLINED");
        testList.add("Name: Jackie Chan Employee Number: 445566 Date: 22/12/2026 22/22/2222 - APPROVED");

        // Pre-conditions - all entries must be inside testList before adding their IDs
        assertEquals("Name: Alya Rees Employee Number: 332244 Date: 11/11/1111 22/22/2222 - PENDING APPROVAL", testList.get(0));
        assertEquals("Name: Homer Simpson Employee Number: 112233 Date: 11/11/1111 22/22/2222 - DECLINED", testList.get(1));
        assertEquals("Name: Jackie Chan Employee Number: 445566 Date: 22/12/2026 22/22/2222 - APPROVED", testList.get(2));

        // Act
        app.addNumberIDs(testList);

        // Assert
        assertEquals("1 - Name: Alya Rees Employee Number: 332244 Date: 11/11/1111 22/22/2222 - PENDING APPROVAL", testList.get(0));
        assertEquals("2 - Name: Homer Simpson Employee Number: 112233 Date: 11/11/1111 22/22/2222 - DECLINED", testList.get(1));
        assertEquals("3 - Name: Jackie Chan Employee Number: 445566 Date: 22/12/2026 22/22/2222 - APPROVED", testList.get(2));

    }
}
