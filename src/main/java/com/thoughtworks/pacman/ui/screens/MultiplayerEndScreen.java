package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MultiplayerEndScreen implements Screen {
    static final Image WIN_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "winScreen.jpg");
    static final Image LOST_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "gameOver.png");

    private final Dimension dimension1;
    private final Dimension dimension2;
    private final Game game1;
    private final Game game2;
    private boolean startGame;
    private Image game1Image;
    private Image game2Image;
    private int height;

    public MultiplayerEndScreen(Game game1, Game game2) {
        this.dimension1 = game1.getDimension();
        this.dimension2 = game2.getDimension();
        this.game1 = game1;
        this.game2 = game2;
        this.startGame = false;

        this.game1Image = game1.won() ? WIN_SCREEN_IMAGE : LOST_SCREEN_IMAGE;
        this.game2Image = game2.won() ? WIN_SCREEN_IMAGE : LOST_SCREEN_IMAGE;
        this.height = WIN_SCREEN_IMAGE.getHeight(null) * dimension1.width / WIN_SCREEN_IMAGE.getWidth(null);
    }

    public void draw(Graphics2D graphics) {
        graphics.drawImage(game2Image, 0, 0, dimension2.width, height, null);
        graphics.drawImage(game2Image, dimension2.width + Tile.SIZE *2, 0, dimension2.width, height, null);
    }

    public Screen getNextScreen() {
        if (startGame) {
            IntroScreen introScreen = new IntroScreen(game1);
            introScreen.setMultiplayer(true);
            return introScreen;
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        startGame = true;
    }
}
