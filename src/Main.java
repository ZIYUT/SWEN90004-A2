public class Main {
    private static double muscleMass;
    private static int tickCount;
    public static void setUp(Muscle muscle) {
        /**
         * To initialize the program
         */
        muscleMass = 0;
        tickCount = 0;
        for (int i = 0; i < muscle.width; i++) {
            for (int j = 0; j < muscle.height; j++) {
                Patch patch = muscle.patches[j][i];
                patch.regulateHormones();
                muscleMass = muscleMass + patch.fiber.fiberSize;
            }
        }
    }

    public static void go() {
        muscleMass = 0;
        for (int i = 0; i < Params.BOARD_WIDTH; i++) {
            for (int j = 0; j < Params.BOARD_HEIGHT; j++) {
                Muscle.patches[j][i].performDailyActivity();
                Muscle.patches[j][i].sleep();
                Muscle.patches[j][i].regulateHormones();
                Muscle.patches[j][i].developMuscle();
                muscleMass = muscleMass + Muscle.patches[j][i].fiber.fiberSize;
            }
        }
        tickCount++;
        System.out.println(muscleMass);
        System.out.println(tickCount);

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
