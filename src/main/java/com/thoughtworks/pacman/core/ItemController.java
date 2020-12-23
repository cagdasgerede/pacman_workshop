package com.thoughtworks.pacman.core;

import java.util.ArrayList;
import java.util.Random;

import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.FreezingItem;
import com.thoughtworks.pacman.core.tiles.FreezingItemBomb;

public class ItemController {
    private Game game;

    //Constants
    private static final int itemMaxAliveTime = 200;
    private static final int defaultGhostSpeed = 100;
    private static final int ghostFreezeTime = 100;
    private static final int spawnFrequency = 15; // Lower is more frequent.

    //FreezingItem Variables
    private int freezingItemCount = 0;
    private TileCoordinate freezingItemCoordinate;
    private Tile oldTile;
    private FreezingItem aliveFreezingItem;
    
    //FreezingItemBomb Variables
    private int activeBombCount = 0;
    private ArrayList<TileCoordinate> aliveFreezingItemBombs = new ArrayList<>();

    //Time Variables
    private int freezingItemTimePassed = 0;
    private int blinkyFreezingTimePassed = 0;
    private int pinkyFreezingTimePassed = 0;
    private int inkyFreezingTimePassed = 0;
    private int clydeFreezingTimePassed = 0;

    //Constructors
    public ItemController(Game game) {
        this.game = game;
    }

    //Getter & Setter Methods
    public FreezingItem getAliveItem() {
        return aliveFreezingItem;
    }

    public ArrayList<TileCoordinate> getAliveBombs() {
        return aliveFreezingItemBombs;
    }

    public int getFreezingItemCount(){
        return this.freezingItemCount;
    }

    public int getActiveBombCount() {
        return activeBombCount;
    }

    public void setFreezingItemCount(int freezingItemCount) {
        this.freezingItemCount = freezingItemCount;
    }

    public TileCoordinate getFreezingItemCoordinate(){
        return this.freezingItemCoordinate;
    }

    public ArrayList<TileCoordinate> getAliveFreezingItemBombs() {
        return aliveFreezingItemBombs;
    }

    public Game getGame() {
        return game;
    }
    //FreezingItem Methods
    public void spawnFreezingItem(Random r){
        int spawnDecision = r.nextInt(spawnFrequency);
        if(isFreezingItemAlive() == false && spawnDecision <= 1) {
            int width = game.getMaze().getWidth();
            int height = game.getMaze().getHeight();
            Random r1 = new Random();
            Random r2 = new Random();
            TileCoordinate tempCoordinate = new TileCoordinate(r1.nextInt(width), r2.nextInt(height));
            if(game.getMaze().tileAt(tempCoordinate) instanceof Dot){
                insertFreezingItem(tempCoordinate);
                freezingItemTimePassed = 0;
            }
        }

        else if(isFreezingItemAlive()){
            if(freezingItemTimePassed >= itemMaxAliveTime){
                removeFreezingItem();
                freezingItemTimePassed = 0;
            }
            else{
                freezingItemTimePassed++;
            }
        }

    }

    public boolean isFreezingItemAlive(){
        return this.aliveFreezingItem != null;
    }

    public void insertFreezingItem(TileCoordinate ItemCoordinate){
        this.aliveFreezingItem = new FreezingItem(ItemCoordinate);
        this.freezingItemCoordinate = ItemCoordinate;
        oldTile = game.getMaze().getTiles().get(freezingItemCoordinate);
        game.getMaze().getTiles().put(ItemCoordinate, this.aliveFreezingItem); 
    }

    public void removeFreezingItem(){
        game.getMaze().getTiles().replace(this.freezingItemCoordinate, this.oldTile);
        this.freezingItemCoordinate = null;
        this.aliveFreezingItem = null;
    }

    public void eatFreezingItem(){
        if(this.aliveFreezingItem == null)
            return;

        this.aliveFreezingItem.eat();
        this.freezingItemCount++;
        this.removeFreezingItem();
    }

    //FreezingItemBomb Methods
    public void dropBomb(){
        insertFreezingItemBomb(game.getPacman().getCenter().toTileCoordinate());
        activeBombCount++;
    }
    
    public void insertFreezingItemBomb(TileCoordinate ItemCoordinate){
        if(freezingItemCount <= 0) {
            return;
        }
        this.aliveFreezingItemBombs.add(ItemCoordinate);
        game.getMaze().getTiles().put(ItemCoordinate, new FreezingItemBomb(ItemCoordinate)); 
        freezingItemCount--;
    }

    public void removeFreezingItemBomb(TileCoordinate ItemCoordinate){
        this.aliveFreezingItemBombs.remove(ItemCoordinate);
        game.getMaze().getTiles().put(ItemCoordinate, new EmptyTile(ItemCoordinate)); 
        activeBombCount--;
    }

    public void checkBombCollision(Ghost[] ghostsArray) {
        for(int i = 0; i < ghostsArray.length; i++){
            if(game.getMaze().tileAt(ghostsArray[i].getCenter().toTileCoordinate()) instanceof FreezingItemBomb){
                removeFreezingItemBomb(ghostsArray[i].getCenter().toTileCoordinate());
                switch(i) {
                    case 0:
                        game.getGhostsObject().getBlinky().setSpeed(0);
                        blinkyFreezingTimePassed = 0;
                        break;
                    case 1:
                        game.getGhostsObject().getPinky().setSpeed(0);
                        pinkyFreezingTimePassed = 0;
                        break;
                    case 2:
                        game.getGhostsObject().getInky().setSpeed(0);
                        inkyFreezingTimePassed = 0;
                        break;
                    case 3:
                        game.getGhostsObject().getClyde().setSpeed(0);
                        clydeFreezingTimePassed = 0;
                        break;
               }
            }
        }
    }

    public void ghostFreezeController(Ghost[] ghostsArray){
        for(int i = 0; i < ghostsArray.length; i++){
            switch(i) {
                case 0:
                    if(blinkyFreezingTimePassed>ghostFreezeTime)
                        game.getGhostsObject().getBlinky().setSpeed(defaultGhostSpeed);
                    else{
                        blinkyFreezingTimePassed++;
                    }
                    break;
                case 1:
                    if(pinkyFreezingTimePassed>ghostFreezeTime)
                        game.getGhostsObject().getPinky().setSpeed(defaultGhostSpeed);
                    else{
                        pinkyFreezingTimePassed++;
                    }
                    break;
                case 2:
                    if(inkyFreezingTimePassed>ghostFreezeTime)
                        game.getGhostsObject().getInky().setSpeed(defaultGhostSpeed);
                    else{
                        inkyFreezingTimePassed++;
                    }
                    break;
                case 3:
                    if(clydeFreezingTimePassed>ghostFreezeTime)
                        game.getGhostsObject().getClyde().setSpeed(defaultGhostSpeed);
                    else{
                        clydeFreezingTimePassed++;
                    }
                    break;
            }
        }
    }
}
