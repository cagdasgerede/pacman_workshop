package com.thoughtworks.pacman.core.tiles;

import com.thoughtworks.pacman.core.AllSpecialItems;
import com.thoughtworks.pacman.core.Ghosts;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.TileVisitor;

public class FreezingItem extends AllSpecialItems {
    private long timeCounter = 0;
    private long timeForTheFreezingItemDisappear = 0;
    private long timeForGhostsToMoveAfterFreezing = 0;
    private boolean isItemEaten = false;
    private boolean isGhostsFreezing = false;
    private boolean eaten;
    private FreezingItem previousFreezingItem;

    public FreezingItem(TileCoordinate coordinate, String value) {
        super(coordinate);
    }

    public FreezingItem(TileCoordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void reset(Maze maze) {
        maze.eat(previousFreezingItem);
        timeCounter = 0;
        timeForTheFreezingItemDisappear = 0;
        isItemEaten = true;
        isGhostsFreezing = true;
    }

    @Override
    public void feature(Ghosts ghosts, Maze maze) {
        if(isItemEaten){
           
            if(isGhostsFreezing) {
                ghosts.getBlinky().freeze();
                ghosts.getPinky().freeze();
                ghosts.getInky().freeze();
                ghosts.getClyde().freeze();
                isGhostsFreezing = false;
            }
            timeForGhostsToMoveAfterFreezing++;
            
        } else {
            
            if(!isGhostsFreezing) {
                ghosts.getBlinky().removeFreeze();
                ghosts.getPinky().removeFreeze();
                ghosts.getInky().removeFreeze();
                ghosts.getClyde().removeFreeze();
                timeForGhostsToMoveAfterFreezing = 0;
                isGhostsFreezing = true;
            }            
        }

        if(timeForGhostsToMoveAfterFreezing % 100 == 99 && isItemEaten) {
            isItemEaten = false;
        }

        if(timeCounter % 200 == 199) {
            timeForTheFreezingItemDisappear++;
        }

        timeCounter++;

        int minX = 1;
        int maxX = maze.getWidth() - 2;
        int minY = 4;
        int maxY = maze.getHeight() - 4;
        int random_intX;
        int random_intY;

        if(!maze.isFreezingItemExist(previousFreezingItem)) {
                if(timeForTheFreezingItemDisappear % 2 == 1) {               
                    while(true){
                        random_intX = (int)(Math.random() * (maxX - minX + 1) + minX);
                        random_intY = (int)(Math.random() * (maxY - minY + 1) + minY);
                        TileCoordinate freezingItemCoordinate = new TileCoordinate(random_intX,random_intY);
                        if(maze.canMove(freezingItemCoordinate) && !(maze.tileAt(freezingItemCoordinate) instanceof EmptyTile)) {
                            maze.tileAt(freezingItemCoordinate);
                            previousFreezingItem = new FreezingItem(freezingItemCoordinate);            
                            maze.insert(previousFreezingItem);
                            break;
                        }                   
                    }          
                }            
        } else {
            if(timeForTheFreezingItemDisappear % 2 == 0){
                maze.eat(previousFreezingItem);
            }
        }
    }
   
    @Override
    public boolean isEaten() {
        return eaten;
    }

    @Override
    public void eat() {
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
        return "freezing item";
    }

}
