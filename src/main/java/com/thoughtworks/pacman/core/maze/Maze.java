package com.thoughtworks.pacman.core.maze;

import java.awt.Dimension;
import java.util.Map;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.visitors.DotsLeftVisitor;
import com.thoughtworks.pacman.core.tiles.visitors.ScoreTileVisitor;

public class Maze {
    private final Map<TileCoordinate, Tile> tiles;
    private final int width;
    private final int height;
    private final long createdMillis = System.currentTimeMillis();
    int totalItems;
    Achievements achievement;

    Maze(int width, int height, Map<TileCoordinate, Tile> tiles) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        totalItems = 245;
        achievement = new Achievements();
    }

    public boolean canMove(TileCoordinate tileCoordinate) {
        return tileAt(tileCoordinate).isMovable();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Dimension getDimension() {
        return new Dimension(width * Tile.SIZE, height * Tile.SIZE);
    }

    public int getScore() {
        ScoreTileVisitor scoreVisitor = new ScoreTileVisitor();
        int totalScore = 0;
        for (Tile tile : tiles.values()) {
            totalScore += tile.visit(scoreVisitor);
        }
        return totalScore;
    }

    public boolean hasDotsLeft() {
        achievement.setTimeplayed(getAgeInSeconds());
        DotsLeftVisitor dotsLeftVisitor = new DotsLeftVisitor();
        int dotsLeft = 0;
        for (Tile tile : tiles.values()) {
            dotsLeft += tile.visit(dotsLeftVisitor);
        }
        achievement.setItemsCollected(totalItems-dotsLeft);

        if(dotsLeft == 0){
            achievement.setTimeFinished(getAgeInSeconds());
            achievement.setWon(true);
            if(achievement.isNewlyAchievedFinished());
            
            if(achievement.isNewlyAchievedPlayed());
            
            if(achievement.isNewlyAchievedCollected());
            
            if(achievement.isNewlyAchievedTook());
            
            achievement.initializeAchievements();
        }
        return dotsLeft > 0;
    }

    public Tile tileAt(TileCoordinate tileCoordinate) {
        if (tiles.containsKey(tileCoordinate)) {
            return tiles.get(tileCoordinate);
        } else {
            return new EmptyTile(tileCoordinate);
        }
    }
    public Achievements getAchievement(){
        return this.achievement;
    }


    public  int getAgeInSeconds() {
        long nowMillis = System.currentTimeMillis();
        return (int)((nowMillis - this.createdMillis) / 1000);
    }
    public void writeAchievements(){
        
        achievement.initializeAchievements();
    }
    public void incrementDirections(){
        achievement.incrementTurnsTook();
    }
    public void setTimePlayed(int time){
        achievement.setTimeplayed(time);
    }
    public void resetAchievements(){
        achievement.resetAchievements();
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.append(tileAt(new TileCoordinate(x, y)));
            }
            result.append("\n");
        }

        return result.toString();
    }
}
