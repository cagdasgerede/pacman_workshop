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

        graphics.setColor(Color.cyan);
        graphics.drawString("#1:", Tile.SIZE*9, Tile.SIZE*2);
        graphics.drawString(String.format("%2d", maze.getNumberOfType1Items()), Tile.SIZE*11, Tile.SIZE*2);

        graphics.setColor(Color.green);
        graphics.drawString("#2:", Tile.SIZE*14, Tile.SIZE*2);
        graphics.drawString(String.format("%2d", maze.getNumberOfType2Items()), Tile.SIZE*16, Tile.SIZE*2);

        graphics.setColor(Color.red);
        graphics.drawString("#3:", Tile.SIZE*19, Tile.SIZE*2);
        graphics.drawString(String.format("%2d", maze.getNumberOfType3Items()), Tile.SIZE*21, Tile.SIZE*2);
    }

    public void drawSpecialCollectableItem(Graphics2D graphics) {
        if (this.maze.itemExists()) {
            (toPresenter(this.maze.getItem())).draw(graphics);
        }
    }
}
