package shape.shape;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import shape.Shape;

@Test
public class ShapeTest {

    @InjectMocks
    private Shape shape = new Shape();

    @BeforeMethod(alwaysRun = true)
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createDefaultShape() throws Exception {
        shape.createShape();
    }

    @Test
    public void createCustomSizedShape() throws Exception {
        shape.createShape(42);
    }

    @Test
    public void createSmallestAllowedShape() throws Exception {
        shape.createShape(5);
    }

    @Test
    public void createLargestAllowedShape() throws Exception {
        shape.createShape(80);
    }

    @Test()
    public void attemptToCreateShapeTooSmall()  {
        try {
            shape.createShape(3);
        }
        catch (Exception e){
            Assert.assertEquals(e.getMessage(), "Cannot create a shape smaller than 5x5");
        }
    }

    @Test()
    public void attemptToCreateShapeTooLarger()  {
        try {
            shape.createShape(81);
        }
        catch (Exception e){
            Assert.assertEquals(e.getMessage(), "Cannot create a shape larger than 80x80");
        }
    }
}
