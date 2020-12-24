package com.thoughtworks.pacman.core.maze;

import java.awt.Dimension;
import java.util.Map;
import java.util.ArrayList;

import com.thoughtworks.pacman.core.AllSpecialItems;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.FreezingItem;
import com.thoughtworks.pacman.core.tiles.visitors.DotsLeftVisitor;
import com.thoughtworks.pacman.core.tiles.visitors.ScoreTileVisitor;

public class Maze {
    private final Map<TileCoordinate, Tile> tiles;
    private final ArrayList<AllSpecialItems> allitems;
    private final int width;
    private final int height;

    Maze(int width, int height, Map<TileCoordinate, Tile> tiles) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        allitems = new ArrayList<AllSpecialItems>();
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

    public Tile getFreezingItem(int index) {
        if(allitems.get(index) instanceof FreezingItem) {
            return allitems.get(index);
        }
       return null;
    }

    public void eat(AllSpecialItems specialItems) {
        if(specialItems == null){
            return;
        }
        for(int i = 0;i<allitems.size();i++) {
            if(allitems.get(i).equals(specialItems)) {
                allitems.get(i).eat();
                removeFreezingItem(i);
            }
        }
    }

    public void insert(AllSpecialItems specialItems) {
        allitems.add(specialItems);
    }

    public void removeFreezingItem(int index) {
       for(int i = 0;i<allitems.size();i++) {
            if(allitems.get(i) instanceof FreezingItem && i == index) {
                allitems.remove(index);
            }
       }
    }

    public boolean isFreezingItemExist(int index) {
        if(allitems.get(index) instanceof FreezingItem) {
            return true;
        } 
        return false;
    }

    public boolean isFreezingItemExist(FreezingItem freezingItem) {
        if(freezingItem == null) {
            return false;
        }
        for(int i = 0; i < allitems.size();i++) {
                if(allitems.get(i).equals(freezingItem)) {
                    return true;
                }
        }
        return false;
    }

    public ArrayList<AllSpecialItems> getAllItems() {
        return allitems;
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
