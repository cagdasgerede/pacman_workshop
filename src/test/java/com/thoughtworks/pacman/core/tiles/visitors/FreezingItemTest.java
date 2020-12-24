package com.thoughtworks.pacman.core.tiles;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.pacman.core.Ghosts;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.core.tiles.FreezingItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FreezingItemTest {

    @Test
    public void shouldBeMovable() {
        assertTrue(new FreezingItem(null, null).isMovable());
    }

    @Test
    public void isEaten_shouldBeFalseByDefault() {
        final FreezingItem freezingItem = new FreezingItem(null, null);
        assertFalse(freezingItem.isEaten());
    }

    @Test
    public void isEaten_shouldBeTrueAfterEat() {
        final FreezingItem freezingItem = new FreezingItem(null, null);

        freezingItem.eat();

        assertTrue(freezingItem.isEaten());
    }

    @Test
    public void toString_shouldReturnDot() {
        assertThat(new FreezingItem(null, null).toString(), equalTo("freezing item"));
    }

    @Test
    public void test() {
        Maze maze = mock(Maze.class);
        FreezingItem freezingItem = mock(FreezingItem.class);

        doNothing().when(maze).eat(freezingItem);

        freezingItem.reset(maze);

        verify(freezingItem, times(1)).reset(maze);
    }
}
