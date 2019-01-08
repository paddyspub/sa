package speedup;

/***************************************************************

 Created by william.russell@viasat.com  on 12/6/18

 The current loop takes 6-7 seconds to complete.
 Without decreasing the MAX value iterations make changes to have this execute within 0-2 seconds.



 ***************************************************************/
public class SpeedUpTest {

    public static final Integer MAX = 2147483647;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();


        for (Integer i = 0; i < MAX; i++) {
        }

        long endTime = System.currentTimeMillis();

        System.out.println("total execution time: " + (endTime - startTime) / 1000 + "seconds.");

    }
}
