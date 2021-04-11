package com.thoughtworks.pacman.ui.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

public class IntroScreen implements Screen {
    static final Image TITLE_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "titleScreen.jpg");

    private final Dimension dimension;
    private boolean startGame;
    private boolean scoreListMenu;
    private Game game;

    public IntroScreen(Game game) {
        this.dimension = game.getDimension();
        this.startGame = false;
        this.scoreListMenu = false;
        this.game = game;
    }

    public void draw(Graphics2D graphics) {
        int height = TITLE_SCREEN_IMAGE.getHeight(null) * dimension.width / TITLE_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(TITLE_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
        graphics.setFont(new Font("Monospaced", Font.BOLD, 16));
        graphics.setColor(Color.YELLOW);
        graphics.drawString("Press T to see the high score list", 65, 400);
    }

    public Screen getNextScreen() throws Exception {
        if (startGame) {
            return new GameScreen();
        }
        else if(scoreListMenu){
            return new ScoreScreen(game,true);
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_T){
            scoreListMenu=true;
        }
        else{
            startGame = true;
        }
    }
}
