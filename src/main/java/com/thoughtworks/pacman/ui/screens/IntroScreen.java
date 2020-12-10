package com.thoughtworks.pacman.ui.screens;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

public class IntroScreen implements Screen {
    static final Image TITLE_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "titleScreen.jpg");

    private final Dimension dimension;
    private Game gameForward;
    private boolean startGame;
    private boolean settingUp;

    public IntroScreen(Game game) {
        this.gameForward = game;
        this.dimension = game.getDimension();
        this.startGame = false;
        this.settingUp = false;
    }

    public void draw(Graphics2D graphics) {
        int height = TITLE_SCREEN_IMAGE.getHeight(null) * dimension.width / TITLE_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(TITLE_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
        graphics.drawString("This is gona be awesome",70,20);
    }

    public Screen getNextScreen() throws Exception {
        if (startGame) {
            return new GameScreen();
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_S){
            System.out.println("Setting Screen Up!");
            new SettingsScreen();
        }
        else {
            startGame = true;
        }
        
    }

}
