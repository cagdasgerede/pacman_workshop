package com.thoughtworks.pacman.core;

import java.awt.Dimension;

import com.thoughtworks.pacman.core.actors.ClonePacman;
import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.Pacman;

import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

import com.thoughtworks.pacman.core.tiles.CloneItem;
import com.thoughtworks.pacman.core.tiles.Dot;

import com.thoughtworks.pacman.core.tiles.visitors.ClonePacmanTileVisitor;
import com.thoughtworks.pacman.core.tiles.visitors.PacmanTileVisitor;

public class Game {
    private final Maze maze;
    private final Pacman pacman;
    private final Ghosts ghosts;
    private final PacmanTileVisitor pacmanTileVisitor;
    private final ClonePacmanTileVisitor clonePacmanTileVisitor;

    private ClonePacman clonePacman;

    private static final int CLONE_ITEM_THRESHOLD = 100;
    private static final int CLONE_PACMAN_THRESHOLD = 150;
    private int cloneItemTimePassed = 0;
    private int clonePacmanTimePassed = 0;

    private boolean doesCloneItemExist = false;

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
        this.clonePacmanTileVisitor = new ClonePacmanTileVisitor();
    }

    public Game(Maze maze, Pacman pacman, Ghosts ghosts) {
        this.maze = maze;
        this.pacman = pacman;
        this.ghosts = ghosts;
        this.pacmanTileVisitor = new PacmanTileVisitor();
        this.clonePacmanTileVisitor = new ClonePacmanTileVisitor();
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

    public void addClonePacman() {
        if(this.hasClonePacman())
            return;

        this.clonePacman = new ClonePacman(maze, this.pacman.getCenter(), this.pacman.getDirection()); 
        this.maze.useCloneItem();
    }

    public ClonePacman getClonePacman() {
        return this.clonePacman;
    }

    public boolean hasClonePacman() {
        return this.clonePacman != null;
    }

    public void clonePacmanPresenceState() {
        java.util.Random random = new java.util.Random();
        int decision = random.nextInt(10);
        
        if(!this.doesCloneItemExist && decision == 1) {
            int mazeWidth = this.maze.getWidth();
            int mazeHeight = this.maze.getHeight();
            TileCoordinate tempCloneItemCoordinate = new TileCoordinate(random.nextInt(mazeWidth), random.nextInt(mazeHeight));
            if(this.maze.tileAt(tempCloneItemCoordinate) instanceof Dot) {
                this.maze.insertCloneItem(tempCloneItemCoordinate);
                this.doesCloneItemExist = true;
                this.cloneItemTimePassed = 0;
            }
        } else if(this.doesCloneItemExist) {
            if(this.cloneItemTimePassed == CLONE_ITEM_THRESHOLD) { 
                this.doesCloneItemExist = false;
                this.maze.removeCloneItem();
                this.cloneItemTimePassed = 0;
            } else {
                this.cloneItemTimePassed++;
            }
        }

        if(this.hasClonePacman()) {
            if(this.clonePacmanTimePassed == CLONE_PACMAN_THRESHOLD) {
                this.clonePacmanTimePassed = 0;
                this.clonePacman = null;
            } else {
                this.clonePacmanTimePassed++;
            }
        }
    }

    public void advance(long timeDeltaInMillis) {
        if (pacman.isDead()) {
            return;
        }

        this.clonePacmanPresenceState();

        ghosts.freeGhostsBasedOnScore(maze.getScore());

        pacman.advance(timeDeltaInMillis);
        ghosts.advance(timeDeltaInMillis);
        if(this.hasClonePacman())
            this.clonePacman.advance(timeDeltaInMillis);

        if (ghosts.killed(pacman)) {
            pacman.die();
        }

        Tile pacmanTile = maze.tileAt(pacman.getCenter().toTileCoordinate());
        if(this.hasClonePacman()) {
            Tile clonePacmanTile = maze.tileAt(this.clonePacman.getCenter().toTileCoordinate());
            clonePacmanTile.visit(clonePacmanTileVisitor);
        }
        if(pacmanTile instanceof CloneItem)
            this.maze.eatCloneItem();
        pacmanTile.visit(pacmanTileVisitor);
            
    }

    public boolean won() {
        return !maze.hasDotsLeft();
    }

    public boolean lost() {
        return pacman.isDead();
    }
}
