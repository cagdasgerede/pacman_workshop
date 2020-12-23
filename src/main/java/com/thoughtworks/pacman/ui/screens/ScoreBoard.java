package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.FileIO;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class ScoreBoard extends JFrame {
    JPanel panel;
    JLabel message;
    String Text = "";

    public ScoreBoard() {
        panel = new JPanel(new GridLayout(0, 1));
        Text = FileIO.getScoreBoard();
        String[] lines = Text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            message = new JLabel("   " + lines[i]);
            panel.add(message);
        }
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        add(panel, BorderLayout.CENTER);
        setTitle("ScoreBoard");
        setSize(250, 250);
        setVisible(true);
    }
}
