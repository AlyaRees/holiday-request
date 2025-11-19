import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.*;
import org.junit.Test;
import java.util.Scanner;

public class AppTest {

    @Test

    public void testIsValidFormat() {

        App app = new App();
        
        assertTrue(app.isValidFormat("04/11/2025"));
        assertTrue(app.isValidFormat("27/12/2025"));
        assertFalse(app.isValidFormat("hey"));

    }
}