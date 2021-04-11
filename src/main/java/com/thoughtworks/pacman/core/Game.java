package com.thoughtworks.pacman.core;

import java.awt.Dimension;

import java.util.Random;

import com.thoughtworks.pacman.core.actors.ClonePacman;
import com.thoughtworks.pacman.core.actors.ClonePacman1;
import com.thoughtworks.pacman.core.actors.ClonePacman2;
import com.thoughtworks.pacman.core.actors.ClonePacman3;
import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.SpecialCollectableItem;
import com.thoughtworks.pacman.core.tiles.visitors.ClonePacmanTileVisitor;
import com.thoughtworks.pacman.core.tiles.visitors.PacmanTileVisitor;

public class Game {
    private final Maze maze;
    private final Pacman pacman;
    private final Ghosts ghosts;
    private final PacmanTileVisitor pacmanTileVisitor;

    private boolean specialCollectableItemExists = false;
    private ClonePacman clonePacman;
    private ClonePacmanTileVisitor clonePacmanTileVisitor;
    private int SpecialCollectableItemTimeBoundary = 200;
    private int ClonePacmanTimeBoundary = 200;
    private int SpecialCollectableItemTimePassed = 0;
    private int ClonePacmanTimePassed = 0;
    
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

    public boolean clonePacmanExists() {
        return (this.clonePacman != null);
    }

    public void addClonePacman1() {
        if (this.clonePacmanExists()) {
            return;
        }
        else {
            if (this.maze.useType1Item()) {
                this.clonePacman = new ClonePacman1(maze, this.pacman.getCenter(), this.pacman.getDirection());
            }
        }
    }

    public void addClonePacman2() {
        if (this.clonePacmanExists()) {
            return;
        }
        else {
            if (this.maze.useType2Item()) {
                this.clonePacman = new ClonePacman2(maze, this.pacman.getCenter(), this.pacman.getDirection());
            }
        }
    }

    public void addClonePacman3() {
        if (this.clonePacmanExists()) {
            return;
        }
        else {
            if (this.maze.useType3Item()) {
                this.clonePacman = new ClonePacman3(maze, this.pacman.getCenter(), this.pacman.getDirection());
            }
        }
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

    public ClonePacman getClonePacman() {
        return this.clonePacman;
    }

    public Ghost[] getGhosts() {
        return new Ghost[] {ghosts.getBlinky(), ghosts.getPinky(), ghosts.getInky(), ghosts.getClyde()};
    }

    public void specialCollectableItemCheck() {
        if ( (((new Random()).nextInt(8)) == 0) && (!this.specialCollectableItemExists) ) {
            TileCoordinate coordinateOfSpecialCollectibleItem = new TileCoordinate( (new Random()).nextInt(this.maze.getWidth()), (new Random()).nextInt(this.maze.getHeight()) );
            if (this.maze.tileAt(coordinateOfSpecialCollectibleItem) instanceof Dot) {
                this.maze.putItem(coordinateOfSpecialCollectibleItem);
                this.specialCollectableItemExists = true;
                this.SpecialCollectableItemTimePassed = 0;
            }
        }
        else if (this.specialCollectableItemExists) {
            if (SpecialCollectableItemTimePassed == SpecialCollectableItemTimeBoundary) {
                this.maze.removeItem();
                this.specialCollectableItemExists = false;
                this.SpecialCollectableItemTimePassed = 0;
            }
            else {
                this.SpecialCollectableItemTimePassed += 1;
            }
        }
    }

    public void clonePacmanCheck() {
        if (this.clonePacmanExists()) {
            if (this.ClonePacmanTimePassed == this.ClonePacmanTimeBoundary) {
                this.ClonePacmanTimePassed = 0;
                this.clonePacman = null;
            }
            else {
                this.ClonePacmanTimePassed += 1;
            }
        }
    }

    public void advance(long timeDeltaInMillis) {
        if (pacman.isDead()) {
            return;
        }

        specialCollectableItemCheck();
        clonePacmanCheck();

        ghosts.freeGhostsBasedOnScore(maze.getScore());

        pacman.advance(timeDeltaInMillis);
        ghosts.advance(timeDeltaInMillis);
        if (this.clonePacmanExists()) {
            this.clonePacman.advance(timeDeltaInMillis);
        }

        if (ghosts.killed(pacman)) {
            pacman.die();
        }

        Tile pacmanTile = maze.tileAt(pacman.getCenter().toTileCoordinate());
        if (this.clonePacmanExists()) {
            maze.tileAt(this.clonePacman.getCenter().toTileCoordinate()).visit(clonePacmanTileVisitor);
        }

        pacmanTile.visit(pacmanTileVisitor);
        if (pacmanTile instanceof SpecialCollectableItem) {
            this.maze.eatItem();
        }
    }

    public boolean won() {
        return !maze.hasDotsLeft();
    }

    public boolean lost() {
        return pacman.isDead();
    }
}
