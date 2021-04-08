package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

public class LostScreen implements Screen {
    static final Image LOST_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "gameOver.png");

    private final Dimension dimension;
    private final Game game;
    private boolean startGame;


    StringBuilder sb = new StringBuilder();

    public LostScreen(Game game) {
        this.dimension = game.getDimension();
        this.game = game;
        this.startGame = false;
    }

    public void draw(Graphics2D graphics) {
        int height = LOST_SCREEN_IMAGE.getHeight(null) * dimension.width / LOST_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(LOST_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
    }

    public Screen getNextScreen() {
        if (startGame) {
            return new IntroScreen(game);
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        if(game.newHighScore){
            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                game.scoreList.put(sb.toString(),game.finalScore);
                try {
                    FileWriter fw = new FileWriter("scores.txt");
                    for (HashMap.Entry<String, Integer> entry : game.scoreList.entrySet()) {
                        fw.write("" + entry.getKey()+ ", " + entry.getValue());
                    }
                    fw.close();
                    game.newHighScore = false;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                

                startGame = true;
            }
            sb.append(e.getKeyChar());
        }
        else{
            startGame = true;
        }
    }
}
