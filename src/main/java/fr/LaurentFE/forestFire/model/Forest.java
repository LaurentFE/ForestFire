package fr.LaurentFE.forestFire.model;

import java.util.HashSet;
import java.util.Set;

public class Forest {
    private final int forestHeight;
    private final int forestWidth;
    private final double fireSpreadProbability;
    private Tree[][] forest;
    private Set<Tree> ignitedTrees;

    public Forest(int h, int l, double p) {
        forestHeight = h;
        forestWidth = l;
        fireSpreadProbability = p;
        initializeForest();
        ignitedTrees = new HashSet<>();
    }

    // Ignites the trees set in the config.json
    public void initialIgnite(int[][] treeCoords) {
        for(int[] treePos : treeCoords) {
            Tree t = forest[treePos[1]][treePos[0]];
            t.ignite();
            ignitedTrees.add(t);
        }
    }

    // Creates the graphs of trees.
    private void initializeForest() {
        forest = new Tree[forestHeight][forestWidth];
        for(int y=0; y<forestHeight; y++)
            for(int x = 0; x< forestWidth; x++) {
                forest[y][x] = new Tree();
            }

        for(int y=0; y<forestHeight; y++)
            for(int x = 0; x< forestWidth; x++) {
                if (x-1 >= 0)
                    forest[y][x].addNeighbour(forest[y][x-1]);
                if (x+1 < forestWidth)
                    forest[y][x].addNeighbour(forest[y][x+1]);
                if (y-1 >= 0)
                    forest[y][x].addNeighbour(forest[y-1][x]);
                if (y+1 < forestHeight)
                    forest[y][x].addNeighbour(forest[y+1][x]);
            }
    }

    // Returns true if there is more steps to play
    public boolean playStep() {
        Set<Tree> newlyIgnitedTrees = new HashSet<>();
        for(Tree t : ignitedTrees) {
            for(Tree neighbour: t.getNeighbours()) {
                if(neighbour.ignite(fireSpreadProbability))
                    newlyIgnitedTrees.add(neighbour);
            }
            t.burn();
        }
        ignitedTrees = newlyIgnitedTrees;

        return !ignitedTrees.isEmpty();
    }

    public Tree[][] getForestState() {
        return forest;
    }

    public int getForestHeight() {
        return forestHeight;
    }

    public int getForestWidth() {
        return forestWidth;
    }
}
