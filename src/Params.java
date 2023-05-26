/**
 * Name: ZIYU TIAN(1284270),  YONGLE CHEN(1347396), JIYANG XIN(1322761)
 * parameters for the muscle development model
 */

public class Params {
    static final public int ANABOLIC_HORMONE_MAX = 200;//upper bound for the ANABOLIC_HORMONE
    static final public int ANABOLIC_HORMONE_MIN = 50;//lower bound for the ANABOLIC_HORMONE
    static final public int CATABOLIC_HORMONE_MAX = 250;//upper bound for the CATABOLIC_HORMONE
    static final public int CATABOLIC_HORMONE_MIN = 52;//lower bound for the CATABOLIC_HORMONE
    static final public int INTENSITY = 80;// Range for intensity should be 50 ~ 100.
    static final public double SLEEP_HOURS = 8;// Range for sleepHours should be 0.0 ~ 12.0 hours.
    static final public int DAYS_BETWEEN_WORKOUTS = 5;// Range for daysBetweenWorkouts should be 1 ~ 30 days.
    static final public int PERCENTAGE_OF_SLOW_TWITCH_FIBERS = 50;// Range for percentageOfSlowTwitchFibers should be 0 ~ 100.

    // The size of the board
    public final static int BOARD_WIDTH = 17;
    public final static int BOARD_HEIGHT = 17;

    public final static double DIFFUSION = 0.75;// The percentage of diffusion
    public final static boolean LIFT = true;// If it is training
}
