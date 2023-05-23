/**
 * Name: ZIYU TIAN(1284270),  YONGLE CHEN(1347396), JIYANG XIN(1322761)
 * This is the running class for the program.
 *
 */

import java.io.*;

public class Main {

    private static double muscleMass;
    private static double averageAnabolic;
    private static double averageCatabolic;
    private static int tickCount;

    private static FileWriter writer;

    public static void setUp(Muscle muscle) {
        /**
         * To initialize the program
         */
        muscleMass = 0;
        tickCount = 0;
        double anabolic = 0;
        double catabolic = 0;
        for (int i = 0; i < muscle.WIDTH; i++) {
            for (int j = 0; j < muscle.HEIGHT; j++) {
                Patch patch = Muscle.patches[i][j];
                patch.regulateHormones();
                muscleMass = muscleMass + patch.fiber.fiberSize;
                anabolic = anabolic + patch.anabolicHormone;
                catabolic = catabolic + patch.catabolicHormone;
            }
        }
        averageAnabolic = anabolic / (Params.BOARD_WIDTH * Params.BOARD_HEIGHT);
        averageCatabolic = catabolic / (Params.BOARD_WIDTH * Params.BOARD_HEIGHT);
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
                if (Params.LIFT && tickCount % Params.DAYS_BETWEEN_WORKOUTS == 0) {
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
        averageAnabolic = anabolic / (Params.BOARD_WIDTH * Params.BOARD_HEIGHT);
        averageCatabolic = catabolic / (Params.BOARD_WIDTH * Params.BOARD_HEIGHT);
        tickCount++;
    }

    public static void setUpOutputCSV(String csvFileName, String title) {
        /**
         * Set up an output CSV file and set the title in the first line of the file.
         */
        try {
            writer = new FileWriter(csvFileName);
            writer.append(title + " \n");

        } catch (IOException e) {
            System.out.println("Error during setUp Output CSV:" + e.getMessage());
        }
    }

    public static void updateOutputCSV() {
        /**
         * Update the CSV that has been set up.
         */
        try {
            writer.append(tickCount + ", " + averageAnabolic + ", "
                    + averageCatabolic + ", " + muscleMass + " \n");
        } catch (IOException e) {
            System.out.println("Error during update Output CSV:" + e.getMessage());
        }
    }

    public static void closeOutputCSV() {
        /**
         * Close the CSV that has been set up.
         */
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Error during close Output CSV:" + e.getMessage());
        }
        writer = null;
    }

    public static void main(String[] args) {
        Muscle muscle = new Muscle();
        setUp(muscle);
        String csvName = "data.csv";
        String csvTitle = "Days/Ticks, averageAnabolic, averageCatabolic, muscleMass";
        setUpOutputCSV(csvName, csvTitle);
        // To set up the program
        while (tickCount < 10000) {
            /**
             * Simulate 10000 days
             */
            go();
            updateOutputCSV();
        }
        closeOutputCSV();
        System.out.println("The data for the Muscle Development model is stored in a file named: "+csvName);
    }
}
