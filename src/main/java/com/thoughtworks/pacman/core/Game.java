package com.thoughtworks.pacman.core;


import java.awt.Dimension;
import java.util.Random;
import java.util.Set;

import com.thoughtworks.pacman.core.actors.FreezeItem;
import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.core.tiles.visitors.PacmanTileVisitor;





public class Game {
    private final Maze maze;
    private final Pacman pacman;
    private final Ghosts ghosts;
    private final PacmanTileVisitor pacmanTileVisitor;
    private final FreezeItem freezeItem = new FreezeItem(this, 1, 4);
    private Random random = new Random();
    private int FREEZE_COUNTER=200;
    private int PAUSE_TIME=0;

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

    public FreezeItem getFreezeItem() {
        return freezeItem;
    }

    public Ghost[] getGhosts() {
        return new Ghost[] {ghosts.getBlinky(), ghosts.getPinky(), ghosts.getInky(), ghosts.getClyde()};
    }

    public void advance(long timeDeltaInMillis) {
        if (pacman.isDead()) {
            return;
        }

        ghosts.freeGhostsBasedOnScore(maze.getScore());

        pacman.advance(timeDeltaInMillis);
        if(PAUSE_TIME >= 1)
            PAUSE_TIME--;
        else
            ghosts.advance(timeDeltaInMillis);
        
        if(!freezeItem.isVisible()){
            tryToCreateFreezeItem();
        }
        if(freezeItem.isVisible()){
            if(pacman.collidesWith(freezeItem)){
                freezeItem.pickedUp();
                setPauseTimeAfterCollide();
            }
            FREEZE_COUNTER--;
            if (FREEZE_COUNTER == 0){
                freezeItem.setVisible(false);
            }
        }

        if (ghosts.killed(pacman)) {
            pacman.die();
        }

        Tile pacmanTile = maze.tileAt(pacman.getCenter().toTileCoordinate());
        pacmanTile.visit(pacmanTileVisitor);
    }

    public void tryToCreateFreezeItem(){
        int r = random.nextInt(230);
        if (r == 0){
            TileCoordinate x =  maze.getDotCoordinate();
            freezeItem.setLocation(x);
            freezeItem.setRandomColor();
            freezeItem.setVisible(true);
            FREEZE_COUNTER = 150;
        }
    }
    public boolean setPauseTimeAfterCollide(){
        if (freezeItem.getColor() == 0) 
            PAUSE_TIME = 50;
        else if (freezeItem.getColor() == 1) 
            PAUSE_TIME = 100;
        else if (freezeItem.getColor() == 2) 
            PAUSE_TIME = 150;

        if (PAUSE_TIME == 50 || PAUSE_TIME == 100 || PAUSE_TIME == 150)
            return true;
        else
            return false;
    }

    public int getPauseTime(){
        return PAUSE_TIME;
    }




    public boolean won() {
        return !maze.hasDotsLeft();
    }

    public boolean lost() {
        return pacman.isDead();
    }
}
