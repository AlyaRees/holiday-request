import org.holidayReq.ReadFromFile;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Mocks {

    @Mock
    ReadFromFile reader;

    @Test
    public void testGetFileContent() {

        // mock the file object?
    }

}
