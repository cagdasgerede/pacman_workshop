package com.thoughtworks.pacman.core;

import java.awt.Dimension;

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

    private boolean isItOnPause;

    public int score;

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

    public void advance(long timeDeltaInMillis, boolean isItStopped) {
        if (pacman.isDead()) {
            return;
        }
        isItOnPause = isItStopped;
        score = maze.getScore();
        ghosts.freeGhostsBasedOnScore(maze.getScore());

        pacman.advance(timeDeltaInMillis, isItStopped);
        ghosts.advance(timeDeltaInMillis, isItStopped);

        if (ghosts.killed(pacman)) {
            pacman.die();
        }

        Tile pacmanTile = maze.tileAt(pacman.getCenter().toTileCoordinate());
        pacmanTile.visit(pacmanTileVisitor);
    }

    public boolean won() {
        return !maze.hasDotsLeft();
    }

    public boolean lost() {
        return pacman.isDead();
    }

    public boolean onPause(){
        return isItOnPause;
    }
}
