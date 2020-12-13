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
    private final long createdMillis = System.currentTimeMillis();

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

        Tile pacmanTile = maze.tileAt(pacman.getCenter().toTileCoordinate());
        pacmanTile.visit(pacmanTileVisitor);
    }

    public boolean won() {
        if(!maze.hasDotsLeft() && this.getMaze().getAchievement().isNewlyAchievedFinished()){
        }
        if(!maze.hasDotsLeft() && this.getMaze().getAchievement().isNewlyAchievedPlayed()){
        }
        if(!maze.hasDotsLeft() && this.getMaze().getAchievement().isNewlyAchievedCollected()){
        }
        if(!maze.hasDotsLeft() && this.getMaze().getAchievement().isNewlyAchievedTook()){
        }
        return !maze.hasDotsLeft();
    }

    public boolean lost() {
        if(pacman.isDead()){
            
            if(this.getMaze().getAchievement().isNewlyAchievedPlayed()){
            }
            if(this.getMaze().getAchievement().isNewlyAchievedCollected()){
            }
            if(this.getMaze().getAchievement().isNewlyAchievedTook()){
            }
            maze.setTimePlayed(getAgeInSeconds());
            maze.writeAchievements();
        }
        return pacman.isDead();
    }
    public  int getAgeInSeconds() {
        long nowMillis = System.currentTimeMillis();
        return (int)((nowMillis - this.createdMillis) / 1000);
    }
}
