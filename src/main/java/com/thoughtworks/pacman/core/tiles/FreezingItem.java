package com.thoughtworks.pacman.core.tiles;

import com.thoughtworks.pacman.core.TileCoordinate;

public class FreezingItem extends Item {

    public FreezingItem(TileCoordinate coordinate) {
        super(coordinate);
    }

    public FreezingItem(TileCoordinate coordinate, String value){
        super(coordinate);
    }
    
    @Override
    public String toString() {
        return "*";
    }
    
}
