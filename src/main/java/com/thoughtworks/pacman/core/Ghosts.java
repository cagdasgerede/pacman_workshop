package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.GhostType;
import com.thoughtworks.pacman.core.actors.Pacman;

public class Ghosts {
    private Ghost blinky;
    private Ghost pinky;
    private Ghost inky;
    private Ghost clyde;

    public Ghosts(Game game) {
        this(new Ghost(game, GhostType.BLINKY), new Ghost(game, GhostType.PINKY), new Ghost(game, GhostType.INKY),
                new Ghost(game, GhostType.CLYDE));
    }

    Ghosts(Ghost blinky, Ghost pinky, Ghost inky, Ghost clyde) {
        this.blinky = blinky;
        this.pinky = pinky;
        this.inky = inky;
        this.clyde = clyde;
    }

    public Ghost getBlinky() {
        return blinky;
    }

    public Ghost getPinky() {
        return pinky;
    }

    public Ghost getInky() {
        return inky;
    }

    public Ghost getClyde() {
        return clyde;
    }

    public void freeGhostsBasedOnScore(int score) {
        if (blinky.isTrapped()) {
            blinky.free();
        } else if (pinky.isTrapped()) {
            pinky.free();
        } else if (inky.isTrapped() && score > 300) {
            inky.free();
        } else if (clyde.isTrapped() && score > 600) {
            clyde.free();
        }        
    }

    public void advance(long timeDeltaInMillis, boolean isItStopped) {
        blinky.advance(timeDeltaInMillis, isItStopped);
        pinky.advance(timeDeltaInMillis, isItStopped);
        inky.advance(timeDeltaInMillis, isItStopped);
        clyde.advance(timeDeltaInMillis, isItStopped);
    }

    public boolean killed(Pacman pacman) {
        return pacman.collidesWith(blinky) || pacman.collidesWith(pinky) || pacman.collidesWith(inky)
                || pacman.collidesWith(clyde);
    }
}