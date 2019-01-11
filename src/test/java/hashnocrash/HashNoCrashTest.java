package hashnocrash;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test
public class HashNoCrashTest {

    @InjectMocks
    private HashNoCrash hashNoCrash = new HashNoCrash(4);

    @BeforeMethod(alwaysRun = true)
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCollisions() throws Exception {
        hashNoCrash.put("A", null);
        hashNoCrash.put("B", null);
        hashNoCrash.put("C", null);
        hashNoCrash.put("D", null);
        hashNoCrash.put("E", null);
    }
}
