package com.thoughtworks.pacman.core.maze;

import java.awt.Dimension;
import java.util.Map;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.visitors.DotsLeftVisitor;
import com.thoughtworks.pacman.core.tiles.visitors.ScoreTileVisitor;

import com.thoughtworks.pacman.core.tiles.CloneItem;

public class Maze {
    private final Map<TileCoordinate, Tile> tiles;
    private final int width;
    private final int height;

    private TileCoordinate cloneItemCoordinate;
    private Tile prevTile;
    private int cloneItemEaten = 0;
    private CloneItem activeCloneItem;

    Maze(int width, int height, Map<TileCoordinate, Tile> tiles) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
    }

    public boolean canMove(TileCoordinate tileCoordinate) {
        return tileAt(tileCoordinate).isMovable();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Dimension getDimension() {
        return new Dimension(width * Tile.SIZE, height * Tile.SIZE);
    }

    public Tile getCloneItem()
    {
        return this.activeCloneItem;
    }

    public int getScore() {
        ScoreTileVisitor scoreVisitor = new ScoreTileVisitor();
        int totalScore = 0;
        for (Tile tile : tiles.values()) {
            totalScore += tile.visit(scoreVisitor);
        }
        return totalScore;
    }

    public void insertCloneItem(TileCoordinate cloneItemCoordinate)
    {
        this.activeCloneItem = new CloneItem(cloneItemCoordinate);
        this.cloneItemCoordinate = cloneItemCoordinate;
        this.prevTile = this.tiles.get(cloneItemCoordinate);
        this.tiles.put(cloneItemCoordinate, this.activeCloneItem); 
    }

    public TileCoordinate getCloneItemCoordinate()
    {
        return this.cloneItemCoordinate;
    }

    public boolean isCloneItemPresent()
    {
        return this.activeCloneItem != null;
    }

    public void removeCloneItem()
    {
        this.tiles.replace(this.cloneItemCoordinate, this.prevTile);
        this.prevTile = null;
        this.cloneItemCoordinate = null;
        this.cloneItemCoordinate = null;
        this.activeCloneItem = null;
    }

    public void eatCloneItem()
    {
        if(this.activeCloneItem == null)
            return;

        this.activeCloneItem.eat();
        this.cloneItemEaten++;
        this.removeCloneItem();
    }

    public void useCloneItem()
    {
        if(this.cloneItemEaten == 0)
            return;
        
        this.cloneItemEaten--;
    }

    public int getEatenCloneItemCount()
    {
        return this.cloneItemEaten;
    }

    public boolean hasDotsLeft() {
        DotsLeftVisitor dotsLeftVisitor = new DotsLeftVisitor();
        int dotsLeft = 0;
        for (Tile tile : tiles.values()) {
            dotsLeft += tile.visit(dotsLeftVisitor);
        }
        return dotsLeft > 0;
    }

    public Tile tileAt(TileCoordinate tileCoordinate) {
        if (tiles.containsKey(tileCoordinate)) {
            return tiles.get(tileCoordinate);
        } else {
            return new EmptyTile(tileCoordinate);
        }
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.append(tileAt(new TileCoordinate(x, y)));
            }
            result.append("\n");
        }

        return result.toString();
    }
}
