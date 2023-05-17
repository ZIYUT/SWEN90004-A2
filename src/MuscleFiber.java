import java.util.Random;
public class MuscleFiber {
    private int maxSize = 4;
    private double fiberSize;
    public MuscleFiber() {
        sproutMuscleFibers();
        Random r = new Random();
        double d = r.nextDouble() * 0.4;

        fiberSize = (0.2 + d) * maxSize;
        }
    private void sproutMuscleFibers(){
         /** create a normalized distribution of maximum muscle fiber sizes
         with median dependent on % of slow twitch fibers.
         */
        for (int i=0; i <20; i++) {
            Random r = new Random();
            int n = r.nextInt(100);
            if (n > Params.percentageOfSlowTwitchFibers){
                maxSize ++;
            }
        }
    }
    private void regulateMuscleFibers(){
        /** Create a unique starting fiber size.
         *  Simulate the body's natural limits on minimum and maximum fiber sizes.
         */
        Random r = new Random();
        double d = r.nextDouble() * 0.4;

    }

    public static void main(String[] args) {

    }
}
