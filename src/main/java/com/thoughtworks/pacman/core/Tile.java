package com.thoughtworks.pacman.core;

public abstract class Tile {
    public static int SIZE = 16;

    private  TileCoordinate coordinate;

    public Tile(TileCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public SpacialCoordinate getCenter() {
        return coordinate.toSpacialCoordinate();
    }

    public void setCenter(TileCoordinate tileCoordinate) {
        this.coordinate = tileCoordinate;
    }

    public abstract boolean isMovable();
    
    public abstract boolean isDropped();

    public abstract <T> T visit(TileVisitor<T> visitor);
}
