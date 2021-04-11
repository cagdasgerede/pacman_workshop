package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

public class WinScreen implements Screen {
    static final Image WIN_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "winScreen.jpg");

    private final Dimension dimension;
    private final Game game;
    private boolean startGame;

    public final static ArrayList<String> names = new ArrayList<String>();
    public final static ArrayList<Integer> scores = new ArrayList<Integer>();

    boolean newHighscore = false;
    Integer finalScore = 0;
    StringBuilder sb = new StringBuilder();

    public WinScreen(Game game) {
        this.dimension = game.getDimension();
        this.game = game;
        this.startGame = false;

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

            FileInputStream fis2=new FileInputStream("score.txt");       
            Scanner sc2=new Scanner(fis2);
            if(sc2.hasNextLine()) { 
                finalScore = Integer.parseInt(sc2.nextLine());
            }
            sc2.close();

        } catch(IOException e2) {  
            e2.printStackTrace();  
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
    }

    public void draw(Graphics2D graphics) {
        int height = WIN_SCREEN_IMAGE.getHeight(null) * dimension.width / WIN_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(WIN_SCREEN_IMAGE, 0, 0, dimension.width, height, null);

        if(newHighscore){
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 340, 700, 300);
            graphics.setColor(Color.YELLOW);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
            graphics.drawString("You Won!", 140, 350);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
            graphics.drawString("New Highscore! Enter your name below", 20, 430);
            graphics.drawString("Press enter to confirm it", 120, 450);
        }
        graphics.setColor(Color.YELLOW);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        graphics.drawString(sb.toString().replace("?",""), (int)(200+sb.toString().replace("?","").length()*6.1)-sb.toString().replace("?","").length()*10, 490);
    }

    public Screen getNextScreen() {
        if (startGame) {
            return new IntroScreen(game);
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        if(newHighscore){
            if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            {  
                if(sb.toString().length()>=1)
                sb.deleteCharAt(sb.toString().length() - 1);
            }
            else if(e.getKeyCode()==KeyEvent.VK_ENTER){
                game.userName = sb.toString();
            }
            else if(e.getKeyCode()!=KeyEvent.VK_CAPS_LOCK)
                sb.append(e.getKeyChar());
        }
        else{
            startGame = true;
        }

        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            names.add(sb.toString().replace("?",""));
            try {
                FileWriter fw = new FileWriter("scores.txt");
                
                for(int i = 0 ; i<scores.size();i++){
                    fw.write(""+names.get(i)+", "+scores.get(i)+"\n");
                }
                //fw.write("");
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            names.clear();
            scores.clear();
            startGame = true;
        }
    }
}
