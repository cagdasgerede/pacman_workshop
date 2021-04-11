package com.thoughtworks.pacman.core.maze;

import java.awt.Dimension;
import java.util.Map;
import java.util.Random;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.SpecialCollectableItem;
import com.thoughtworks.pacman.core.tiles.visitors.DotsLeftVisitor;
import com.thoughtworks.pacman.core.tiles.visitors.ScoreTileVisitor;

public class Maze {
    private final Map<TileCoordinate, Tile> tiles;
    private final int width;
    private final int height;

    private SpecialCollectableItem item;
    private int numberOfType1Items = 0;
    private int numberOfType2Items = 0;
    private int numberOfType3Items = 0;
    private Tile lastTileBeforeItem;
    private TileCoordinate coordinateOfItem;

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

    public int getScore() {
        ScoreTileVisitor scoreVisitor = new ScoreTileVisitor();
        int totalScore = 0;
        for (Tile tile : tiles.values()) {
            totalScore += tile.visit(scoreVisitor);
        }
        return totalScore;
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

    public void putItem(TileCoordinate coordinateOfItem) {
        this.item = new SpecialCollectableItem(coordinateOfItem, ((new Random()).nextInt(3)+1) );
        this.coordinateOfItem = coordinateOfItem;
        this.lastTileBeforeItem = this.tiles.get(coordinateOfItem);
        this.tiles.put(coordinateOfItem, this.item);
    }

    public void removeItem() {
        this.tiles.replace(this.coordinateOfItem, this.lastTileBeforeItem);
        this.lastTileBeforeItem = null;
        this.coordinateOfItem = null;
        this.item = null;
    }

    public void eatItem() {
        if (!this.itemExists()) {
            return;
        }
        else {
            this.item.eat();
            if (this.item.getKind() == 1) {
                this.numberOfType1Items += 1;
            }
            else if (this.item.getKind() == 2) {
                this.numberOfType2Items += 1;
            }
            else if (this.item.getKind() == 3) {
                this.numberOfType3Items += 1;
            }
            this.removeItem();
        }
    }

    public boolean useType1Item() {
        if (this.numberOfType1Items == 0) {
            return false;
        }
        else {
            this.numberOfType1Items -= 1;
            return true;
        }
    }

    public boolean useType2Item() {
        if (this.numberOfType2Items == 0) {
            return false;
        }
        else {
            this.numberOfType2Items -= 1;
            return true;
        }
    }

    public boolean useType3Item() {
        if (this.numberOfType3Items == 0) {
            return false;
        }
        else {
            this.numberOfType3Items -= 1;
            return true;
        }
    }

    public SpecialCollectableItem getItem() {
        return this.item;
    }

    public int getNumberOfType1Items() {
        return this.numberOfType1Items;
    }

    public int getNumberOfType2Items() {
        return this.numberOfType2Items;
    }

    public int getNumberOfType3Items() {
        return this.numberOfType3Items;
    }

    public TileCoordinate getCoordinateOfItem() {
        return this.coordinateOfItem;
    }

    public boolean itemExists() {
        return (this.item != null);
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
