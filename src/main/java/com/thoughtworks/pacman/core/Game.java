package com.thoughtworks.pacman.core;

import java.awt.Dimension;
import java.util.Random;

import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.SpecialItem;
import com.thoughtworks.pacman.core.tiles.visitors.PacmanTileVisitor;

public class Game {
    private final Maze maze;
    private final Pacman pacman;
    private final Ghosts ghosts;
    private final PacmanTileVisitor pacmanTileVisitor;
    private long counter=0;
    private long counter2 = 0;
    private long counter3 = 0;
    private boolean isItemEaten = false;
    private int one = 0;

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
        
        if(isItemEaten){
           
            if(one == 1){
                ghosts.getBlinky().freeze();
                ghosts.getPinky().freeze();
                ghosts.getInky().freeze();
                ghosts.getClyde().freeze();
                one--;
            }
            counter3++;
            
        }else{
            
            if(one == 0){
                ghosts.getBlinky().notFreeze();
                ghosts.getPinky().notFreeze();
                ghosts.getInky().notFreeze();
                ghosts.getClyde().notFreeze();
                counter3 = 0;
                one++;
            }            
        }

        if(counter3 % 100 == 99 && isItemEaten){
            isItemEaten = false;
        }

        ghosts.advance(timeDeltaInMillis);        

        if (ghosts.killed(pacman)) {
            pacman.die();
        }

        if(counter % 200 == 199){
            counter2++;
        }

        counter++;

        int minX = 1;
        int maxX = maze.getWidth() - 2;
        int minY = 4;
        int maxY = maze.getHeight() - 4;
        int random_intX;
        int random_intY;

        if(!maze.isSIExist()){   
                if(counter2 % 2 == 1){               
                    while(true){
                        random_intX = (int)(Math.random() * (maxX - minX + 1) + minX);
                        random_intY = (int)(Math.random() * (maxY - minY + 1) + minY);
                        TileCoordinate siCoordinate = new TileCoordinate(random_intX,random_intY);
                        if(maze.canMove(siCoordinate) && !(maze.tileAt(siCoordinate) instanceof EmptyTile)){
                            this.maze.tileAt(siCoordinate);
                            maze.insert(siCoordinate);
                            break;
                        }                   
                    }          
                }            
        }else{
            if(counter2 % 2 == 0){
                maze.eat();
            }
        }

        Tile pacmanTile = maze.tileAt(pacman.getCenter().toTileCoordinate());
        if(maze.isSIExist()){
            if(pacmanTile.getCenter().getX() == maze.getSpecialItem().getCenter().getX() && pacmanTile.getCenter().getY() == maze.getSpecialItem().getCenter().getY()){
                 maze.eat();
                 counter = 0;
                 counter2 = 0;
                 isItemEaten = true;
                 one = 1;
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
