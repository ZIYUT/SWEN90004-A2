import java.util.ArrayList;
import java.util.List;

public class Muscle {

    private final int width;
    private final int height;
    private Patch[][] patches;
    private int tickCount;

    public Muscle() {

        this.width = Params.BOARD_WIDTH;
        this.height = Params.BOARD_HEIGHT;
        this.patches = new Patch[height][width];
        this.tickCount = 0;
    }


    public List<Patch> getNeighbours(int x, int y) {

        ArrayList<Patch> neighbours = new ArrayList<Patch>();

        // find possible neighbours for given coordinate
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {

                // check if patch exist
                if (i < height && i >= 0 && j < width && j >= 0) {

                    // avoid duplicate of given coordinate
                    if (i != y || j != x) {

                        neighbours.add(patches[j][i]);
                    }
                }
            }
        }

        return neighbours;
    }
}
