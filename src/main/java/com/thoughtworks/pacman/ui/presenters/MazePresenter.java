package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.pacman.ui.TileToPresenterFactory.toPresenter;

public class MazePresenter implements Presenter {
    private final Maze maze;
    private final List<Presenter> mazeTiles;

    private static final Font FONT = new Font("Monospaced", Font.BOLD, Tile.SIZE);

    public MazePresenter(Maze maze) {
        this.maze = maze;
        this.mazeTiles = new ArrayList<Presenter>();
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                mazeTiles.add(toPresenter(maze.tileAt(new TileCoordinate(x, y))));
            }
        }
    }

    public void draw(Graphics2D graphics) {
        for (Presenter tilePresenter : mazeTiles) {
            tilePresenter.draw(graphics);
        }
        drawScore(graphics);
    }

    private void drawScore(Graphics2D graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(FONT);

        graphics.drawString(String.format("%2d", maze.getScore()), Tile.SIZE * 5, Tile.SIZE * 2);
    }

    public void draw(Graphics2D graphics, int levelA) {
        for (Presenter tilePresenter : mazeTiles) {
            tilePresenter.draw(graphics);
        }
        drawScore(graphics, levelA);
        drawLevel(graphics, levelA);
    }

    private void drawScore(Graphics2D graphics , int levelA) {
        graphics.setColor(Color.white);
        graphics.setFont(FONT);

        graphics.drawString(String.format("%2d", maze.getScore()), Tile.SIZE * 5, Tile.SIZE * 2);
    }

    private void drawLevel(Graphics2D graphics, int levelA) {
        graphics.setColor(Color.white);
        graphics.setFont(FONT);

        String level= "Level: "+ levelA;
        graphics.drawString(level, Tile.SIZE * 10, Tile.SIZE * 2);
    }
}
