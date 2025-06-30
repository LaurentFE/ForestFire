package fr.LaurentFE.forestFire;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    private Tree[][] initializeForest(int h, int l) {
        Tree[][] forest = new Tree[h][l];
        for(int y=0; y<3; y++)
            for(int x=0; x<3; x++) {
                forest[y][x] = new Tree();
            }

        for(int y=0; y<3; y++)
            for(int x=0; x<3; x++) {
                if (x-1 >= 0)
                    forest[y][x].addNeighbour(forest[y][x-1]);
                if (x+1 < l)
                    forest[y][x].addNeighbour(forest[y][x+1]);
                if (y-1 >= 0)
                    forest[y][x].addNeighbour(forest[y-1][x]);
                if (y+1 < h)
                    forest[y][x].addNeighbour(forest[y+1][x]);
            }
        return forest;
    }

    @Test
    void cantIgniteABurntTree() {
        Tree t = new Tree();
        t.burn();
        assertFalse(t.ignite(1));
    }

    @Test
    void cantIgniteAnIgnitedTree() {
        Tree t = new Tree();
        t.ignite();
        assertFalse(t.ignite(1));
    }

    @Test
    void checkFirePropagationWhenProbabilityIsCertain() {
        Tree[][] forest = initializeForest(3,3);
        forest[1][1].ignite();

        for (Tree t : forest[1][1].getNeighbours()) {
            t.ignite(1);
        }

        assertAll(
                () -> assertEquals("NORMAL", forest[0][0].getState()),
                () -> assertEquals("IGNITED", forest[0][1].getState()),
                () -> assertEquals("NORMAL", forest[0][2].getState()),
                () -> assertEquals("IGNITED", forest[1][0].getState()),
                () -> assertEquals("IGNITED", forest[1][1].getState()),
                () -> assertEquals("IGNITED", forest[1][2].getState()),
                () -> assertEquals("NORMAL", forest[2][0].getState()),
                () -> assertEquals("IGNITED", forest[2][1].getState()),
                () -> assertEquals("NORMAL", forest[2][2].getState())
        );
    }

    @Test
    void NoFirePropagationWhenProbabilityIsZero() {
        Tree[][] forest = initializeForest(3,3);
        forest[1][1].ignite();

        for (Tree t : forest[1][1].getNeighbours()) {
            t.ignite(0);
        }

        assertAll(
                () -> assertEquals("NORMAL", forest[0][0].getState()),
                () -> assertEquals("NORMAL", forest[0][1].getState()),
                () -> assertEquals("NORMAL", forest[0][2].getState()),
                () -> assertEquals("NORMAL", forest[1][0].getState()),
                () -> assertEquals("IGNITED", forest[1][1].getState()),
                () -> assertEquals("NORMAL", forest[1][2].getState()),
                () -> assertEquals("NORMAL", forest[2][0].getState()),
                () -> assertEquals("NORMAL", forest[2][1].getState()),
                () -> assertEquals("NORMAL", forest[2][2].getState())
        );
    }
}