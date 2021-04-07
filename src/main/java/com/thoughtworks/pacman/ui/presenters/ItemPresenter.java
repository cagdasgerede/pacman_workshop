package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.tiles.TeleporterItem;
import com.thoughtworks.pacman.core.TeleporterItemController;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;

public class ItemPresenter implements Presenter {
    private final TeleporterItemController teleporterItemController;
    private static final Font FONT = new Font("Monospaced", Font.BOLD, Tile.SIZE);

    public ItemPresenter(TeleporterItemController teleporterItemController) {
        this.teleporterItemController = teleporterItemController;
    }
    
    public void draw(Graphics2D graphics) {
        drawTeleporterItem(graphics);
        drawDroppedTeleporterItems(graphics);
        drawTeleporterItemCount(graphics);
    }

    private void drawTeleporterItemCount(Graphics2D graphics) {
        graphics.setColor(Color.blue);
        graphics.setFont(FONT);
        graphics.drawString(String.format("%s %2d","Teleporter Item Count:", teleporterItemController.getTeleporterItemCount()), Tile.SIZE * 10, Tile.SIZE * 2);
    }

    public void drawTeleporterItem(Graphics2D graphics) {
        if(teleporterItemController.isFreezingItemAlive()) {
            TeleporterItemPresenter tpItemPresenter = new TeleporterItemPresenter(teleporterItemController.getTeleporterItem());
            tpItemPresenter.draw(graphics);
        }
    }

    public void drawDroppedTeleporterItems(Graphics2D graphics) {
        if(!teleporterItemController.getDroppedTeleporters().isEmpty()) {
            for(TeleporterItem droppedTeleporter:teleporterItemController.getDroppedTeleporters()) {
                TeleporterItemPresenter tpItemPresenter = new TeleporterItemPresenter(droppedTeleporter);
                tpItemPresenter.draw(graphics);
            }
        }
    }
}