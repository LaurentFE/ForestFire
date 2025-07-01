package fr.LaurentFE.forestFire;

import java.io.IOException;

public class ForestFire {
    public static void main(String[] args) {

        Config config = Config.readConfig("./config.json");
        Forest forest = new Forest(
                config.getForestHeight(),
                config.getForestLength(),
                config.getFireSpreadProbability());
        forest.initialIgnite(config.getIgnitedTrees());

        // Display initial forest state
        System.out.println(displayForestState(forest.getForestState()));
        // Wait for user interaction
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        while(forest.playStep()) {
            // Display current forest state
            System.out.println(displayForestState(forest.getForestState()));
            // Wait for user interaction
            try {
                System.in.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // Display final forest state
        System.out.println(displayForestState(forest.getForestState()));
    }

    public static String displayForestState(Tree[][] forest) {
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