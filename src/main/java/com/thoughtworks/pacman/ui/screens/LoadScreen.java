package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LoadScreen extends WindowAdapter implements ActionListener, ItemListener {
    private JComboBox<String> comboBox;
    private JPanel panel;
    private JFrame frame;
    private JLabel label;
    private JButton buttonLoad;
    private JButton buttonAutoSave;
    private String playerName;
    private Game game;
    private String path;

    public Game getGame(){
        return game;
    }

    public LoadScreen(){
        path = Paths.get("").toAbsolutePath().toString();
        path = path.substring(0,path.lastIndexOf("\\"));
        path = path.substring(0,path.lastIndexOf("\\"));

        frame = new JFrame("Load Game");
        label = new JLabel("Select Game");
        comboBox = new JComboBox<String>();
        buttonLoad = new JButton("Load");
        buttonAutoSave = new JButton("AutoSave");
        panel = new JPanel();

        buttonLoad.addActionListener(this);
        buttonAutoSave.addActionListener(this);

        comboBox.addItem("Select Player");
        File file = new File(path + "\\" + "saved.txt");
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(!line.equals("")){
                    line = line.replace('#',' ');
                    line = line.replace('$', ':');
                    comboBox.addItem(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        comboBox.addItemListener(this);

        panel.add(label);
        panel.add(comboBox);
        panel.add(buttonLoad);
        panel.add(buttonAutoSave);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("AutoSave")){
            path += "\\" + "autoSave.bin";
            File file = new File(path);
            if (file.exists()) {
                loadGame();
                frame.dispose();
            }
        }

        else {
            if (!comboBox.getSelectedItem().equals("Select Player")) {
                playerName = playerName.replace(' ','#');
                playerName = playerName.replace(':', '$');
                deleteFromSavedTxt();
                path += "\\"+playerName+".bin";
                File file = new File(path);
                if (file.exists()) {
                    loadGame();
                    frame.dispose();
                }
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

    private void deleteFromSavedTxt(){
        File file = new File(path + "\\saved.txt");
        List<String> out = null;
        try {
            out = Files.lines(file.toPath())
                    .filter(line -> !line.contains(playerName))
                    .collect(Collectors.toList());
            Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
