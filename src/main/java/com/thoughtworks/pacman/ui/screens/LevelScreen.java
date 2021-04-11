package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.ui.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;


public class LevelScreen implements Screen {
    private static final Font FONT = new Font("Monospaced", Font.BOLD, Tile.SIZE);

    private final Dimension dimension;
    private final Game game;
    private boolean startGame;

    public LevelScreen(Game game) {
        this.dimension = game.getDimension();
        this.game = game;
        this.startGame = false;
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(FONT);
        graphics.drawString(String.format("Level: %2d", game.getLevel()), Tile.SIZE * 10, Tile.SIZE * 15);
        if (game.getLevel() == 1 || game.getLevel() == 3 || game.getLevel() == 5){
            graphics.drawString(String.format("Score %2d to finish the level", game.getTutorialsWinScore()), Tile.SIZE * 4, Tile.SIZE * 16);
        }else{
            graphics.drawString(String.format("Clear Maze"), Tile.SIZE * 10, Tile.SIZE * 16);
        }
    }

    public Screen getNextScreen() throws Exception {
        if (startGame) {
            return new GameScreen(game.getLevel());
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        startGame = true;
    }
}

