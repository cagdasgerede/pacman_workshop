package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.TileCoordinate;

import java.util.Random;

public class FreezeItem extends Actor {
    
    private final int type;
    private boolean visible=false;
    private int color;
    Random random = new Random(); 

    public FreezeItem(Game game, int x , int y) {
        super(game.getMaze(), null, new SpacialCoordinate(x*16+8,y*16+8));
        Random rnd=new Random();        
        this.type = rnd.nextInt(4);
    }

    public void setLocation(TileCoordinate coordinate) {
        setCenter(new SpacialCoordinate(coordinate.getX()*16+8, coordinate.getY()*16+8));
        this.color = random.nextInt(3);
    }

    public void pickedUp(){
        visible = false;
    }

    public int getColor(){
        return color;
    }

    public void setRandomColor(){
        this.color = random.nextInt(3);
    }

    public int getType() {
        return type;
    }

    public void setVisible(boolean visible) {
        this.visible=visible;

    }

    public boolean isVisible(){
        return visible;
    }

    @Override
    protected boolean isHalted() {
        return false;
    }

    
}
