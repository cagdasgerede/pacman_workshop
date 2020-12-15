package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Paths;

public class LoadScreen extends WindowAdapter implements ActionListener, ItemListener {
    private JComboBox<String> comboBox;
    private JPanel panel;
    private JFrame frame;
    private JLabel label;
    private JButton button;
    private String playerName;
    private Game game;
    private String path;

    public Game getGame(){
        return game;
    }

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
        if(e.getSource() == comboBox && !comboBox.getSelectedItem().equals("Select Player")){
            playerName = comboBox.getSelectedItem().toString();

            path = Paths.get("").toAbsolutePath().toString();
            path = path.substring(0,path.lastIndexOf("\\"));
            path = path.substring(0,path.lastIndexOf("\\"));
            path += "\\"+playerName+".bin";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!comboBox.getSelectedItem().equals("Select Player")) {
            File file = new File(path);
            if (file.exists()) {
                loadGame();
                frame.dispose();
            }
        }
    }

    public void windowClosing(WindowEvent e) {
        frame.dispose();
    }

    private void loadGame() {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            game = (Game) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
