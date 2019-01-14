package anagram;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import anagram.Anagram;

@Test
public class AnagramTest {

    @InjectMocks
    private Anagram anagram = new Anagram();

    @BeforeMethod(alwaysRun = true)
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidAnagram() {
       Assert.assertTrue(anagram.isAnagram("racecar", "RaCeCaR"));
       Assert.assertTrue(anagram.isAnagram("abba", "abba"));
       Assert.assertTrue(anagram.isAnagram("a", "a"));
    }


    @Test
    public void testValidNonAnagrams() {
        Assert.assertFalse(anagram.isAnagram("racecar", "car"));
        Assert.assertFalse(anagram.isAnagram("abba", "aba"));
        Assert.assertFalse(anagram.isAnagram("a", "b"));
        Assert.assertFalse(anagram.isAnagram("a", ""));
        Assert.assertFalse(anagram.isAnagram("", "a"));
        Assert.assertFalse(anagram.isAnagram("", ""));
        Assert.assertFalse(anagram.isAnagram("abba", "racecar"));
    }

}
