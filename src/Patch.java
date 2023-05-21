import java.lang.invoke.MutableCallSite;
import java.util.List;

import java.util.*;
public class Patch {
    public MuscleFiber fiber;
    public int coordinateX;
    public int coordinateY;
    public double anabolicHormone = 50;
    public double catabolicHormone = 52;
    public Patch(int coordinateX, int coordinateY,MuscleFiber fiber) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.fiber = fiber;
    }
    public void performDailyActivity() {
        /**
         * Simulate hormonal effect of lifestyle with the daily activities
         * such as working and sleeping
         */
        this.catabolicHormone = this.catabolicHormone + 2.0 * log(this.fiber.fiberSize, 10);
        this.anabolicHormone = this.anabolicHormone + 2.5 * log(this.fiber.fiberSize,10);

    }
    public void sleep(){
        /**
         * Simulate the hormonal effect of sleeping
         */
        this.catabolicHormone = this.catabolicHormone - 0.5 * log(this.catabolicHormone, 10) * Params.sleepHours;
        this.anabolicHormone = this.anabolicHormone - 0.48 * log(this.anabolicHormone,10) * Params.sleepHours;
    }

    public void developMuscle(){
        /**
         *  Catabolic hormones must prepare the fibers for growth before the
         *  anabolic hormones may add mass to the fibers
         */
        this.fiber.fiberSize = (this.fiber.fiberSize - 0.2 * log(this.catabolicHormone, 10));
        if(log(this.anabolicHormone, 10)<=(1.05*log(this.catabolicHormone, 10))){
            this.fiber.fiberSize = (this.fiber.fiberSize + 0.2 * log(this.anabolicHormone, 10));
        }
        else {
            this.fiber.fiberSize = (this.fiber.fiberSize + 0.2 * 1.05* log(this.catabolicHormone, 10));
        }
        this.fiber.regulateMuscleFibers();
    }

    public void liftWeight(){
        /**
         * Simulate hormonal effect of weight lifting
         */
        Random r = new Random();
        int n = r.nextInt(10000);
        if(n<(Params.intensity*Params.intensity)){
            this.anabolicHormone = this.anabolicHormone + log(this.fiber.fiberSize, 10)*55;
            this.catabolicHormone = this.catabolicHormone + log(this.fiber.fiberSize, 10) *44;
        }
    }
    public void regulateHormones(){
        /**
         *   Hormones diffuse to neighboring fibers
         *   if there are to many or to few hormones in an area,
         *   the body will try very hard to restore a balance
         */
        List<Patch> neighbours = getNeighbours(this.coordinateX,this.coordinateY);
        diffuse(neighbours);

        // Set the anabolicHormone and catabolicHormone values in the range of max and min values.
        if (this.anabolicHormone > Params.anabolicHormoneMax){
            this.anabolicHormone = Params.anabolicHormoneMax;
        }
        if (this.anabolicHormone < Params.anabolicHormoneMin){
            this.anabolicHormone = Params.anabolicHormoneMin;
        }
        if (this.catabolicHormone > Params.catabolicHormoneMax){
            this.catabolicHormone = Params.catabolicHormoneMax;
        }
        if (this.catabolicHormone < Params.catabolicHormoneMin){
            this.catabolicHormone = Params.catabolicHormoneMin;
        }
    }

    public static double log(double value, double base) {
        /**
         *  Log algorithm method
         */
        return Math.log(value) / Math.log(base);
    }
    public void diffuse(List<Patch> neighbours) {
        double oldAnabolic = this.anabolicHormone;
        double oldCatabolic = this.catabolicHormone;
        System.out.println(anabolicHormone);
        this.anabolicHormone *= (1 - Params.DIFFUSION);
        this.catabolicHormone *= (1 - Params.DIFFUSION);

        for (Patch p : neighbours) {
            Muscle.patches[p.coordinateX][p.coordinateY].anabolicHormone += (oldAnabolic * Params.DIFFUSION) / 8;
            Muscle.patches[p.coordinateX][p.coordinateY].catabolicHormone += (oldCatabolic * Params.DIFFUSION) / 8;
        }
        System.out.println(anabolicHormone);
    }
    public List<Patch> getNeighbours(int x, int y) {

        ArrayList<Patch> neighbours = new ArrayList<Patch>();

        // find possible neighbours for given coordinate
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                // check if patch exist
                if (i < Params.BOARD_WIDTH && i >= 0 && j < Params.BOARD_HEIGHT && j >= 0) {
                    // avoid duplicate of given coordinate
                    if (i != x || j != y) {
                        neighbours.add(Muscle.patches[i][j]);
                    }
                }
            }
        }
        return neighbours;
    }

}
