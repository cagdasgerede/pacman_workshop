package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.ParentScreen;
import com.thoughtworks.pacman.ui.Screen;

public class LostScreen extends ParentScreen {
    static final Image LOST_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "gameOver.jpg");

    private final Dimension dimension;

    public LostScreen(Game game) {
        super(game);
        this.dimension = game.getDimension();

    }

    public void draw(Graphics2D graphics) {
        int height = LOST_SCREEN_IMAGE.getHeight(null) * dimension.width / LOST_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(LOST_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
        super.draw(graphics);
        
    }
   
}
