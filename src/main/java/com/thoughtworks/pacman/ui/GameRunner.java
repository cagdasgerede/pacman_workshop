package com.thoughtworks.pacman.ui;

import com.thoughtworks.pacman.core.Game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class GameRunner {

    public final static ArrayList<String> names = new ArrayList<String>();
    public final static ArrayList<Integer> scores = new ArrayList<Integer>();
    public boolean newHighScore = false;
    public int finalScore=0;
    private static boolean newHighscore = false;

    private static final int FRAME_INTERVAL = 30;
    public int scorehold = 0;
    private static String userName = "abc";
    public ArrayList<Integer> listofscores = new ArrayList<Integer>();
    
    public static void main(String[] args) throws Exception {
        GameRunner runner = new GameRunner();
        initializeScoreList();
        
        runner.initialize();
        runner.run();
    }

    
    private static void initializeScoreList(){

        try { 
            FileInputStream fis=new FileInputStream("scores.txt");       
            Scanner sc=new Scanner(fis);
            while(sc.hasNextLine()) {  
                String[] parts = sc.nextLine().split(",");
                parts[0] = parts[0].trim();
                parts[1] = parts[1].trim();
                names.add(parts[0]);
                scores.add(Integer.parseInt(parts[1]));
            }  
            sc.close();
        } catch(IOException e) {  
            e.printStackTrace();  
        }  
    }
    private boolean open;
    private GameCanvas canvas;
    private Game game;
    int i = 0;
    private void initialize() throws Exception {
        game = new Game();
        Dimension dimension = game.getDimension();
        canvas = new GameCanvas(dimension, game);
        
        JFrame container = new JFrame("Pacman");

        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(dimension);
        panel.setLayout(null);

        container.pack();
        container.setResizable(false);
        container.setVisible(true);
        open = true;

        container.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                open = false;
                System.exit(0);
            }
        });

        canvas.initialize(panel);
    }

    private void run() throws Exception {
        while (open) {
            canvas.draw();
            try {
                Thread.sleep(FRAME_INTERVAL);
            } catch (InterruptedException e) {
            }
        }
        
        try { 
            FileInputStream fis=new FileInputStream("score.txt");       
            Scanner sc=new Scanner(fis);
            if(sc.hasNextLine()) { 
                finalScore = Integer.parseInt(sc.nextLine());
            }  
            if(scores.size()>=10){
                int minScore = 10000;
                int smallestEntry = 0;
                for(int i = 0 ; i<scores.size();i++){
                    if(scores.get(i)<minScore){
                        minScore = scores.get(i);
                        smallestEntry = i;
                    }
                }
                if(finalScore > minScore){
                    scores.remove(smallestEntry);
                    names.remove(smallestEntry);
                    scores.add(finalScore);
                    newHighscore = true;
                }
            }
            else{
                scores.add(finalScore);
                newHighscore = true;
            }
            sc.close();
        } catch(IOException e) {  
            e.printStackTrace();  
        }  
        userName = game.userName;

        
        

    }
}
