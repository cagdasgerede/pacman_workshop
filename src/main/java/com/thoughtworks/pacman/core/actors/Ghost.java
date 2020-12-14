package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Game;

public class Ghost extends Actor {
    private final GhostType type;
    private boolean free;
    private boolean freeze = false; //yeni

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

    public void freeze(){//yeni
        freeze = true;
    }

    public void notFreeze(){//yeni
        freeze = false;
    }

    public boolean isFreezed(){//yeni
        return !freeze;
    }

    @Override
    protected boolean isHalted() {
        return isTrapped();
    }
}
