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
    private int level;//Added level counter.
    private int score;//
    private int tutorialsWinScore = 1000;//points needed to win Half Maze Levels

    public Game() throws Exception {
        this(MazeBuilder.buildWalledMaze());
        this.level = 1;
    }

    public Game(int level) throws Exception {
        this(MazeBuilder.buildWalledMaze());
        this.level = level;
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
        this.level = 1;
        this.maze = maze;
        this.pacman = pacman;
        this.ghosts = ghosts;
        this.pacmanTileVisitor = new PacmanTileVisitor();
    }

    public Game(Maze maze, Pacman pacman, Ghosts ghosts, int level) {
        this.level = level;
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

    public Ghost[] getGhostsLeveled() {//Game now divided by levels which acts like tutorial and gets harder.
        Ghost[] ghostS = new Ghost[0];
        switch (this.level) {
            case 1: ghostS = new Ghost[]{ghosts.getBlinky()};//Half Maze
            case 2: ghostS = new Ghost[]{ghosts.getBlinky()};//Full Maze
            case 3: ghostS = new Ghost[]{ghosts.getBlinky(), ghosts.getPinky()};//Half Maze
            case 4: ghostS = new Ghost[]{ghosts.getBlinky(), ghosts.getPinky()};//Full Maze
            case 5: ghostS = new Ghost[]{ghosts.getBlinky(), ghosts.getPinky(), ghosts.getClyde()};//Half Maze
            case 6: ghostS = new Ghost[]{ghosts.getBlinky(), ghosts.getPinky(), ghosts.getClyde()};//Full Maze
        }
        if (level >= 7){//After 6th stage ghosts will get faster and faster in endgame.
            ghostS = new Ghost[]{ghosts.getBlinky(), ghosts.getPinky(), ghosts.getInky(), ghosts.getClyde()};//Full Maze
        }
        return ghostS;
    }

    public void advance(long timeDeltaInMillis) {
        if (pacman.isDead()) {
            return;
        }

        ghosts.freeGhostsBasedOnScoreAndLevel(maze.getScore(), this.getLevel());//can be changed for optimizing

        pacman.advance(timeDeltaInMillis);
        ghosts.advance(timeDeltaInMillis);

        if (ghosts.killed(pacman)) {
            pacman.die();
        }

        Tile pacmanTile = maze.tileAt(pacman.getCenter().toTileCoordinate());
        pacmanTile.visit(pacmanTileVisitor);
    }

    public boolean won() {//win condition changed
        boolean win;
        score = maze.getScore();

        if (level == 1 || level == 3 || level == 5) {
            win = tutorialsWinScore <= score;
        }else{
            win = !maze.hasDotsLeft();
        }

        return win;
    }

    public boolean lost() {
        if (pacman.isDead()){
            level = 1;
            return true;
        }
        return false;
    }

    public int getLevel(){
        return level;
    }

    public void LevelUp(){
        level++;
    }

    public int getTutorialsWinScore() {
        return tutorialsWinScore;
    }
}
