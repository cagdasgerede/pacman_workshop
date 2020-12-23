package com.thoughtworks.pacman.core;

import java.awt.Dimension;
import java.util.Random;

import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.FreezingItem;
import com.thoughtworks.pacman.core.tiles.FreezingItemBomb;
import com.thoughtworks.pacman.core.tiles.visitors.PacmanTileVisitor;

public class Game {
    private final Maze maze;
    private final Pacman pacman;
    private final Ghosts ghosts;
    private final PacmanTileVisitor pacmanTileVisitor;
    private final int maxAliveTime = 200;
    private final int ghostFreezeTime = 100;
    private final int defaultGhostSpeed = 100;
    private final int spawnFrequency = 15; // Lower is more frequent.

    private int activeBombCount = 0;
    private int freezingItemTimePassed = 0;

    private int blinkyFreezingTimePassed = 0;
    private int pinkyFreezingTimePassed = 0;
    private int inkyFreezingTimePassed = 0;
    private int clydeFreezingTimePassed = 0;

    public Game() throws Exception {
        this(MazeBuilder.buildWalledMaze());
    }

    private Game(Maze maze) {
        this(maze, new Pacman(maze));
    }

    private Game(Maze maze, Pacman pacman) {
        this.maze = maze;
        this.pacman = pacman;
        this.ghosts = new Ghosts(this);
        this.pacmanTileVisitor = new PacmanTileVisitor();
    }

    public Game(Maze maze, Pacman pacman, Ghosts ghosts) {
        this.maze = maze;
        this.pacman = pacman;
        this.ghosts = ghosts;
        this.pacmanTileVisitor = new PacmanTileVisitor();
    }

    public Maze getMaze() {
        return maze;
    }

    public Dimension getDimension() {
        return maze.getDimension();
    }

    public Pacman getPacman() {
        return pacman;
    }

    public Ghost[] getGhosts() {
        return new Ghost[] {ghosts.getBlinky(), ghosts.getPinky(), ghosts.getInky(), ghosts.getClyde()};
    }

    public void dropBomb() {
        this.maze.insertFreezingItemBomb(pacman.getCenter().toTileCoordinate());
        activeBombCount++;
    }

    public void advance(long timeDeltaInMillis) {
        if (pacman.isDead()) {
            return;
        }

        Ghost[] ghostsArray = getGhosts();
        if(activeBombCount>0){
            for(int i = 0; i <= ghostsArray.length; i++){
                if(this.maze.tileAt(ghostsArray[i].getCenter().toTileCoordinate()) instanceof FreezingItemBomb){
                    this.maze.removeFreezingItemBomb(ghostsArray[i].getCenter().toTileCoordinate());
                    activeBombCount--;
                    switch(i) {
                        case 0:
                            ghosts.getBlinky().setSpeed(0);
                            blinkyFreezingTimePassed = 0;
                            break;
                        case 1:
                            ghosts.getPinky().setSpeed(0);
                            pinkyFreezingTimePassed = 0;
                            break;
                        case 2:
                            ghosts.getInky().setSpeed(0);
                            inkyFreezingTimePassed = 0;
                            break;
                        case 3:
                            ghosts.getClyde().setSpeed(0);
                            clydeFreezingTimePassed = 0;
                            break;
                   }
                }
            }
        }

        for(int i = 0; i <= ghostsArray.length; i++){
                switch(i) {
                    case 0:
                        if(blinkyFreezingTimePassed>ghostFreezeTime)
                            ghosts.getBlinky().setSpeed(defaultGhostSpeed);
                        else{
                            blinkyFreezingTimePassed++;
                        }
                        break;
                    case 1:
                        if(pinkyFreezingTimePassed>ghostFreezeTime)
                            ghosts.getPinky().setSpeed(defaultGhostSpeed);
                        else{
                            pinkyFreezingTimePassed++;
                        }
                        break;
                    case 2:
                        if(inkyFreezingTimePassed>ghostFreezeTime)
                            ghosts.getInky().setSpeed(defaultGhostSpeed);
                        else{
                            inkyFreezingTimePassed++;
                        }
                        break;
                    case 3:
                        if(clydeFreezingTimePassed>ghostFreezeTime)
                            ghosts.getClyde().setSpeed(defaultGhostSpeed);
                        else{
                            clydeFreezingTimePassed++;
                        }
                        break;
                }
        }
        
        Random r = new Random();
        int spawnDecision = r.nextInt(spawnFrequency);

        if(this.maze.isFreezingItemAlive() == false && spawnDecision == 1) {
            int width = this.maze.getWidth();
            int height = this.maze.getHeight();
            Random r1 = new Random();
            Random r2 = new Random();
            TileCoordinate tempCoordinate = new TileCoordinate(r1.nextInt(width), r2.nextInt(height));
            if(this.maze.tileAt(tempCoordinate) instanceof Dot){
                this.maze.insertFreezingItem(tempCoordinate);
                freezingItemTimePassed = 0;
            }
        }
        else if(this.maze.isFreezingItemAlive()){
            if(freezingItemTimePassed == maxAliveTime){
                this.maze.removeFreezingItem();
                freezingItemTimePassed = 0;
            }
            else{
                freezingItemTimePassed++;
            }
        }

        ghosts.freeGhostsBasedOnScore(maze.getScore());

        pacman.advance(timeDeltaInMillis);
        ghosts.advance(timeDeltaInMillis);

        if (ghosts.killed(pacman)) {
            pacman.die();
        }

        Tile pacmanTile = maze.tileAt(pacman.getCenter().toTileCoordinate());
        if(pacmanTile instanceof FreezingItem) {
            this.maze.eatFreezingItem();
        }
        pacmanTile.visit(pacmanTileVisitor);
    }

    public boolean won() {
        return !maze.hasDotsLeft();
    }

    public boolean lost() {
        return pacman.isDead();
    }
}
