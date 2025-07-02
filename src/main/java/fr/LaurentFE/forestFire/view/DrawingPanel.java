package fr.LaurentFE.forestFire.view;

import fr.LaurentFE.forestFire.model.Forest;
import fr.LaurentFE.forestFire.model.Tree;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {

    Forest forest;
    int treeWidth;
    int treeHeight;

    public DrawingPanel(int treeWidth, int treeHeight, Forest forest) {
        int forestWidth = forest.getForestWidth() * treeWidth;
        int forestHeight = forest.getForestHeight() * treeHeight;
        this.setPreferredSize(new Dimension(forestWidth, forestHeight));
        this.forest = forest;
        this.treeWidth = treeWidth;
        this.treeHeight = treeHeight;
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        for(int y=0; y<forest.getForestState().length; y++) {
            for(int x=0; x<forest.getForestState()[0].length; x++) {
                Tree t = forest.getForestState()[y][x];
                switch (t.getState()) {
                    case "NORMAL" -> g2D.setColor(Color.GREEN);
                    case "IGNITED" -> g2D.setColor(Color.ORANGE);
                    case "BURNT" -> g2D.setColor(Color.BLACK);
                }
                g2D.fillRect(x*treeWidth, y*treeHeight, treeWidth, treeHeight);
                g2D.setColor(Color.BLACK);
                g2D.drawRect(x*treeWidth, y*treeHeight, treeWidth, treeHeight);
            }
        }
    }
}
