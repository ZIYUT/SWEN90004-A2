import java.util.List;

import java.util.*;
public class Patch {
    public MuscleFiber fiber;

    public int coordinateX;
    public int coordinateY;
    public double anabolicHormone = 50;
    public double catabolicHormone = 52;
    public Patch(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }
    public void performDailyActivity(MuscleFiber fiber) {
        /**
         * Simulate hormonal effect of lifestyle with the daily activities
         * such as working and sleeping
         */
        catabolicHormone = catabolicHormone + 2.0 * log(fiber.fiberSize, 10);
        anabolicHormone = anabolicHormone + 2.5 * log(fiber.fiberSize,10);
    }
    public void sleep(MuscleFiber fiber){
        /**
         * Simulate the hormonal effect of sleeping
         */
        catabolicHormone = catabolicHormone - 0.5 * log(catabolicHormone, 10) * Params.sleepHours;
        anabolicHormone = anabolicHormone - 0.48 * log(anabolicHormone,10) * Params.sleepHours;
    }

    public void developMuscle(MuscleFiber fiber){
        /**
         *  Catabolic hormones must prepare the fibers for growth before the
         *  anabolic hormones may add mass to the fibers
         */
        fiber.fiberSize = (fiber.fiberSize - 0.2 * log(catabolicHormone, 10));
        if(log(anabolicHormone, 10)<=(1.05*log(catabolicHormone, 10))){
            fiber.fiberSize = (fiber.fiberSize + 0.2 * log(anabolicHormone, 10));
        }
        else {
            fiber.fiberSize = (fiber.fiberSize + 0.2 * 1.05* log(catabolicHormone, 10));
        }
        fiber.regulateMuscleFibers();
    }

    public void liftWeight(){
        Random r = new Random();
        int n = r.nextInt(10000);
        if(n<(Params.intensity*Params.intensity)){
            anabolicHormone = anabolicHormone + log(fiber.fiberSize, 10)*55;
            catabolicHormone = catabolicHormone + log(fiber.fiberSize, 10) *44;
        };
    }
    public void regulateHormones(){
    }

    public static double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }

    public void diffuse(List<Patch> neighbours) {
        double oldAnabolic = this.anabolicHormone;
        double oldCatabolic = this.catabolicHormone;

        this.anabolicHormone *= (1 - Params.DIFFUSION);
        this.catabolicHormone *= (1 - Params.DIFFUSION);

        for (Patch p : neighbours) {
            p.anabolicHormone += (oldAnabolic * Params.DIFFUSION) / 8;
            p.catabolicHormone += (oldCatabolic * Params.DIFFUSION) / 8;
        }
    }
}
