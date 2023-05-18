public class Main {
    private static double muscleMass;
    private static double averageAnabolic;
    private static double averageCatabolic;
    private static int tickCount;
    public static void setUp(Muscle muscle) {
        /**
         * To initialize the program
         */
        muscleMass = 0;
        tickCount = 0;
        double anabolic = 0;
        double catabolic = 0;
        for (int i = 0; i < muscle.width; i++) {
            for (int j = 0; j < muscle.height; j++) {
                Patch patch = Muscle.patches[i][j];
                patch.regulateHormones();
                muscleMass = muscleMass + patch.fiber.fiberSize;
                anabolic = anabolic + patch.anabolicHormone;
                catabolic = catabolic + patch.catabolicHormone;
            }
        }
        averageAnabolic = anabolic/(Params.BOARD_WIDTH*Params.BOARD_HEIGHT);
        averageCatabolic = catabolic/(Params.BOARD_WIDTH*Params.BOARD_HEIGHT);
    }

    public static void go() {
        /**
         * To run the program
         */
        muscleMass = 0;
        double anabolic = 0;
        double catabolic = 0;
        for (int i = 0; i < Params.BOARD_WIDTH; i++) {
            for (int j = 0; j < Params.BOARD_HEIGHT; j++) {
                Muscle.patches[i][j].performDailyActivity();
                if (Params.LIFT && tickCount%Params.daysBetweenWorkouts==0){
                    Muscle.patches[i][j].liftWeight();
                }
                Muscle.patches[i][j].sleep();
                Muscle.patches[i][j].regulateHormones();
                Muscle.patches[i][j].developMuscle();
                muscleMass = muscleMass + Muscle.patches[i][j].fiber.fiberSize;
                anabolic = anabolic + Muscle.patches[i][j].anabolicHormone;
                catabolic = catabolic + Muscle.patches[i][j].catabolicHormone;
            }
        }
        averageAnabolic = anabolic/(Params.BOARD_WIDTH*Params.BOARD_HEIGHT);
        averageCatabolic = catabolic/(Params.BOARD_WIDTH*Params.BOARD_HEIGHT);
        tickCount++;
        //System.out.println(muscleMass);
        //System.out.println(averageAnabolic-averageCatabolic);
        //System.out.println(averageAnabolic);
        //System.out.println(averageCatabolic);
        // System.out.println(tickCount);

    }
    public static void main(String[] args) {
        Muscle muscle = new Muscle();
        setUp(muscle);
        while(tickCount < 1000){
            go();
            //System.out.println(Muscle.patches[0][0].fiber.fiberSize);
        }

    }
}
