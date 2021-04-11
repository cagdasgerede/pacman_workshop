package com.thoughtworks.pacman.core.movement;

import java.util.Timer;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;

class ToggleFeignTask extends TimerTask {
    TargetFeignUninterestMovementStrategy strat;

    ToggleFeignTask(TargetFeignUninterestMovementStrategy strat) {
        this.strat = strat;
    }

    public void run() {
        strat.toggleMode();
    }
}

public class TargetFeignUninterestMovementStrategy implements MovementStrategy {
    private static enum State {
        WANDER, FEIGN, CHASE;
    };

    private final Maze maze;
    private final Random random;
    private final Pacman pacman;

    private TileCoordinate previousTile;
    private Direction desiredDirection;
    private ToggleFeignTask toggleTask;
    private State state;
    private Timer timer;

    public TargetFeignUninterestMovementStrategy(SpacialCoordinate center, Maze maze, Pacman pacman) {
        this.maze = maze;
        this.pacman = pacman;
        this.state = State.WANDER;
        this.random = new Random();
        this.timer = new Timer();

        this.toggleTask = new ToggleFeignTask(this);

        this.timer.scheduleAtFixedRate(this.toggleTask, 20000, 20000);
        jump(center.toTileCoordinate());
    }

    public Direction getNextDirection(TileCoordinate currentTile) {
        List<Direction> availableDirections = getPossibleDirections(currentTile);

        // No choice anyway so use the fastpath
        if (availableDirections.size() < 3) {
            return wanderDirection(currentTile, availableDirections);
        }

        switch (state) {
        case WANDER:
            return wanderDirection(currentTile, availableDirections);
        case FEIGN:
            return feignDirection(currentTile, availableDirections);
        case CHASE:
            return chaseDirection(currentTile, availableDirections);
        }

        // Unreachable
        System.out.printf("Unreachable state: feign interest\n");
        System.exit(0);
        return Direction.NONE;
    }

    public void toggleMode() {
        state = (state == State.WANDER) ? State.FEIGN : State.WANDER;
    }

    public void jump(TileCoordinate tileCoordinate) {
        this.previousTile = null;
        this.desiredDirection = Direction.NONE;
    }

    public Direction getDirection() {
        return desiredDirection;
    }

    public boolean isMoving() {
        return true;
    }

    private List<Direction> getPossibleDirections(TileCoordinate currentTile) {
        List<Direction> availableDirections = new ArrayList<Direction>();
        for (Direction direction : Direction.validMovements()) {
            TileCoordinate nextTile = currentTile.add(direction.tileDelta());
            if (maze.canMove(nextTile) && !nextTile.remainder(maze).equals(previousTile)) {
                availableDirections.add(direction);
            }
        }
        return availableDirections;
    }

    private Direction wanderDirection(TileCoordinate currentTile, List<Direction> availableDirections) {
        if (!currentTile.equals(previousTile)) {
            int randomIndex = random.nextInt(availableDirections.size());
            desiredDirection = availableDirections.get(randomIndex);
            previousTile = currentTile;
        }
        return desiredDirection;
    }

    /*
     * This will only try to reduce the coordinate instead of tailgating so that it
     * is possible to manuver around it
     */
    private Direction chaseDirection(TileCoordinate currentTile, List<Direction> availableDirections) {
        if (!currentTile.equals(previousTile)) {
            TileCoordinate delta = currentTile.subtract(pacman.getCenter().toTileCoordinate());

            // Select available with priorty right > up > left > down
            if (delta.y() >= 0 && availableDirections.contains(Direction.UP)) {
                desiredDirection = Direction.UP;
            } else if (delta.x() >= 0 && availableDirections.contains(Direction.LEFT)) {
                desiredDirection = Direction.LEFT;
            } else if (delta.x() <= 0 && availableDirections.contains(Direction.RIGHT)) {
                desiredDirection = Direction.RIGHT;
            } else if (delta.y() <= 0 && availableDirections.contains(Direction.DOWN)) {
                desiredDirection = Direction.DOWN;
            }

            previousTile = currentTile;
        }
        return desiredDirection;
    }

    private Direction feignDirection(TileCoordinate currentTile, List<Direction> availableDirections) {
        double delta = currentTile.distanceTo(pacman.getCenter().toTileCoordinate());

        if (delta < 8) {
            state = State.CHASE;
            return chaseDirection(currentTile, availableDirections);
        }

        return wanderDirection(currentTile, availableDirections);
    }
}
