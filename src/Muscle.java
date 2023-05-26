/**
 * Name: ZIYU TIAN(1284270),  YONGLE CHEN(1347396), JIYANG XIN(1322761)
 * It represents the muscle in this model, which can be abstracted as a board/table that contains patches.
 * here each patch contains a muscle fiber and 2 kind of hormones
 */

public class Muscle {

    public final int WIDTH;// WIDTH of the board
    public final int HEIGHT;// HEIGHT of the board
    public static Patch[][] patches;// muscle fibers and hormones


    public Muscle() {
        /**
         * initial Muscle base on the parameters set in Params
         */
        this.WIDTH =  Params.BOARD_WIDTH;
        this.HEIGHT = Params.BOARD_HEIGHT;
        patches = new Patch[WIDTH][HEIGHT];
        setPatches();
    }
    public void setPatches() {
        /**
         *  j represents height, i represents width
         */
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                patches[i][j] = new Patch(i,j,new MuscleFiber());
            }
        }
    }

}
