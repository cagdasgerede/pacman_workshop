package com.thoughtworks.pacman.core;

import java.awt.Dimension;
import java.util.Random;

import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.FreezingItem;
import com.thoughtworks.pacman.core.tiles.visitors.PacmanTileVisitor;

public class Game {
    private final Maze maze;
    private final Pacman pacman;
    private final Ghosts ghosts;
    private final PacmanTileVisitor pacmanTileVisitor;
    private FreezingItem freezingItem;

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
        freezingItem = new FreezingItem(new TileCoordinate(0,0));
    }

    public Game(Maze maze, Pacman pacman, Ghosts ghosts) {
        this.maze = maze;
        this.pacman = pacman;
        this.ghosts = ghosts;
        this.pacmanTileVisitor = new PacmanTileVisitor();
        freezingItem = new FreezingItem(new TileCoordinate(0,0));
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

    public void advance(long timeDeltaInMillis) {
        if (pacman.isDead()) {
            return;
        }

        ghosts.freeGhostsBasedOnScore(maze.getScore());

        pacman.advance(timeDeltaInMillis);

        ghosts.advance(timeDeltaInMillis);        

        if (ghosts.killed(pacman)) {
            pacman.die();
        }

        freezingItem.feature(ghosts,maze);

        Tile pacmanTile = maze.tileAt(pacman.getCenter().toTileCoordinate());

        for(int i = 0; i < maze.getAllItems().size(); i++) {
            if(maze.isFreezingItemExist(i)){
                if(pacmanTile.getCenter().getX() == maze.getFreezingItem(i).getCenter().getX() && pacmanTile.getCenter().getY() == maze.getFreezingItem(i).getCenter().getY()){
                    freezingItem.reset(maze);
                }
            }
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
