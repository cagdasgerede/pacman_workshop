package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.movement.MovementStrategy;
import com.thoughtworks.pacman.core.movement.RandomMovementStrategy;

import java.io.Serializable;

public enum GhostType implements Serializable{
    BLINKY("ghost10.jpg", "ghost11.jpg", new SpacialCoordinate(14 * Tile.SIZE, 14 * Tile.SIZE + Tile.SIZE / 2)) {
        @Override
        public MovementStrategy getMovementStrategy(Game game) {
            return new RandomMovementStrategy(getStartCoordinate(), game.getMaze());
        }
    },
    PINKY("ghost40.jpg", "ghost41.jpg", new SpacialCoordinate(14 * Tile.SIZE + Tile.SIZE / 2, 17 * Tile.SIZE + Tile.SIZE / 2)) {
        @Override
        public MovementStrategy getMovementStrategy(Game game) {
            return new RandomMovementStrategy(getStartCoordinate(), game.getMaze());
        }
    },
    INKY("ghost30.jpg", "ghost31.jpg", new SpacialCoordinate(12 * Tile.SIZE + Tile.SIZE / 2, 17 * Tile.SIZE + Tile.SIZE / 2)) {
        @Override
        public MovementStrategy getMovementStrategy(Game game) {
            return new RandomMovementStrategy(getStartCoordinate(), game.getMaze());
        }
    },
    CLYDE("ghost20.jpg", "ghost21.jpg", new SpacialCoordinate(16 * Tile.SIZE + Tile.SIZE / 2, 17 * Tile.SIZE + Tile.SIZE / 2)) {
        @Override
        public MovementStrategy getMovementStrategy(Game game) {
            return new RandomMovementStrategy(getStartCoordinate(), game.getMaze());
        }
    };

    private final SpacialCoordinate startCoordinate;
    private final String image1;
    private final String image2;

    private GhostType(String image1, String image2, SpacialCoordinate startCoordinate) {
        this.image1 = image1;
        this.image2 = image2;
        this.startCoordinate = startCoordinate;
    }

    public SpacialCoordinate getStartCoordinate() {
        return startCoordinate;
    }

    public String getImageOne() {
        return image1;
    }

    public String getImageTwo() {
        return image2;
    }

    public static SpacialCoordinate doorExit() {
        return GhostType.BLINKY.getStartCoordinate();
    }

    public abstract MovementStrategy getMovementStrategy(Game game);
}
