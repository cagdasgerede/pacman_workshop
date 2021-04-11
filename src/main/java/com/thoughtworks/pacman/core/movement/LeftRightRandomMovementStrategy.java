package com.thoughtworks.pacman.core.movement;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LeftRightRandomMovementStrategy extends RandomMovementStrategy {

    private final Maze maze;
    private final Random random;

    private Direction desiredDirection;
    private Direction previousDirection;
    private Direction previousSquareDirection;
    private int willToGoToThePreviousDirection = 8;

    public LeftRightRandomMovementStrategy(SpacialCoordinate center, Maze maze) {
        this(center, maze, new Random());
    }

    public LeftRightRandomMovementStrategy(SpacialCoordinate center, Maze maze, Random random) {
        super(center, maze, random);
        this.maze = maze;
        this.random = random;
    }

    public Direction getDirection() {
        return desiredDirection;
    }

    public boolean isMoving() {
        return true;
    }

    public Direction getNextDirection(TileCoordinate currentTile) {
        List<Direction> availableDirections = getPossibleDirections(currentTile);

        if (!availableDirections.contains(Direction.LEFT) && !availableDirections.contains(Direction.RIGHT)) {
            desiredDirection = Direction.NONE;
            return desiredDirection;
        }

        int randomIndex = random.nextInt(availableDirections.size());
        for (int i=0; i<willToGoToThePreviousDirection; i++) {
            if (availableDirections.get(randomIndex) != previousDirection && availableDirections.get(randomIndex) != previousSquareDirection) {
                randomIndex = random.nextInt(availableDirections.size());
            }
            else {
                break;
            }
        }
        previousSquareDirection = previousDirection;
        previousDirection = desiredDirection;
        desiredDirection = availableDirections.get(randomIndex);

        return desiredDirection;
    }

    List<Direction> getPossibleDirections(TileCoordinate currentTile) {
        List<Direction> availableDirections = new ArrayList<Direction>();
        for (Direction direction : Direction.validMovements()) {
            TileCoordinate nextTile = currentTile.add(direction.tileDelta());
            if (maze.canMove(nextTile)) {
                if (direction == Direction.LEFT || direction == Direction.RIGHT) {
                    availableDirections.add(direction);
                }
            }
        }
        return availableDirections;
    }
}