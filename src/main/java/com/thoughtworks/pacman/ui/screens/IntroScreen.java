package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.*;

import com.thoughtworks.pacman.core.achievements.Achievements;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

public class IntroScreen implements Screen {
    static final Image TITLE_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "titleScreen.jpg");

    private final Dimension dimension;
    private boolean startGame;
    private Game game;
    private boolean closeDialogBox = false;

    public IntroScreen(Game game) {
        this.dimension = game.getDimension();
        this.startGame = false;
        this.game = game;
    }

    public void draw(Graphics2D graphics) {
        int height = TITLE_SCREEN_IMAGE.getHeight(null) * dimension.width / TITLE_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(TITLE_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
    }

    public Screen getNextScreen() throws Exception {
        if (startGame) {
            return new GameScreen();
        }
        if(!closeDialogBox)
            showAchievementInfoBox();

        return this;
    }

    public void setCloseDialogBox(boolean b) {
        this.closeDialogBox = b;
    }

    public void showAchievementInfoBox() {
        String message = new Achievements(game).getAchievementInfo();
        int result = JOptionPane.showConfirmDialog(null, message, "Achievements", JOptionPane.DEFAULT_OPTION);
        if(result == JOptionPane.OK_OPTION)
            this.closeDialogBox = true;
    }

    public void keyPressed(KeyEvent e) {
        startGame = true;
    }
}
