package fr.LaurentFE.forestFire;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ForestFire {
    public static void main(String[] args) {
        /*
         * Envisioned Algorithm
         * Load config file (h, l, p, (x,y) of ignited trees, maybe GUI settings too)
         * Create array[h][l] of Tree {state, neighbours=new set<Tree>, void addNeighbour(Tree), bool ignite(p)}
         * ignite(p) checks for state, plays probability check, then modifies state and answers true if successful.
         * Compose graph of Tree by adding neighbours in accordance to graph
         * Ignite initial tree(s) and add them to a set
         * While there is a tree in set
         *   For each tree
         *     For each neighbour
         *       Try to ignite neighbour
         *       If ignite successful
         *         Add ignited tree to a new set
         *   Replace set with new set
         *   Display state of the forest using array of Tree
         *   Wait for user action
         * No tree is burning, end of simulation.
         */
        Config config = Config.readConfig("./config.json");
        Tree[][] forest = initializeForest(config.getForestHeight(), config.getForestLength());
        Set<Tree> ignitedTrees = new HashSet<>();
        for(int[] treePos : config.getIgnitedTrees()) {
            Tree t = forest[treePos[1]][treePos[0]];
            t.ignite();
            ignitedTrees.add(t);
        }

        // Display initial forest state
        System.out.println(displayForest(forest));
        // Wait for user interaction
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        while(!ignitedTrees.isEmpty()) {
            Set<Tree> newlyIgnitedTrees = new HashSet<>();
            for(Tree t : ignitedTrees) {
                for(Tree neighbour: t.getNeighbours()) {
                    if(neighbour.ignite(config.getFireSpreadProbability()))
                        newlyIgnitedTrees.add(neighbour);
                }
                t.burn();
            }

            // Display forest state
            System.out.println(displayForest(forest));
            // Wait for user interaction
            try {
                System.in.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ignitedTrees = newlyIgnitedTrees;
        }
    }

    public static Tree[][] initializeForest(int h, int l) {
        Tree[][] forest = new Tree[h][l];
        for(int y=0; y<h; y++)
            for(int x=0; x<l; x++) {
                forest[y][x] = new Tree();
            }

        for(int y=0; y<h; y++)
            for(int x=0; x<l; x++) {
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

    public static String displayForest(Tree[][] forest) {
        StringBuilder s = new StringBuilder();
        for (Tree[] trees : forest) {
            for (Tree t : trees) {
                s.append(
                        switch (t.getState()) {
                            case "NORMAL" -> "N ";
                            case "IGNITED" -> "I ";
                            case "BURNT" -> "# ";
                            default -> "? ";
                        }
                );
            }
            s.append("\n");
        }
        return s.toString();
    }
}