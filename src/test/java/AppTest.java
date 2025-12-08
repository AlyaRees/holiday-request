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

    App app = new App();
    Path pathOfFile = Paths.get("HolidayReq.txt");

    // deletes the file before each test, should it already exist
    @Before
    public void deleteFile() {
        try {
            Files.deleteIfExists(pathOfFile);
            // Catches an input/output exception should one occur. This indicates the failure or interruption of an input/output operation.
        } catch (IOException e) {
            app.statusReport("" + e);
        }
    }

    @Test

    public void testGetFileContent() {

        ReadFromFile reader = new ReadFromFile();
        WriteToFile writer = new WriteToFile();
        HolidayRequest request = new HolidayRequest("Homer Simpson", "112233", "11/11/1111", "22/22/2222");
        HolidayRequest request2 = new HolidayRequest("Jerry Smith", "556677", "11/11/1111", "22/22/2222");

        assertEquals(new ArrayList<String>(), reader.getFileContent());

        // writes to a new file
        writer.save(request);
        writer.save(request2);

        // gets the information from the file and puts it in an ArrayList
        ArrayList<String> result = reader.getFileContent();

        // checks the information added earlier is in the file
        assertEquals("Name: Homer Simpson Employee Number: 112233 Date: 11/11/1111 22/22/2222 - PENDING APPROVAL", result.get(0));
        assertEquals("[Name: Homer Simpson Employee Number: 112233 Date: 11/11/1111 22/22/2222 - PENDING APPROVAL," +
                " Name: Jerry Smith Employee Number: 556677 Date: 11/11/1111 22/22/2222 - PENDING APPROVAL]", result.toString());

    }

    @Test

    public void testDateValidation() {

        HandleValidation dateHandle = new HandleValidation();
        HandleValidation.Date date = dateHandle.new Date();

        assertTrue(date.isValid("04/11/2025"));
        assertTrue(date.isValid("27/12/2025"));
        assertFalse(date.isValid("hey"));

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

    @Test

    public void testEmployeeNumberValidation() {

        HandleValidation validate = new HandleValidation();
        HandleValidation.EmployeeNumber employeeNumber = validate.new EmployeeNumber();

        String sixDigitEmployeeNum = "112233";
        String invalidEmployeeNumShort = "12";
        String invalidEmployeeNumLong = "0234560";
        String invalidEmployeeNum = "-990023";
        String invalidInput = "pretamanger";

        assertTrue(employeeNumber.isValid(sixDigitEmployeeNum));
        assertFalse(employeeNumber.isValid(invalidEmployeeNumShort));
        assertFalse(employeeNumber.isValid(invalidEmployeeNumLong));
        assertFalse(employeeNumber.isValid(invalidEmployeeNum));
        assertFalse(employeeNumber.isValid(invalidInput));
    }
}