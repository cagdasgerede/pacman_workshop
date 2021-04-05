package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.Sound;

public class IntroScreen implements Screen {
    static final Image TITLE_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "titleScreen.jpg");
    Sound  s;
    private final Dimension dimension;
    private boolean startGame;

    public IntroScreen(Game game) {
        this.dimension = game.getDimension();
        this.startGame = false;
        s =new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        s.playIntroSound();
    }

    public void draw(Graphics2D graphics) {
        
        int height = TITLE_SCREEN_IMAGE.getHeight(null) * dimension.width / TITLE_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(TITLE_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
    }

    public Screen getNextScreen() throws Exception {
        
        if (startGame) {
            s.stopIntroSound();
            return new GameScreen();
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        startGame = true;
    }
}
