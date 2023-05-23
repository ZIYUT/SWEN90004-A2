/**
 *
 *
 */

public class Muscle {

    public final int WIDTH;
    public final int HEIGHT;
    public static Patch[][] patches;


    public Muscle() {
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
