package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

public class IntroScreen implements Screen {
    static final Image TITLE_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "titleScreen.jpg");
    static final Image SETTINGS_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "difficultyMenu.png");

    private final Dimension dimension;
    private boolean startGame;
    private boolean settingsMenu;
    private String difficultyMode;

    public IntroScreen(Game game) {
        this.dimension = game.getDimension();
        this.startGame = false;
        this.settingsMenu = false;
        difficultyMode = "";
    }

    public void draw(Graphics2D graphics) {
        int height = TITLE_SCREEN_IMAGE.getHeight(null) * dimension.width / TITLE_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(TITLE_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
        if (settingsMenu) {
            int settingsMenuHeight = SETTINGS_SCREEN_IMAGE.getHeight(null) * 200 / SETTINGS_SCREEN_IMAGE.getWidth(null);
            graphics.drawImage(SETTINGS_SCREEN_IMAGE, dimension.width / 4 + 15, dimension.height / 4, 200, settingsMenuHeight, null);
        }
    }

    public Screen getNextScreen() throws Exception {
        if (startGame) {
            return new GameScreen(difficultyMode);
        } 
        return this;
    }

    public void keyPressed(KeyEvent e) {
        if (e != null) {
            if (e.getKeyCode() == KeyEvent.VK_S) {
                settingsMenu = true;
                return;
            }
        }
        startGame = true;
        difficultyMode = "easy";
    }

    public void mouseClicked(MouseEvent e) {
        if (settingsMenu) {
            int x = e.getX();
            int y = e.getY();
            if (x >= 146 && x <= 307) {
                if (y >= 163 && y <= 197) {
                    difficultyMode = "easy";
                } else if (y >= 225 && y <= 257) {
                    difficultyMode = "medium";
                } else if (y >= 286 && y <= 319) {
                    difficultyMode = "hard";
                }
            }
            startGame = true;
        }
    }

    public void setSettingsMenu(boolean settingsMenu) {
        this.settingsMenu = settingsMenu;
    }

    public String getDifficultyMode() {
        return difficultyMode;
    }
}
