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
    private String difficulty = "";

    public Game(String difficulty) throws Exception{
        this.difficulty = difficulty;
        this.maze = MazeBuilder.buildWalledMaze();
        this.pacman = new Pacman(maze, difficulty);
        this.ghosts = new Ghosts(this);
        this.pacmanTileVisitor = new PacmanTileVisitor();
    }

    public Game(String mazeDescription, String difficulty) throws Exception {
        this.difficulty = difficulty;
        maze = MazeBuilder.buildMaze(mazeDescription);
        this.pacman = new Pacman(maze, difficulty);
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

    public void advance(long timeDeltaInMillis) {
        if (pacman.isDead()) {
            return;
        }

        ghosts.freeGhostsBasedOnScore(maze.getScore());

        int dotsLeft = maze.dotsLeft();
        if (difficulty.equals("easy")) {
            if (dotsLeft <= 20) {
                pacman.setSpeed(105);
                ghosts.setGhostsSpeed(105);
            }
            if (dotsLeft <= 10) {
                pacman.setSpeed(100);
                ghosts.setGhostsSpeed(110);
            }
        } else if (difficulty.equals("medium")) {
            if (dotsLeft <= 20) {
                pacman.setSpeed(90);
                ghosts.setGhostsSpeed(110);
            }
            if (dotsLeft <= 10) {
                pacman.setSpeed(85);
                ghosts.setGhostsSpeed(115);
            }
        } else if (difficulty.equals("hard")) {
            if (dotsLeft <= 20) {
                pacman.setSpeed(75);
                ghosts.setGhostsSpeed(115);
            }
            if (dotsLeft <= 10) {
                pacman.setSpeed(70);
                ghosts.setGhostsSpeed(120);
            }
        }

        pacman.advance(timeDeltaInMillis);
        ghosts.advance(timeDeltaInMillis);

        if (ghosts.killed(pacman)) {
            pacman.die();
        }

        Tile pacmanTile = maze.tileAt(pacman.getCenter().toTileCoordinate());
        pacmanTile.visit(pacmanTileVisitor);
    }

    public boolean won() {
        return maze.dotsLeft() <= 0;
    }

    public boolean lost() {
        return pacman.isDead();
    }
}
