package fr.LaurentFE.forestFire;

import java.util.HashSet;
import java.util.Set;

public class Tree {
    private State treeState;
    private final Set<Tree> neighbours;

    enum State {
        NORMAL,
        IGNITED,
        BURNT
    }

    public Tree() {
        treeState = State.NORMAL;
        neighbours = new HashSet<>();
    }

    public String getState() {
        return switch (treeState) {
            case State.NORMAL -> "NORMAL";
            case State.IGNITED -> "IGNITED";
            case State.BURNT -> "BURNT";
        };
    }

    public void burn() {
        treeState = State.BURNT;
    }

    public void ignite() {
        treeState = State.IGNITED;
    }

    public boolean ignite(double p) {
        if (treeState == State.NORMAL
                && Math.random() <= p) {
            treeState = State.IGNITED;
            return true;
        }
        return false;
    }

    public void addNeighbour(Tree t) {
        neighbours.add(t);
    }

    public Set<Tree> getNeighbours() {
        return neighbours;
    }
}
