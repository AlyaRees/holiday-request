import org.holidayReq.UserInteractions;
import org.holidayReq.Validate;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Mocks {

    @Test
    public void testGetFileContent() {

        // mock the file object?
    }

    @Test

    public void testCheckAndUpdateWithValidInput() {

        // Employee numbers must be six digits long.

        Validate validate = new Validate();
        String sixDigitEmployeeNum = "112233";
        String invalidEmployeeNum = "-990023";
        String invalidInput = "pretamanger";
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.next()).thenReturn(sixDigitEmployeeNum);

        String result = validate.checkAndUpdate(mockScanner);

        verify(mockScanner, times(1)).next();
        assertEquals(sixDigitEmployeeNum, result);

    }

    @Test

    public void testCheckAndUpdateWithInvalidInput() {

        Validate validate = new Validate();
        String sixDigitEmployeeNum = "112233";
        String invalidEmployeeNum = "-990023";
        String invalidInput = "pretamanger";

        // Store original standard input for later restoration
        InputStream originalIn = System.in;

        // call with invalid input
        try {
            // Programmatical simulation of user input
            ByteArrayInputStream in = new ByteArrayInputStream(sixDigitEmployeeNum.getBytes());
            System.setIn(in);

            // redirect standard input to use simulated input
            validate.checkAndUpdate(new Scanner(System.in));


        } finally {
            // Restore original standard input
            System.setIn(originalIn);
        }
        // getUserInput needs to return valid input this second time around

    }
}