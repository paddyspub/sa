package hashnocrash;

import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
    public void testHashFuntionality() throws Exception {
        hashNoCrash.put("A", new Integer(1));
        Assert.assertEquals(hashNoCrash.get("A"), 1);
        hashNoCrash.put("AA", new Integer(11));
        Assert.assertEquals(hashNoCrash.get("AA"), 11);
        hashNoCrash.put("AAA", new Integer(111));
        Assert.assertEquals(hashNoCrash.get("AAA"), 111);

        hashNoCrash.put("B", new Integer(2));
        hashNoCrash.put("C", new Integer(3));
        Assert.assertEquals(hashNoCrash.get("B"), 2);
        Assert.assertEquals(hashNoCrash.get("C"), 3);
    }
}
