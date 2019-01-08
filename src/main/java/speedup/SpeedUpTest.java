package speedup;

/***************************************************************

 Created by william.russell@viasat.com  on 12/6/18

 The current loop takes 6-7 seconds to complete.
 Without decreasing the MAX value iterations make changes to have this execute within 0-2 seconds.



 ***************************************************************/
public class SpeedUpTest {
    // use a primitive int instead of Integer, no need for an Integer object in this class
    public static final int MAX = 2147483647;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // use the primitive int so you dont have the overhead of creating an Integer object and doing
        // comparison to an Integer object for MAX, which has also been changed to the primitive int
        for (int i = 0; i < MAX; i++) {
        }

        long endTime = System.currentTimeMillis();

        System.out.println("total execution time: " + (endTime - startTime) / 1000 + "seconds.");

    }
}
