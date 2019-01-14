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
    private HashNoCrash hashNoCrash = new HashNoCrash();

    @BeforeMethod(alwaysRun = true)
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHashFuntionality() throws Exception {
        Assert.assertEquals(hashNoCrash.put("A", 1), true);
        Assert.assertEquals(hashNoCrash.get("A"), 1);
        Assert.assertEquals(hashNoCrash.put("AA", new Integer(11)), true);
        Assert.assertEquals(hashNoCrash.get("AA"), 11);
        Assert.assertEquals(hashNoCrash.put("AAA", 'a'), true);
        Assert.assertEquals(hashNoCrash.get("AAA"), 'a');

        Assert.assertEquals(hashNoCrash.put("B", "TEST B"), true);
        Assert.assertEquals(hashNoCrash.put("C", null), true);
        Assert.assertEquals(hashNoCrash.put("CC", "TEST C"), true);

        Assert.assertEquals(hashNoCrash.get("B"), "TEST B");
        Assert.assertEquals(hashNoCrash.get("C"), null);
        Assert.assertEquals(hashNoCrash.get("CC"), "TEST C");

        Assert.assertEquals(hashNoCrash.delete("ABC"), false);
        Assert.assertEquals(hashNoCrash.containsKey("CC"), true);
        Assert.assertEquals(hashNoCrash.delete("CC"), true);
        Assert.assertEquals(hashNoCrash.containsKey("CC"), false);

        Assert.assertEquals(hashNoCrash.put("CC", "TEST NEW C"), true);
        Assert.assertEquals(hashNoCrash.containsKey("CC"), true);
        Assert.assertEquals(hashNoCrash.put("ZZZZZZZZ", "TEST key the produces largest hash"), true);
    }

    @Test
    public void testExceptions () {
        try {
            hashNoCrash.put("", 1);
        }
        catch (Exception e) {
            Assert.assertEquals (e.getMessage(), "Key must be 1-8 characters");
        }
        try {
            hashNoCrash.put("123456789", 1);
        }
        catch (Exception e) {
            Assert.assertEquals (e.getMessage(), "Key must be 1-8 characters");
        }
    }
}
