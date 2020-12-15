package com.thoughtworks.pacman.ui.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.thoughtworks.pacman.core.Game;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Paths;

public class SaveScreen extends WindowAdapter implements ActionListener, ItemListener {
    private JComboBox<String> comboBox;
    private JPanel panel;
    private JFrame frame;
    private JLabel label;
    private JButton button;
    private String playerName;

    private final Game game;

    public SaveScreen(Game game){
        this.game = game;

        for(int i=0; i<game.getGhosts().length; i++){
            game.getGhosts()[i].setSPEED(0);
        }

        frame = new JFrame("Save and Exit");
        label = new JLabel("Select Player");
        comboBox = new JComboBox<String>();
        button = new JButton("Save");
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
        if(e.getSource() == comboBox && !comboBox.getSelectedItem().equals("Select Player")){
            playerName = comboBox.getSelectedItem().toString();
        }
    }

    public void windowClosing(WindowEvent e) {
        for(int i=0; i<game.getGhosts().length; i++){
            game.getGhosts()[i].setSPEED(100);
        }
        frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        save();
    }

    private void save(){
        String path = Paths.get("").toAbsolutePath().toString();
        path = path.substring(0,path.lastIndexOf("\\"));
        path = path.substring(0,path.lastIndexOf("\\"));
        path += "\\" + playerName + ".bin";

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(game);

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
