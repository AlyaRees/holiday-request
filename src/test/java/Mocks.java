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

    public void testReviewSelectionValidInput() {
        Validate validate = new Validate();
        Scanner mockScanner = mock(Scanner.class);
        int invalidInput = 12;
        int validInputOne = 1;
        int validInputTwo= 2;
        when(mockScanner.nextInt()).thenReturn(validInputOne);

        int result = validate.selection(mockScanner);

        verify(mockScanner, times(1)).nextInt();

        assertEquals(validInputOne, result);

    }

    @Test

    public void testReviewSelectionInvalidInput() {
        Validate validate = new Validate();
        Scanner mockScanner = mock(Scanner.class);
        int invalidInput = 12;
        int validInput = 1;

        when(mockScanner.nextInt())
                .thenReturn(invalidInput)
                .thenReturn(validInput);

        int result2 = validate.selection(mockScanner);

        verify(mockScanner, times(2)).nextInt();
        assertEquals(validInput, result2);
    }

    @Test

    public void testCheckAndUpdateWithValidInput() {

        // Employee numbers must be six digits long.

        Validate validate = new Validate();
        String sixDigitEmployeeNum = "112233";
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.next()).thenReturn(sixDigitEmployeeNum);

        String result = validate.employeeNumber(mockScanner);

        verify(mockScanner, times(1)).next();
        assertEquals(sixDigitEmployeeNum, result);

    }

    @Test

    public void testCheckAndUpdateWithInvalidInput() {

        Validate validate = new Validate();
        String sixDigitEmployeeNum = "112233";
        String invalidEmployeeNum = "-990023";
        Scanner mockScanner = mock(Scanner.class);

        when(mockScanner.next())
                .thenReturn(invalidEmployeeNum)
                .thenReturn(sixDigitEmployeeNum);

        String result = validate.employeeNumber(mockScanner);

        verify(mockScanner, times(2)).next();
        assertEquals(sixDigitEmployeeNum, result);

    }
}