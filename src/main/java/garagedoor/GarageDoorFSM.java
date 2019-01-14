package garagedoor;

/***************************************************************

 Created by william.russell@viasat.com  on 12/6/18

 See package-info.java.
 ***************************************************************/
public class GarageDoorFSM {
    public enum State {
        CLOSED, OPENED
    }

    private State currentState = State.CLOSED;

    public GarageDoorFSM() {
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void init() {
        currentState = State.CLOSED;
    }

    public void openDoor() {
        currentState = State.OPENED;
    }

    public void closeDoor() {
        currentState = State.CLOSED;
    }

    public void main(String[] args) {
        init();
        openDoor();
        closeDoor();
    }
}
