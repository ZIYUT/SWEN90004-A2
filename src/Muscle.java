import java.util.ArrayList;
import java.util.List;

public class Muscle {

    public final int width;
    public final int height;
    public static Patch[][] patches;


    public Muscle() {
        this.width =  Params.BOARD_WIDTH;
        this.height = Params.BOARD_HEIGHT;
        patches = new Patch[height][width];
        setPatches();
    }
    public void setPatches() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                patches[j][i] = new Patch(j,i,new MuscleFiber());
            }
        }
    }

}
