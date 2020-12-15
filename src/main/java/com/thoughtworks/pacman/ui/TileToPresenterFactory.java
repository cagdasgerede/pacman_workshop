package com.thoughtworks.pacman.ui;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileVisitor;
import com.thoughtworks.pacman.core.tiles.Door;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;
import com.thoughtworks.pacman.ui.presenters.DoorPresenter;
import com.thoughtworks.pacman.ui.presenters.DotPresenter;
import com.thoughtworks.pacman.ui.presenters.NullPresenter;
import com.thoughtworks.pacman.ui.presenters.WallPresenter;

public class TileToPresenterFactory implements TileVisitor<Presenter> {
    public static Presenter toPresenter(Tile tile) {
        final TileToPresenterFactory factory = new TileToPresenterFactory();
        return tile.visit(factory);
    }

    public static Presenter toPresenter(Tile tile, int xOffset) {
        final TileToPresenterFactory factory = new TileToPresenterFactory();
        return tile.visit(factory, xOffset);
    }

    public Presenter visit(Dot dot) {
        return new DotPresenter(dot);
    }

    public Presenter visit(Wall wall) {
        return new WallPresenter(wall);
    }

    public Presenter visit(EmptyTile emptyTile) {
        return new NullPresenter();
    }

    public Presenter visit(Door door) {
        return new DoorPresenter(door);
    }

    public Presenter visit(Dot dot, int xOffset) {
        return new DotPresenter(dot, xOffset);
    }

    public Presenter visit(Wall wall, int xOffset) {
        return new WallPresenter(wall, xOffset);
    }


    public Presenter visit(Door door, int xOffset) {
        return new DoorPresenter(door, xOffset);
    }
}
