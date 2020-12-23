package com.thoughtworks.pacman.core.tiles;

import com.thoughtworks.pacman.core.TileCoordinate;

public class FreezingItemBomb extends Item {
    
    public FreezingItemBomb(TileCoordinate coordinate) {
        super(coordinate);
    }

    public FreezingItemBomb(TileCoordinate coordinate, String value){
        super(coordinate);
    }
    
    @Override
    public void eat() {
        return;
    }

    @Override
    public String toString() {
        return "0";
    }
    
}
