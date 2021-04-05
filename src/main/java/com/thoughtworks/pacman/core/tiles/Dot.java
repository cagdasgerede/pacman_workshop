package com.thoughtworks.pacman.core.tiles;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.TileVisitor;
import com.thoughtworks.pacman.ui.Sound;

public class Dot extends Tile {
    Sound s;
    private boolean eaten;

    public Dot(TileCoordinate coordinate, String value) {
        super(coordinate);    
    }

    public Dot(TileCoordinate coordinate) {
        super(coordinate);
    }

    public boolean isEaten() {
        return eaten;
    }

    public void eat() {
     if(this.eaten==false){
        s =  new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        s.eatDot();
     }
        this.eaten = true;
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public <T> T visit(TileVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return ".";
    }
}
