package com.thoughtworks.pacman.core;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

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
    public final HashMap<String, Integer> scoreList;
    public boolean newHighScore = false;
    public int finalScore;
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
        this.scoreList = new HashMap<>();
    }

    public Game(Maze maze, Pacman pacman, Ghosts ghosts) {
        this.maze = maze;
        this.pacman = pacman;
        this.ghosts = ghosts;
        this.pacmanTileVisitor = new PacmanTileVisitor();
        this.scoreList = new HashMap<>(10);
        initializeScoreList();
    }

    private void initializeScoreList(){

        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("scores.txt"));
			String line = reader.readLine();
			while (line != null) {


                String[] parts = line.split(",");
                parts[0] = parts[0].trim();
                parts[1] = parts[1].trim();
                scoreList.put(parts[0],Integer.parseInt(parts[1]));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}



        /*try {
            FileWriter writer = new FileWriter("MyFile.txt", true);
            writer.write("Hello World");
            writer.write("\r\n");   // write new line
            writer.write("Good Bye!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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
        return !maze.hasDotsLeft();
    }

    public boolean lost() {
        finalScore = maze.getScore();
        
        for (HashMap.Entry<String, Integer> entry : scoreList.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(finalScore >value){
                newHighScore = true;
                if(scoreList.size() >=10){
                    scoreList.remove(key);
                }
            }
        }
        
        return pacman.isDead();
    }
}
