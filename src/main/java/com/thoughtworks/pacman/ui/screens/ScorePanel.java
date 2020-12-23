package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.FileIO;
import com.thoughtworks.pacman.core.ScoreEntry;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorePanel extends JFrame implements ActionListener {
    JPanel panel;
    JLabel user_label, message;
    JTextField userName_text, fileName_text;
    JButton submit;
    String userName, fileName;
    ArrayList<ScoreEntry> scoreList;
    int score;

    public ScorePanel(int score) {
        user_label = new JLabel();
        user_label.setText("Your Score is High Enough to Join ScoreBoard!");
        userName_text = new JTextField("username");
        fileName_text = new JTextField("filename");
        submit = new JButton("Submit");
        panel = new JPanel(new GridLayout(6, 1));
        panel.add(user_label);
        panel.add(userName_text);
        panel.add(fileName_text);
        message = new JLabel();
        panel.add(submit);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        submit.addActionListener(this);
        add(panel);
        setTitle("Create new user");
        setSize(300, 150);
        setVisible(true);
        this.score = score;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        userName = userName_text.getText();
        fileName = fileName_text.getText();
        scoreList = FileIO.load();
        ScoreEntry current = new ScoreEntry(userName, score);
        scoreList.add(current);
        sortList();
        FileIO.setFileName(this.fileName);
        FileIO.save(scoreList);
        setVisible(false);
    }

    public void sortList() {
        for (int i = 0; i < scoreList.size() - 1; i++) {
            for (int j = 0; j < scoreList.size() - i - 1; j++) {
                if (scoreList.get(j).getScore().compareTo(scoreList.get(j + 1).getScore()) == -1) {
                    ScoreEntry temp = scoreList.get(j);
                    scoreList.set(j, scoreList.get(j + 1));
                    scoreList.set(j + 1, temp);
                }
            }
        }
    }
}
