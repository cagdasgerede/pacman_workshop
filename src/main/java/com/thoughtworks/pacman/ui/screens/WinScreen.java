package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.ParentScreen;
import com.thoughtworks.pacman.ui.Screen;

public class WinScreen extends ParentScreen {
    static final Image WIN_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "winScreen.jpg");

    private final Dimension dimension;

    public WinScreen(Game game) {
        super(game);
        this.dimension = game.getDimension();

    }

    public void draw(Graphics2D graphics) {
        int height = WIN_SCREEN_IMAGE.getHeight(null) * dimension.width / WIN_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(WIN_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
        super.draw(graphics);

    }
}
