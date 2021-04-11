package com.thoughtworks.pacman.core;

import java.util.ArrayList;
import java.util.Random;

import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.TeleporterItem;

public class TeleporterItemController {
    private Game game;
    private Random random = new Random();

    private final int frequency = 10;
    private final int teleporterItemLifetime = 100; 
    private final int[] teleporterDistances = new int[]{5, 10, 15};  
    
    private TeleporterItem teleporterItem;
    private int teleporterItemTimeCount = 0;
    private Tile oldTile;
    private TileCoordinate teleporterItemCoordinate;
    private ArrayList<TeleporterItem> pickedTeleporters = new ArrayList<>();
    private ArrayList<TeleporterItem> droppedTeleporters = new ArrayList<>();

    public TeleporterItemController(Game game) {
        this.game = game;
    }

    public boolean isFreezingItemAlive() {
        return this.teleporterItem != null;
    }

    public ArrayList<TeleporterItem> getDroppedTeleporters() {
        return this.droppedTeleporters;
    }

    public int getTeleporterItemCount() {
        return this.pickedTeleporters.size();
    }

    public TileCoordinate getTeleporterItemCoordinate() {
        return this.teleporterItemCoordinate;
    }

    public Game getGame() {
        return game;
    }

    public TeleporterItem getTeleporterItem() {
        return this.teleporterItem;
    }

    public void spawnTeleporterItem() {
        boolean spawnChoice = random.nextInt(frequency) <= 1;
        if(teleporterItem == null && spawnChoice) {
            TileCoordinate newCoordinate = emptyCoordinateFinder();
            int distanceChoice = random.nextInt(teleporterDistances.length);
            int distance = teleporterDistances[distanceChoice];
            this.teleporterItem = new TeleporterItem(newCoordinate, distance, false);
            this.teleporterItemCoordinate = newCoordinate;
            oldTile = game.getMaze().getTiles().get(newCoordinate);
            game.getMaze().getTiles().put(newCoordinate, this.teleporterItem);
            teleporterItemTimeCount = 0;
        }
        else if(teleporterItem != null) {
            if(teleporterItemTimeCount >= teleporterItemLifetime) {
                removeTeleporterItem();
                teleporterItemTimeCount = 0;
            }
            else{
                teleporterItemTimeCount++;
            }
        }
    }

    public void eatTeleporterItem() {
        if(this.teleporterItem == null) {
            return;
        }
        this.teleporterItem.eat();
        this.pickedTeleporters.add(this.teleporterItem);
        this.removeTeleporterItem();
    }    

    public void removeTeleporterItem() {
        game.getMaze().getTiles().replace(this.teleporterItemCoordinate, this.oldTile);
        this.teleporterItem = null;
        this.teleporterItemCoordinate = null;
    }    

    private TileCoordinate emptyCoordinateFinder() {
        int width = random.nextInt(game.getMaze().getWidth());
        int height = random.nextInt(game.getMaze().getHeight());
        TileCoordinate newCoordinate = new TileCoordinate(width, height);
        while(!(game.getMaze().tileAt(newCoordinate) instanceof Dot)) {
            width = random.nextInt(game.getMaze().getWidth());
            height = random.nextInt(game.getMaze().getHeight());
            newCoordinate = new TileCoordinate(width, height);
        }
        return newCoordinate;
    }

    public void dropTeleporter(TileCoordinate pacCurrentCoordinate) {
        if(!pickedTeleporters.isEmpty()) {
            TeleporterItem droppedTeleporter = this.pickedTeleporters.get(0);
            this.pickedTeleporters.remove(0);
            droppedTeleporter.setIsDropped(true);
            droppedTeleporter.setCenter(pacCurrentCoordinate);
            this.droppedTeleporters.add(droppedTeleporter);
            game.getMaze().getTiles().put(pacCurrentCoordinate, droppedTeleporter);
        }
    }

    public void checkGhostCollision(Ghost[] ghostsArr) {
        for(Ghost ghost:ghostsArr) {
            TileCoordinate ghostCoordinate = ghost.getCenter().toTileCoordinate();
            if(this.game.getMaze().getTiles().get(ghostCoordinate).isDropped()) {
                TeleporterItem droppedTeleporter = (TeleporterItem)this.game.getMaze().getTiles().get(ghostCoordinate);
                int droppedTeleporterX = droppedTeleporter.getCenter().toTileCoordinate().getX();
                int droppedTeleporterY = droppedTeleporter.getCenter().toTileCoordinate().getY();
                int x,y;
                TileCoordinate newTileCoordinate = new TileCoordinate(-1, -1);
                while(!(game.getMaze().tileAt(newTileCoordinate) instanceof Dot)) {
                    x = droppedTeleporterX + random.nextInt(droppedTeleporter.getTeleportDistance());
                    y = droppedTeleporterY + random.nextInt(droppedTeleporter.getTeleportDistance());
                    newTileCoordinate = new TileCoordinate(x,y);
                }
                ghost.setCenter(newTileCoordinate.toSpacialCoordinate());
                droppedTeleporters.remove(this.game.getMaze().getTiles().get(ghostCoordinate));
                game.getMaze().getTiles().put(ghostCoordinate, new EmptyTile(ghostCoordinate));                
            }
        }
    }
}