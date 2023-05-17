public class Patch {
    public MuscleFiber fiber;
    public double anabolicHormone = 50;
    public double catabolicHormone = 52;

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
        catabolicHormone = catabolicHormone - 0.5 * log(fiber.fiberSize, 10) * Params.sleepHours;
        anabolicHormone = anabolicHormone - 0.48 * log(fiber.fiberSize,10) * Params.sleepHours;
    }

    public void developMuscle(MuscleFiber fiber){
        fiber.fiberSize = (fiber.fiberSize - 0.2*log(fiber.fiberSize, 10));
    }



    public static double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }
}
