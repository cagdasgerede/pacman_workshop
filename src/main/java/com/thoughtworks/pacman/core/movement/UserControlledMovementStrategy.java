package com.thoughtworks.pacman.core.movement;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;

public class UserControlledMovementStrategy implements MovementStrategy {// place Detected to be changed to do the issue
    private final Maze maze;
    private Direction desiredDirection;
    private Direction previousDirection;
    private Direction direction;
    private int coordinateX;
    private int coordinateY;
    public UserControlledMovementStrategy(Maze maze, Direction startDirection) {
        this.maze = maze;
        this.direction = startDirection;
        this.desiredDirection = startDirection;
    }
    public void addTeleport(TileCoordinate currentTile){
         coordinateX=(int)(Math.random()*maze.getWidth());
         coordinateY=(int)(Math.random()*maze.getHeight());
        while(maze.canMove(new TileCoordinate(coordinateX, coordinateY))){
            coordinateX=(int)(Math.random()*maze.getWidth());
            coordinateY=(int)(Math.random()*maze.getHeight());
        }
        
        currentTile.add(new TileCoordinate(coordinateX, coordinateY));
    }
    public int getCoordinateX(){
        return coordinateX;
    }
    public int getCoordinateY(){
        return coordinateY;
    }
    public void setNextDirection(Direction nextDirection) {
       // TileCoordinate currentTile = nextDirection.tileDelta();
       /* if(!maze.canMove(currentTile)){
            this.desiredDirection=(new Direction(currentTile));
        }*/
        this.desiredDirection = nextDirection;
    }

    public Direction getDirection() {
        return isMoving() ? direction : previousDirection;
    }

    public boolean isMoving() {
        return direction != Direction.NONE;
    }

    public Direction getNextDirection(TileCoordinate currentTile) {
        if (allowMove(currentTile, desiredDirection)) {
            direction = desiredDirection;
        } else if (!allowMove(currentTile, direction)) {
            addTeleport(currentTile);
            if(direction ==Direction.UP)
                direction = Direction.DOWN;
            else if(direction ==Direction.DOWN)
                direction = Direction.UP;
            else if(direction ==Direction.RIGHT)
                direction = Direction.LEFT;
            else
                direction = Direction.RIGHT;
            previousDirection = direction;
            direction = Direction.NONE;
        }
        return direction;
    }

    public void jump(TileCoordinate tileCoordinate) {
        throw new UnsupportedOperationException("User can't request to jump");
    }

    private boolean allowMove(TileCoordinate tileCoordinate, Direction direction) {
        TileCoordinate nextTile = tileCoordinate.add(direction.tileDelta());
        return maze.canMove(nextTile);
    }
}