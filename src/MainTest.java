import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.*;
import org.junit.Test;

public class MainTest {

    @Test

    public void testIsValidFormat() {
        
        assertTrue(Main.isValidFormat("04/11/25"));
        assertTrue(Main.isValidFormat("27/12/25"));
        assertFalse(Main.isValidFormat("hey"));

    }

    @Test

    // Tests that inputted date has been entered in the correct format
    public void testCheckAndUpdateDate() {

        // Store original standard input for later restoration
        InputStream originalIn = System.in;

        try {
            
            // Programmatical simulation of user input
            String userInput = "04/01/26";
            ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());

            // redirect standard input to use simulated input
            System.setIn(in);
            String result = Main.checkAndUpdateDate("\nDate from\n", "04/01/26");

            assertEquals("04/01/26", result);

        } finally {
            // Restore original standard input
            System.setIn(originalIn);
        }
    }
}
