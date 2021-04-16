package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

import java.awt.Color;

public class ScoreScreen implements Screen {
    static final Image SCORE_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "scoreScreen.jpg");

    private final Dimension dimension;
    private final Game game;
    private boolean startGame;

    public final static ArrayList<String> names = new ArrayList<String>();
    public final static ArrayList<Integer> scores = new ArrayList<Integer>();
    public final static ArrayList<String> sortedNames = new ArrayList<String>();
    public final static ArrayList<Integer> sortedScores = new ArrayList<Integer>();

    public ScoreScreen(Game game) {
        this.dimension = game.getDimension();
        this.game = game;
        this.startGame = false;
    }
    public ScoreScreen(Game game, boolean initialize) {
        this.dimension = game.getDimension();
        this.game = game;
        this.startGame = false;
        if(initialize){
            initializeList();
        }
    }

    public void draw(Graphics2D graphics) {
        int height = SCORE_SCREEN_IMAGE.getHeight(null) * dimension.width / SCORE_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(SCORE_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(new Font("Monospaced", Font.BOLD, 40)); 
        graphics.drawString("Username   Score", 40, 30);
        graphics.setFont(new Font("DialogInput", Font.PLAIN, 20)); 


        for(int i = 0; i<sortedScores.size();i++){
            graphics.drawString(""+sortedNames.get(i), 45, 70+i*40);
            graphics.drawString(""+sortedScores.get(i), 350, 70+i*40);
        }

        graphics.setFont(new Font("Monospaced", Font.BOLD, 20));
        graphics.drawString("Press any key to get to the menu", 30, 500);

    }

    public Screen getNextScreen() {
        if (startGame) {
            return new IntroScreen(game);
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        startGame = true;
    }

    public void initializeList(){
        scores.clear();
        names.clear();
        sortedScores.clear();
        sortedNames.clear();
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


        } catch(IOException e2) {  
            e2.printStackTrace();  
        }
        while(scores.size()>0){
            int maxScore = 0;
            int pointer=0;
            for(int j = 0;j<scores.size();j++){
                if(maxScore<scores.get(j)){
                    maxScore = scores.get(j);
                    pointer = j;
                }
            }
            sortedScores.add(maxScore);
            sortedNames.add(names.get(pointer));
            names.remove(pointer);
            scores.remove(pointer);
        }
    }

}
