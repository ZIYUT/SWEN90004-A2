import java.util.Random;
public class MuscleFiber {
    private final int maxSize;
    public double fiberSize;

    public MuscleFiber() {
        maxSize = sproutMuscleFibers();
        Random r = new Random();
        double d = r.nextDouble() * 0.4;
        fiberSize = (0.2 + d) * maxSize;
        regulateMuscleFibers();
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
    public void regulateMuscleFibers(){
        /** Create a unique starting fiber size.
         *  Simulate the body's natural limits on minimum and maximum fiber sizes.
         */
        if (this.fiberSize < 1 ){
            this.fiberSize = 1;
        }
        if (this.fiberSize > this.maxSize){
            this.fiberSize =  this.maxSize;
        }
    }
}
