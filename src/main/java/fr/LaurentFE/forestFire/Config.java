package fr.LaurentFE.forestFire;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private int forestHeight;
    private int forestLength;
    private double fireSpreadProbability;
    private int[][] ignitedTrees;

    public Config() {

    }

    public int getForestHeight() {
        return forestHeight;
    }

    public void setForestHeight(int forestHeight) {
        this.forestHeight = forestHeight;
    }

    public int getForestLength() {
        return forestLength;
    }

    public void setForestLength(int forestLength) {
        this.forestLength = forestLength;
    }

    public double getFireSpreadProbability() {
        return fireSpreadProbability;
    }

    public void setFireSpreadProbability(double fireSpreadProbability) {
        this.fireSpreadProbability = fireSpreadProbability;
    }

    public int[][] getIgnitedTrees() {
        return ignitedTrees;
    }

    public void setIgnitedTrees(int[][] ignitedTrees) {
        this.ignitedTrees = ignitedTrees;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("forestHeight:" + forestHeight + "\n")
                .append("forestLength:").append(forestLength).append("\n")
                .append("fireSpreadProbability:").append(fireSpreadProbability).append("\n")
                .append("ignitedTrees:");
        for (int[] pos: ignitedTrees) {
            s.append("(").append(pos[0]).append(",").append(pos[1]).append(")");
        }
        return s.toString();
    }

    public static Config readConfig(String path) {
        Path configFilePath = Paths.get(path);
        String jsonConfig;
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonConfig = Files.readString(configFilePath);
            return mapper.readValue(jsonConfig, Config.class);
        } catch (JsonProcessingException e) {
            System.err.println("Failed to parse config.json into Config object");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("Failed to read " + configFilePath + " file");
            throw new RuntimeException(e);
        }
    }
 }
