package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.movement.RandomMovementStrategy;

public class Ghost extends Actor {
    private final GhostType type;
    private boolean free;

    public Ghost(Game game, GhostType type) {
        super(game.getMaze(), type.getMovementStrategy(game) , type.getStartCoordinate());
        this.type = type;
    }

    public GhostType getType() {
        return type;
    }

    public boolean isTrapped() {
        return !free;
    }

    public void free() {
        jump(GhostType.doorExit());
        free = true;
    }

    @Override
    protected boolean isHalted() {
        return isTrapped();
    }
}
