import org.holidayReq.*;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Mocks {

    @Test

    public void testReviewSelection() {

        // selection should be limited to a 1 - approve or 2 - decline

        CheckAndUpdate checkAndUpdate = new CheckAndUpdate();
        Scanner mockScanner = mock(Scanner.class);
        int invalidInput = 12;
        int validInput = 1;

        when(mockScanner.nextInt())
                .thenReturn(invalidInput)
                .thenReturn(validInput);

        int result2 = checkAndUpdate.selectionInt(mockScanner);

        verify(mockScanner, times(2)).nextInt();
        assertEquals(validInput, result2);
    }

    @Test

    public void testCheckAndUpdateEmployeeNumberValidInput() {

        // Employee numbers must be six digits long.

        CheckAndUpdate checkAndUpdate = new CheckAndUpdate();
        String sixDigitEmployeeNum = "112233";
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.next()).thenReturn(sixDigitEmployeeNum);

        String result = checkAndUpdate.employeeNumber(mockScanner);

        verify(mockScanner, times(1)).next();
        assertEquals(sixDigitEmployeeNum, result);

    }

    @Test

    public void testCheckAndUpdateEmployeeNumberInvalidInput() {

        CheckAndUpdate checkAndUpdate = new CheckAndUpdate();
        String sixDigitEmployeeNum = "112233";
        String invalidEmployeeNum = "-990023";
        Scanner mockScanner = mock(Scanner.class);

        when(mockScanner.next())
                .thenReturn(invalidEmployeeNum)
                .thenReturn(sixDigitEmployeeNum);

        String result = checkAndUpdate.employeeNumber(mockScanner);

        verify(mockScanner, times(2)).next();
        assertEquals(sixDigitEmployeeNum, result);

    }

}