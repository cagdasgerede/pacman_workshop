package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import com.thoughtworks.pacman.ui.Sound;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

public class LostScreen implements Screen {
    static final Image LOST_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "gameOver.png");
    Sound   sound;
    private final Dimension dimension;
    private final Game game;
    private boolean startGame;

    public LostScreen(Game game) {
        this.dimension = game.getDimension();
        this.game = game;
        this.startGame = false;
        sound =new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        sound.playGameOverScreenSound();
      
    }

    public void draw(Graphics2D graphics) {
       
        int height = LOST_SCREEN_IMAGE.getHeight(null) * dimension.width / LOST_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(LOST_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
    }

    public Screen getNextScreen() {
        if (startGame) {
         sound.gameOverSoundStop();
        return new IntroScreen(game);
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        startGame = true;
    }
}
