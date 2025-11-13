import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.*;
import org.junit.Test;

public class MainTest {

    @Test

    public void testIsValidFormat() {
        
        assertTrue(App.isValidFormat("04/11/2025"));
        assertTrue(App.isValidFormat("27/12/2025"));
        assertFalse(App.isValidFormat("hey"));

    }

    @Test

    // Tests that inputted date has been entered in the correct format
    public void testCheckAndUpdateDate() {

        // Store original standard input for later restoration
        InputStream originalIn = System.in;

        try {
            
            // Programmatical simulation of user input
            String userInput = "04/01/2026";
            ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());

            // redirect standard input to use simulated input
            System.setIn(in);
            String result = App.checkAndUpdateDate("\nDate from\n", "04/01/2026");

            assertEquals("04/01/2026", result);

        } finally {
            // Restore original standard input
            System.setIn(originalIn);
        }
    }
}
