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

    Validate validate = new Validate();
    String sixDigitEmployeeNum = "112233";
    String invalidEmployeeNum = "-990023";
    String invalidInput = "pretamanger";

    @Test
    public void testGetFileContent() {

        // mock the file object?
    }

    @Test

    public void testCheckAndUpdateWithValidInput() {

        // Don't mock validateFormat. We're testing how this behaves with the while loop.
        // Only mock getUserInput to return valid or invalid entries (depending on the tests).

        UserInteractions mockUserInteractions = mock(UserInteractions.class);
        UserInteractions userInteractions = new UserInteractions();
        Validate mockValidate = mock(Validate.class);

        // Employee numbers must be six digits long.

        // call with valid input
        validate.checkAndUpdate(sixDigitEmployeeNum, new Scanner(System.in));

        // expect userPrompt to not be called
        verify(mockUserInteractions, times(0)).getUserInputStr();

    }

    @Test

    public void testCheckAndUpdateWithInvalidInput() {

        // Store original standard input for later restoration
        InputStream originalIn = System.in;

        // call with invalid input
        try {

            // Programmatical simulation of user input
            ByteArrayInputStream in = new ByteArrayInputStream(invalidEmployeeNum.getBytes());

            // redirect standard input to use simulated input
            System.setIn(in);
            String result = validate.checkAndUpdate(invalidEmployeeNum, new Scanner(System.in));

        } finally {
            // Restore original standard input
            System.setIn(originalIn);
        }
        // getUserInput needs to return valid input this second time around

    }
}