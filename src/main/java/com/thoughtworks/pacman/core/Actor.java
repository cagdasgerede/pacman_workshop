package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.movement.MovementStrategy;


public abstract class Actor {
    private static final int SPEED = 100;

    protected final Maze maze;
    protected MovementStrategy movementStrategy;
    private SpacialCoordinate center;

    public Actor(Maze maze, MovementStrategy movementStrategy, SpacialCoordinate center) {
        this.maze = maze;
        this.movementStrategy = movementStrategy;
        this.center = center;
    }
   
    public SpacialCoordinate getCenter() {
        return center;
    }

    public boolean collidesWith(Actor other) {
        return center.toTileCoordinate().equals(other.center.toTileCoordinate());
    }

    protected void jump(SpacialCoordinate jumpTo) {
        center = jumpTo;
        movementStrategy.jump(center.toTileCoordinate());
    }

    public void advance(long timeDeltaInMillis) {
        if (!isHalted()) {
            advanceDistance((int) (SPEED * timeDeltaInMillis / 1000));
        }
    }

    protected abstract boolean isHalted();

    private void advanceDistance(int distance) {
        TileCoordinate currentTile = center.toTileCoordinate();
        TileCoordinate nextTile = getNextTile(currentTile);
        SpacialCoordinate nextTileCenter = nextTile.toSpacialCoordinate();

        SpacialCoordinate subtract = nextTileCenter.subtract(center);
        if (subtract.isDiagonal()) {
            SpacialCoordinate currentTileCenter = currentTile.toSpacialCoordinate();
            distance = distance - currentTileCenter.subtract(center).magnitude();
            subtract = nextTileCenter.subtract(currentTileCenter);
            center = currentTileCenter;
        }

        if (subtract.magnitude() > 0) {
            if (subtract.magnitude() == distance) {
                center = nextTileCenter.remainder(maze);
            } else if (subtract.magnitude() < distance) {
                center = nextTileCenter.remainder(maze);
                advanceDistance(distance - subtract.magnitude());
            } else {
                SpacialCoordinate movement = subtract.unit().times(distance);
                center = center.add(movement).remainder(maze);
            }
        }
    }
    public TileCoordinate addTeleport(TileCoordinate currentTile){
       int coordinateX=(int)(Math.random()*maze.getWidth());
       int coordinateY=(int)(Math.random()*maze.getHeight());
       while(maze.canMove(new TileCoordinate(coordinateX, coordinateY))){
           coordinateX=(int)(Math.random()*maze.getWidth());
           coordinateY=(int)(Math.random()*maze.getHeight());
       }
       
       currentTile.add(new TileCoordinate(coordinateX, coordinateY));
       return currentTile;
   }
   private boolean allowMove(TileCoordinate tileCoordinate, Direction direction) {
    TileCoordinate nextTile = tileCoordinate.add(direction.tileDelta());
    return maze.canMove(nextTile);
    }
    private TileCoordinate getNextTile(TileCoordinate currentTile) {
        Direction nextDirection = movementStrategy.getNextDirection(currentTile);
        if(!allowMove(currentTile,nextDirection)){         
            addTeleport(currentTile);
        }
        
        
        return currentTile.add(nextDirection.tileDelta());
    }
}
