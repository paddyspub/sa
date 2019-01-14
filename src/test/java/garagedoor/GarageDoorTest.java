package garagedoor;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



@Test
public class GarageDoorTest {

    @InjectMocks
    private GarageDoorFSM garageDoorFSM = new GarageDoorFSM();

    @BeforeMethod(alwaysRun = true)
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void runGarageDoorFSM() {
        garageDoorFSM.init();
        Assert.assertEquals(garageDoorFSM.getCurrentState(), GarageDoorFSM.State.CLOSED);

        garageDoorFSM.openDoor();
        Assert.assertEquals(garageDoorFSM.getCurrentState(), GarageDoorFSM.State.OPENED);

        garageDoorFSM.closeDoor();
        Assert.assertEquals(garageDoorFSM.getCurrentState(), GarageDoorFSM.State.CLOSED);
    }
}
