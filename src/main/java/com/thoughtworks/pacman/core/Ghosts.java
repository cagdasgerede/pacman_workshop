package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.GhostType;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;

public class Ghosts {
    private Ghost blinky;
    private Ghost pinky;
    private Ghost inky;
    private Ghost clyde;

    public Ghosts(Game game) {
        int level = game.getLevel();
        if (game.getLevel() <= 7){
            this.blinky = new Ghost(game, GhostType.BLINKY);
            this.pinky = new Ghost(game, GhostType.PINKY);
            this.clyde = new Ghost(game, GhostType.CLYDE);
            this.inky = new Ghost(game, GhostType.INKY);
        }else{
            this.blinky = new Ghost(game, GhostType.BLINKY);
            this.blinky.SPEEDIncreaseBy(level-7);
            this.pinky = new Ghost(game, GhostType.PINKY);
            this.pinky.SPEEDIncreaseBy(level-7);
            this.clyde = new Ghost(game, GhostType.CLYDE);
            this.clyde.SPEEDIncreaseBy(level-7);
            this.inky = new Ghost(game, GhostType.INKY);
            this.inky.SPEEDIncreaseBy(level-7);
        }

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
        if (blinky != null && blinky.isTrapped()) {
            blinky.free();
        } else if (pinky != null && pinky.isTrapped()) {
            pinky.free();
        } else if (inky != null && inky.isTrapped() && score > 300) {
            inky.free();
        } else if (clyde != null && clyde.isTrapped() && score > 600) {
            clyde.free();
        }        
    }

    public void freeGhostsBasedOnScoreAndLevel(int score, int level) {
        if (blinky != null && blinky.isTrapped()) {
            blinky.free();
        } else if (pinky != null && pinky.isTrapped() && level > 2 && score > 150) {
            pinky.free();
        } else if (inky != null && inky.isTrapped() && level > 4 && score > 300) {
            inky.free();
        } else if (clyde != null && clyde.isTrapped() && level > 6 && score > 600) {
            clyde.free();
        }
    }

    public void advance(long timeDeltaInMillis) {
        if (blinky != null){
            blinky.advance(timeDeltaInMillis);
        }
        if (pinky != null) {
            pinky.advance(timeDeltaInMillis);
        }
        if (inky != null) {
            inky.advance(timeDeltaInMillis);
        }
        if (clyde != null) {
            clyde.advance(timeDeltaInMillis);
        }
    }

    public boolean killed(Pacman pacman) {
        if(blinky != null && pacman.collidesWith(blinky)){
            return true;
        }else if(pinky != null && pacman.collidesWith(pinky)){
            return true;
        }else if(inky != null && pacman.collidesWith(inky)){
            return true;
        }else if(clyde != null && pacman.collidesWith(clyde)){
            return true;
        }
        return false;
    }
}