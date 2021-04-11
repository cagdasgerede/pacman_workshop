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

        graphics.setColor(Color.blue);
        graphics.drawString("Type 1 Special Item:", Tile.SIZE*4, Tile.SIZE*2);
        graphics.drawString(String.format("%2d", maze.getNumberOfType1Items()), Tile.SIZE*4, Tile.SIZE*2);

        graphics.setColor(Color.green);
        graphics.drawString("Type 2 Special Item:", Tile.SIZE*4, Tile.SIZE*2);
        graphics.drawString(String.format("%2d", maze.getNumberOfType2Items()), Tile.SIZE*4, Tile.SIZE*2);

        graphics.setColor(Color.red);
        graphics.drawString("Type 3 Special Item:", Tile.SIZE*4, Tile.SIZE*2);
        graphics.drawString(String.format("%2d", maze.getNumberOfType3Items()), Tile.SIZE*4, Tile.SIZE*2);
    }

    public void drawSpecialCollectableItem(Graphics2D graphics) {
        if (this.maze.specialCollectableItemExists) {
            (toPresenter(this.maze.getItem())).draw(graphics);
        }
    }
}
