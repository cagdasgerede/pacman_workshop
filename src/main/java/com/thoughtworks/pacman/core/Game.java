package com.thoughtworks.pacman.core;

import java.awt.Dimension;

import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.core.tiles.TeleporterItem;
import com.thoughtworks.pacman.core.tiles.visitors.PacmanTileVisitor;

public class Game {
    private final Maze maze;
    private final Pacman pacman;
    private final Ghosts ghosts;
    private final PacmanTileVisitor pacmanTileVisitor;
    private final TeleporterItemController teleporterItemController;

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
        this.teleporterItemController = new TeleporterItemController(this);
    }

    public Game(Maze maze, Pacman pacman, Ghosts ghosts) {
        this.maze = maze;
        this.pacman = pacman;
        this.ghosts = ghosts;
        this.pacmanTileVisitor = new PacmanTileVisitor();
        this.teleporterItemController = new TeleporterItemController(this);
    }

    public Maze getMaze() {
        return maze;
    }

    public TeleporterItemController getTeleporterItemController() {
        return teleporterItemController;
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
        Ghost[] ghostsArr = getGhosts();
        if(!teleporterItemController.getDroppedTeleporters().isEmpty()) {
            teleporterItemController.checkGhostCollision(ghostsArr);
        }
        teleporterItemController.spawnTeleporterItem();
        ghosts.freeGhostsBasedOnScore(maze.getScore());

        pacman.advance(timeDeltaInMillis);
        ghosts.advance(timeDeltaInMillis);

        if (ghosts.killed(pacman)) {
            pacman.die();
        }

        Tile pacmanTile = maze.tileAt(pacman.getCenter().toTileCoordinate());
        if(pacmanTile instanceof TeleporterItem && !pacmanTile.isDropped()) {
            teleporterItemController.eatTeleporterItem();   
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
