package garagedoor;

/***************************************************************

 Created by william.russell@viasat.com  on 12/6/18

 See package-info.java.
 ***************************************************************/
public class GarageDoorFSM {
    public enum State {
        CLOSED, OPENED, CLOSING, OPENING, ERROR
    }

    private static State currentState = State.CLOSED;
    public static void init () {
        currentState = State.CLOSED;
    }
    public static void openDoor () {
        if (currentState == State.OPENING || currentState == State.CLOSED) {
            currentState = State.OPENING;
        }
        if (currentState == State.CLOSING) {
            currentState = State.ERROR;
        }
    }
    public static void closeDoor () {
        if (currentState == State.CLOSING || currentState == State.OPENED) {
            currentState = State.CLOSING;
        }
        if (currentState == State.OPENING) {
            currentState = State.ERROR;
        }
    }
    public static void main(String[] args) {
        init();
        openDoor();
        closeDoor();
    }
}
