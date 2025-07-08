package fr.LaurentFE.forestFire.model;

import java.util.HashSet;
import java.util.Set;

public class Forest {
    private final int forestHeight;
    private final int forestWidth;
    private final double fireSpreadProbability;
    private Tree[][] trees;
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
            Tree t = trees[treePos[1]][treePos[0]];
            t.ignite();
            ignitedTrees.add(t);
        }
    }

    // Creates the graphs of trees.
    private void initializeForest() {
        trees = new Tree[forestHeight][forestWidth];
        for(int y=0; y<forestHeight; y++)
            for(int x = 0; x< forestWidth; x++) {
                trees[y][x] = new Tree();
            }

        for(int y=0; y<forestHeight; y++)
            for(int x = 0; x< forestWidth; x++) {
                if (x-1 >= 0)
                    trees[y][x].addNeighbour(trees[y][x-1]);
                if (x+1 < forestWidth)
                    trees[y][x].addNeighbour(trees[y][x+1]);
                if (y-1 >= 0)
                    trees[y][x].addNeighbour(trees[y-1][x]);
                if (y+1 < forestHeight)
                    trees[y][x].addNeighbour(trees[y+1][x]);
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

    private boolean isWithinBounds(int x, int y) {
        return x < forestWidth && x >= 0 && y < forestHeight && y >= 0;
    }

    // Returns the Tree at the given coordinates, or null if out of bounds
    public Tree getTree(int x, int y) {
        if (isWithinBounds(x, y))
            return trees[y][x];
        return null;
    }

    public int getForestHeight() {
        return forestHeight;
    }

    public int getForestWidth() {
        return forestWidth;
    }
}
