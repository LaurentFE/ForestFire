package fr.LaurentFE.forestFire;

import fr.LaurentFE.forestFire.model.Forest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForestTest {

    @Test
    void noIgnitedTreesReturnsFalse() {
        Forest forest = new Forest(3,3,0);
        int[][] coords = new int[][] {{1, 1}};
        forest.initialIgnite(coords);

        assertFalse(forest.playStep());
    }

    @Test
    void remainingIgnitedTreesReturnsFalse() {
        Forest forest = new Forest(3,3,1);
        int[][] coords = new int[][] {{1, 1}};
        forest.initialIgnite(coords);

        assertTrue(forest.playStep());
    }
}