package com.thoughtworks.pacman.ui.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoadScreen extends WindowAdapter implements ActionListener, ItemListener {
    private JComboBox<String> comboBox;
    private JPanel panel;
    private JFrame frame;
    private JLabel label;
    private JButton button;

    public LoadScreen(){
        frame = new JFrame("Load Game");
        label = new JLabel("Select Player");
        comboBox = new JComboBox<String>();
        button = new JButton("Load");
        panel = new JPanel();

        button.addActionListener(this);

        comboBox.addItem("Select Player");
        comboBox.addItem("Player1");
        comboBox.addItem("Player2");
        comboBox.addItem("Player3");
        comboBox.addItemListener(this);

        panel.add(label);
        panel.add(comboBox);
        panel.add(button);
        panel.setVisible(true);

        frame.setLayout(new FlowLayout());
        frame.add(panel);
        frame.setSize(700,200);
        frame.show();
        frame.addWindowListener(this);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
    }

    public void windowClosing(WindowEvent e) {
        frame.dispose();
    }
}
