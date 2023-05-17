import java.util.Random;
public class MuscleFiber {
    private final int maxSize;
    public double fiberSize;

    public MuscleFiber() {
        maxSize = sproutMuscleFibers();
        fiberSize = regulateMuscleFibers();
        }
    private int sproutMuscleFibers(){
         /** create a normalized distribution of maximum muscle fiber sizes
         with median dependent on % of slow twitch fibers.
         */
         int size = 4;
         for (int i=0; i <20; i++) {
            Random r = new Random();
            int n = r.nextInt(100);
            if (n > Params.percentageOfSlowTwitchFibers){
                size ++;
            }
         }
         return size;
    }
    private double regulateMuscleFibers(){
        /** Create a unique starting fiber size.
         *  Simulate the body's natural limits on minimum and maximum fiber sizes.
         */
        Random r = new Random();
        double d = r.nextDouble() * 0.4;
        double sizeFiber = (0.2 + d) * maxSize;
        if (sizeFiber < 1 ){
            return 1;
        }
        if (sizeFiber > maxSize){
            return maxSize;
        }
        else {
            return sizeFiber;
        }
    }

    public static void main(String[] args) {
        new MuscleFiber();
    }
}
