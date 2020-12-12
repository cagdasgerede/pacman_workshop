package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

public class WinScreen implements Screen {
    static final Image WIN_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "winScreen.jpg");
    static final Image LEVEL_WAITING_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "SSnext.png");
    private final Dimension dimension;
    private final Game game;
    private boolean startGame;

    public WinScreen(Game game) {
        this.dimension = game.getDimension();
        this.game = game;
        this.startGame = false;
    }

    public void draw(Graphics2D graphics) {
        if(game.getLevel() < 3) {
            int height = LEVEL_WAITING_SCREEN_IMAGE.getHeight(null) * dimension.width / LEVEL_WAITING_SCREEN_IMAGE.getWidth(null);
            graphics.drawImage(LEVEL_WAITING_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
        }
        else{
            int height = WIN_SCREEN_IMAGE.getHeight(null) * dimension.width / WIN_SCREEN_IMAGE.getWidth(null);
            graphics.drawImage(WIN_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
        }
    }

    public Screen getNextScreen() throws Exception{
        if (startGame) {
            if(game.getLevel() < 3) {
                game.incrementLevel();
                return new GameScreen(game.getLevel());
            }
            else{
                return new IntroScreen(new Game());
            }
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        startGame = true;
    }
}
