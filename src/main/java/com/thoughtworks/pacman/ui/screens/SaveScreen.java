package com.thoughtworks.pacman.ui.screens;
import com.thoughtworks.pacman.core.Game;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SaveScreen extends WindowAdapter implements ActionListener{
    private JTextField textField;
    private JPanel panel;
    private JFrame frame;
    private JLabel label;
    private JButton button;

    private final Game game ;

    private Logger logger = LogManager.getLogManager().getLogger(SaveScreen.class.getName());

    public SaveScreen(Game game, String whereWasItCalledFrom){
        this.game = game;
        if(whereWasItCalledFrom.equals("GameScreen")) {
            if (game != null) {

                for (int i = 0; i < game.getGhosts().length; i++) {
                    game.getGhosts()[i].setSPEED(0);
                }

                frame = new JFrame("Save");
                label = new JLabel("Player Name");
                textField = new JTextField(20);
                button = new JButton("Save");
                panel = new JPanel();

                button.addActionListener(this);

                panel.add(label);
                panel.add(textField);
                panel.add(button);
                panel.setVisible(true);

                frame.setLayout(new FlowLayout());
                frame.add(panel);
                frame.setSize(700, 200);
                frame.show();

                frame.addWindowListener(this);
            }
        }

        else if(whereWasItCalledFrom.equals("GameRunner")){
            save("autoSave");
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
        String playerName;
        if(e.getActionCommand().equals("Save")){
            playerName = textField.getText();
            if(!playerName.equals("")){
                save(playerName);
                for(int i=0; i<game.getGhosts().length; i++){
                    game.getGhosts()[i].setSPEED(100);
                }
                frame.dispose();
            }
        }
    }

    private void save(String playerName){
        String path = Paths.get("").toAbsolutePath().toString();
        path = path.substring(0,path.lastIndexOf("\\"));
        path = path.substring(0,path.lastIndexOf("\\"));

        if(!playerName.equals("autoSave")){
            String date = java.time.LocalDate.now().toString();
            String time = java.time.LocalTime.now().toString();
            time = time.substring(0,time.lastIndexOf("."));
            time = time.replace(':', '$');
            String dateAndTime = " " + date + " " + time;
            playerName += dateAndTime;
            try {
                FileWriter fileWriter = new FileWriter((path + "\\" + "saved.txt"), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
                printWriter.println(playerName);
                printWriter.close();
            } catch (IOException e) {
                logger.log(Level.INFO, e.getMessage(), e);
            }
        }

        path += "\\" + playerName + ".bin";

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(game);

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            logger.log(Level.INFO, e.getMessage(), e);
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage(), e);
        }
    }
}
