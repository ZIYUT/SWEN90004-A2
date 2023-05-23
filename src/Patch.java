import java.util.List;

import java.util.*;

public class Patch {
    public MuscleFiber fiber;
    public int coordinateX;
    public int coordinateY;
    public double anabolicHormone = 50;
    public double catabolicHormone = 52;
    public Patch(int coordinateX, int coordinateY, MuscleFiber fiber) {
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
        this.anabolicHormone = this.anabolicHormone + 2.5 * log(this.fiber.fiberSize, 10);
    }

    public void sleep() {
        /**
         * Simulate the hormonal effect of sleeping
         */
        this.catabolicHormone = this.catabolicHormone - 0.5 * log(this.catabolicHormone, 10) * Params.SLEEP_HOURS;
        this.anabolicHormone = this.anabolicHormone - 0.48 * log(this.anabolicHormone, 10) * Params.SLEEP_HOURS;
    }

    public void developMuscle() {
        /**
         *  Catabolic hormones must prepare the fibers for growth before the
         *  anabolic hormones may add mass to the fibers
         */
        this.fiber.fiberSize = (this.fiber.fiberSize - 0.2 * log(this.catabolicHormone, 10));
        if (log(this.anabolicHormone, 10) <= (1.05 * log(this.catabolicHormone, 10))) {
            this.fiber.fiberSize = (this.fiber.fiberSize + 0.2 * log(this.anabolicHormone, 10));
        }
        else {
            this.fiber.fiberSize = (this.fiber.fiberSize + 0.2 * 1.05 * log(this.catabolicHormone, 10));
        }
        this.fiber.regulateMuscleFibers();
    }

    public void liftWeight() {
        /**
         * Simulate hormonal effect of weight lifting
         */
        Random r = new Random();
        int n = r.nextInt(10000);
        if (n < (Params.INTENSITY * Params.INTENSITY)) {
            this.anabolicHormone = this.anabolicHormone + log(this.fiber.fiberSize, 10) * 55;
            this.catabolicHormone = this.catabolicHormone + log(this.fiber.fiberSize, 10) * 44;
        }
    }

    public void regulateHormones() {
        /**
         *   Hormones diffuse to neighboring fibers
         *   if there are to many or to few hormones in an area,
         *   the body will try very hard to restore a balance
         */
        List<Patch> neighbours = getNeighbours(this.coordinateX, this.coordinateY);
        diffuse(neighbours);

        // Set the anabolicHormone and catabolicHormone values in the range of max and min values.
        if (this.anabolicHormone > Params.ANABOLIC_HORMONE_MAX) {
            this.anabolicHormone = Params.ANABOLIC_HORMONE_MAX;
        }
        if (this.anabolicHormone < Params.ANABOLIC_HORMONE_MIN) {
            this.anabolicHormone = Params.ANABOLIC_HORMONE_MIN;
        }
        if (this.catabolicHormone > Params.CATABOLIC_HORMONE_MAX) {
            this.catabolicHormone = Params.CATABOLIC_HORMONE_MAX;
        }
        if (this.catabolicHormone < Params.CATABOLIC_HORMONE_MIN) {
            this.catabolicHormone = Params.CATABOLIC_HORMONE_MIN;
        }
    }

    public static double log(double value, double base) {
        /**
         *  Log algorithm method
         */
        return Math.log(value) / Math.log(base);
    }

    public void diffuse(List<Patch> neighbours) {
        /**
         * Implemented diffuse according to the NetLogo Dictionary.
         *
         * Assumed the size of the Muscle should more than 2*2,
         * so the size of neighbours should be 3(conner),5(edge),8(Interior) respectively.
         *
         * Tells each patch to give equal shares of (number * 100) percent of the value of patch-variable
         * to its eight neighboring patches. number(it is from Params.DIFFUSION) should be between 0 and 1.
         * Regardless of topology the sum of patch-variable will be conserved across the world.
         * (If a patch has fewer than eight neighbors, each neighbor still gets an eighth share;
         * the patch keeps any leftover shares.)
         *
         * Note that this is an observer command only, even though you might expect it to be a patch command.
         * (The reason is that it acts on all the patches at once -- patch commands act on individual patches.)
         */
        double oldAnabolic = this.anabolicHormone;
        double oldCatabolic = this.catabolicHormone;
        double difusionRate = 1 - Params.DIFFUSION;
        if (neighbours.size() == 3) {
            this.anabolicHormone *= (1 - difusionRate*(3/8));
            this.catabolicHormone *= (1 - difusionRate*(3/8));
        } else if (neighbours.size() == 5) {
            this.anabolicHormone *= (1 - difusionRate*(5/8));
            this.catabolicHormone *= (1 - difusionRate*(5/8));
        } else if (neighbours.size() == 8) {
            this.anabolicHormone *= (1 - difusionRate);
            this.catabolicHormone *= (1 - difusionRate);
        } else {
            throw new Error("Error in diffuse method, size of the Muscle should more than 2*2, " +
                    "neighbours should not in the size out of 3,5,8");
        }

        for (Patch p : neighbours) {
            Muscle.patches[p.coordinateX][p.coordinateY].anabolicHormone += (oldAnabolic * difusionRate) / 8;
            Muscle.patches[p.coordinateX][p.coordinateY].catabolicHormone += (oldCatabolic * difusionRate) / 8;
        }
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
