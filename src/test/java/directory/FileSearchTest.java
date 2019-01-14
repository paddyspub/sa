package directory;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


@Test
public class FileSearchTest {

    @InjectMocks
    private FileSearch fileSearch = new FileSearch();

    @BeforeMethod(alwaysRun = true)
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchForFilesByNames() {
        List<String> expectedFiles = new ArrayList<>();
        expectedFiles.add("./src/main/java/directory/dir2/subdir1/subdir1/subdir1/image.png");
        expectedFiles.add("./src/main/java/directory/dir1/subdir1/image2.png");
        Assert.assertEquals(fileSearch.findFiles("image"), expectedFiles);

        expectedFiles = new ArrayList<>();
        expectedFiles.add("./src/main/java/directory/dir2/subdir1/subdir2/text.txt");
        expectedFiles.add("./src/main/java/directory/dir1/subdir2/text.txt");
        Assert.assertEquals(fileSearch.findFiles("text"), expectedFiles);
    }

    @Test
    public void testSearchForFilesByExtention() {
        List<String> expectedFiles = new ArrayList<>();
        expectedFiles.add("./src/main/java/directory/dir2/subdir1/subdir1/subdir1/image.png");
        expectedFiles.add("./src/main/java/directory/dir1/subdir1/image2.png");
        Assert.assertEquals(fileSearch.findFiles(".png"), expectedFiles);

        expectedFiles = new ArrayList<>();
        expectedFiles.add("./src/main/java/directory/dir2/subdir1/subdir2/text.txt");
        expectedFiles.add("./src/main/java/directory/dir1/subdir2/text.txt");
        Assert.assertEquals(fileSearch.findFiles(".txt"), expectedFiles);
    }

}
