package com.thoughtworks.pacman.core.maze;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import java.util.ArrayList;

import com.thoughtworks.pacman.core.AllSpecialItems;
import com.thoughtworks.pacman.core.TileCoordinate;
import org.junit.Test;

import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.FreezingItem;
import com.thoughtworks.pacman.core.tiles.Wall;

public class MazeTest {

    @Test
    public void shouldCalculateDimensionInPixels() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();

        assertThat(maze.getDimension(), equalTo(new Dimension(28 * 16, 36 * 16)));
    }

    @Test
    public void toString_shouldReturnPrintableMaze() throws Exception {
        String mazeDescription = "+++\n" + //
                "+.+\n" + //
                "+ +\n" + //
                "+-+\n" + //
                "+++\n";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        assertThat(maze.toString(), equalTo(mazeDescription));
    }

    @Test
    public void tileAt_shouldReturnTileAtGivenCoordinate() throws Exception {
        String mazeDescription = "+. ";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);

        assertThat(maze.tileAt(new TileCoordinate(0, 0)), instanceOf(Wall.class));
        assertThat(maze.tileAt(new TileCoordinate(1, 0)), instanceOf(Dot.class));
        assertThat(maze.tileAt(new TileCoordinate(2, 0)), instanceOf(EmptyTile.class));
    }

    @Test
    public void tileAt_shouldReturnEmptyTile_whenTileCoordinateIsInvalidToAllowTeleport() throws Exception {
        String mazeDescription = "+";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        assertThat(maze.tileAt(new TileCoordinate(2, 2)), instanceOf(EmptyTile.class));
    }

    @Test
    public void getScore_shouldCalculatePointsPerDotsEaten() throws Exception {
        String mazeDescription = "++++\n+..+\n++++";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);

        assertThat(maze.getScore(), equalTo(0));

        Dot dot = (Dot) maze.tileAt(new TileCoordinate(1, 1));
        dot.eat();
        assertThat(maze.getScore(), equalTo(10));

        dot = (Dot) maze.tileAt(new TileCoordinate(2, 1));
        dot.eat();
        assertThat(maze.getScore(), equalTo(20));
    }

    @Test
    public void hasDotsLeft_shouldBeTrue_whenDotsAreLeftUneaten() throws Exception {
        String mazeDescription = "+++\n+.+\n+++";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        assertThat(maze.hasDotsLeft(), is(true));
    }

    @Test
    public void hasDotsLeft_shouldBeFalse_whenAllDotsAreEaten() throws Exception {
        String mazeDescription = "+++\n+.+\n+++";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);

        Dot dot = (Dot) maze.tileAt(new TileCoordinate(1, 1));
        dot.eat();

        assertThat(maze.hasDotsLeft(), is(false));
    }

    @Test
    public void getFreezingItem_shouldBeNull_whenCanNotFindAnFreezingItemInTheArraylist() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        ArrayList<AllSpecialItems> allitems = maze.getAllItems();
        
        allitems.add(null);
        
        assertNull(maze.getFreezingItem(0));
    }

    @Test
    public void getFreezingItem_shouldReturnFreezingItem() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        ArrayList<AllSpecialItems> allitems = maze.getAllItems();
        FreezingItem freezingItem = new FreezingItem(new TileCoordinate(1,1));
        
        allitems.add(freezingItem);

        assertThat(maze.getFreezingItem(0), instanceOf(FreezingItem.class));
    }

    @Test
    public void isFreezingItemExist_shouldReturnTrue_whenFreezingItemIsExistCertainIndex() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        ArrayList<AllSpecialItems> allitems = maze.getAllItems();
        FreezingItem freezingItem = new FreezingItem(new TileCoordinate(1,1));
        
        allitems.add(null);
        allitems.add(freezingItem);

        assertTrue(maze.isFreezingItemExist(1));
    }

    @Test
    public void isFreezingItemExist_shouldReturnFalse_whenFreezingItemIsNotExistCertainIndex() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        ArrayList<AllSpecialItems> allitems = maze.getAllItems();
        FreezingItem freezingItem = new FreezingItem(new TileCoordinate(1,1));
        
        allitems.add(null);
        allitems.add(freezingItem);

        assertFalse(maze.isFreezingItemExist(0));
    }

    @Test
    public void isFreezingItemExist_shouldReturnFalse_whenSpecificFreezingItemIsNull() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        ArrayList<AllSpecialItems> allitems = maze.getAllItems();
        FreezingItem freezingItem = null;
        
        allitems.add(freezingItem);

        assertFalse(maze.isFreezingItemExist(freezingItem));
    }

    @Test
    public void isFreezingItemExist_shouldReturnTrue_whenSpecificFreezingItemIsExistInArraylist() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        ArrayList<AllSpecialItems> allitems = maze.getAllItems();
        FreezingItem freezingItem = new FreezingItem(new TileCoordinate(1,1));
        
        allitems.add(freezingItem);

        assertTrue(maze.isFreezingItemExist(freezingItem));
    }

    @Test
    public void isFreezingItemExist_shouldReturnFalse_whenSpecificFreezingItemIsNotExistInArraylist() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        ArrayList<AllSpecialItems> allitems = maze.getAllItems();
        FreezingItem freezingItem = new FreezingItem(new TileCoordinate(1,1));
        FreezingItem freezingItem2 = new FreezingItem(new TileCoordinate(2,2));
        
        allitems.add(freezingItem2);

        assertFalse(maze.isFreezingItemExist(freezingItem));
    }

    @Test
    public void insert_correctlyFreezingItem() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        ArrayList<AllSpecialItems> allitems = maze.getAllItems();
        FreezingItem freezingItem = new FreezingItem(new TileCoordinate(1,1));
        FreezingItem freezingItem2 = new FreezingItem(new TileCoordinate(2,2));
        
        maze.insert(freezingItem);
        maze.insert(freezingItem2);

        assertEquals(freezingItem,maze.getAllItems().get(0));
        assertEquals(freezingItem2,maze.getAllItems().get(1));
    }

    @Test
    public void removeFreezingItem_correctly() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        FreezingItem freezingItem = new FreezingItem(new TileCoordinate(1,1));
        FreezingItem freezingItem2 = new FreezingItem(new TileCoordinate(2,2));
       
        maze.insert(freezingItem);
        maze.insert(freezingItem2);
        maze.removeFreezingItem(0);

        assertEquals(freezingItem2,maze.getAllItems().get(0));
    }
}
