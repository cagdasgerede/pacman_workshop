package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Game;

import java.io.Serializable;

public class Ghost extends Actor implements Serializable {
    private final GhostType type;
    private boolean free;

    public Ghost(Game game, GhostType type) {
        super(game.getMaze(), type.getMovementStrategy(game) , type.getStartCoordinate());
        this.type = type;
    }

    public void setSPEED(int speed){
        super.setSPEED(speed);
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
