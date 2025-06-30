package fr.LaurentFE.forestFire;

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
    }
}