package fr.LaurentFE.forestFire.controller;

import fr.LaurentFE.forestFire.model.Config;
import fr.LaurentFE.forestFire.model.Forest;
import fr.LaurentFE.forestFire.view.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DisplayWindow extends JFrame implements KeyListener {

    private final DrawingPanel drawingPanel;
    private final Forest forest;
    private boolean forestStillBurning = true;

    public DisplayWindow() {
        super("Forest Fire Simulation");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                confirmClose();
            }
        });

        Config config = Config.readConfig("./config.json");
        if (!config.isConfigUsable()) {
            forest = null;
            drawingPanel = null;
            unusableConfigClose();
        } else {
            forest = new Forest(
                    config.getForestHeight(),
                    config.getForestWidth(),
                    config.getFireSpreadProbability());
            forest.initialIgnite(config.getIgnitedTrees());

            drawingPanel = new DrawingPanel(
                    config.getTreeWidth(),
                    config.getTreeHeight(),
                    forest);

            this.add(drawingPanel, BorderLayout.CENTER);
            this.add(new Label(
                            "Press any key to go to the next step of the simulation", Label.CENTER),
                    BorderLayout.SOUTH);
            this.pack();

            this.addKeyListener(this);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }
    }

    // Displays an option panel to check if user really wants to exit program
    private void confirmClose() {
        int exitValue = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to exit ?",
                "Exit",
                JOptionPane.YES_NO_OPTION);
        if (exitValue == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    // Displays an option panel to inform the user that the config doesn't allow the simulation to run
    private void unusableConfigClose() {
        JOptionPane.showMessageDialog(
                null,
                "The config.json file contains values that do not allow the simulation to run.\n" +
                        "Check the error stream.",
                "config.json value error",
                JOptionPane.ERROR_MESSAGE);

        dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (!forestStillBurning)
            dispose();
        forestStillBurning = forest.playStep();
        drawingPanel.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
