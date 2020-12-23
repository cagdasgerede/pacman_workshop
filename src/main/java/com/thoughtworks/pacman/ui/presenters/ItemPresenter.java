package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.ItemController;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.tiles.FreezingItem;
import com.thoughtworks.pacman.core.tiles.FreezingItemBomb;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


import static com.thoughtworks.pacman.ui.TileToPresenterFactory.toPresenter;

public class ItemPresenter implements Presenter {
    private static final Font FONT = new Font("Monospaced", Font.BOLD, Tile.SIZE);
    
    private final ItemController itemController;

    public ItemPresenter(ItemController itemController){
        this.itemController = itemController;
    }

    @Override
    public void draw(Graphics2D graphics) {
        drawFreezingItem(graphics);
        drawFreezingItemBomb(graphics);
        drawFreezingItemCount(graphics);
        drawFreezingItemCountString(graphics);
    }
    
    public void drawFreezingItem(Graphics2D graphics){
        if(itemController.isFreezingItemAlive())
            toPresenter((FreezingItem)itemController.getAliveItem()).draw(graphics);
    }
    
    public void drawFreezingItemBomb(Graphics2D graphics){
        if(itemController.getAliveFreezingItemBombs().size()>0){
            for(int i = 0; i < itemController.getAliveFreezingItemBombs().size(); i++){
                toPresenter((FreezingItemBomb)itemController.getGame().getMaze().tileAt(itemController.getAliveFreezingItemBombs().get(i))).draw(graphics);
            }
        }
    }

    private void drawFreezingItemCount(Graphics2D graphics){
        graphics.setColor(Color.red);
        graphics.setFont(FONT);
        graphics.drawString(String.format("%2d", itemController.getFreezingItemCount()), Tile.SIZE * 23, Tile.SIZE * 2);
    }

    private void drawFreezingItemCountString(Graphics2D graphics){
        graphics.setColor(Color.gray);
        graphics.setFont(FONT);
        graphics.drawString("Freezing Bomb Count:", Tile.SIZE * 11, Tile.SIZE * 2);
    }
}
