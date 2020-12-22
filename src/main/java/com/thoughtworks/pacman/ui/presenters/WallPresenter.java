package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.Wall;
import com.thoughtworks.pacman.core.tiles.WallType;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class WallPresenter implements Presenter {
    private final Wall wall;

    public WallPresenter(Wall wall, int xOffset) {
        TileCoordinate coordinate = wall.getCoordinate();
        WallType type = wall.getType();
        this.wall = new Wall(new TileCoordinate(coordinate.getX() + xOffset / Tile.SIZE, coordinate.getY()), type);
    }

    public WallPresenter(Wall wall) {
        this.wall = wall;
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.blue);
        graphics.setStroke(new BasicStroke(2.5f));
        graphics.draw(wall.getShape());
    }

    public Wall getWall() {
        return wall;
    }
}
