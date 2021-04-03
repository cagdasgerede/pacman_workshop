package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Game;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FreezeItemTest {

    private Game game;
    private FreezeItem freezeItem;
    private Pacman pacman;

    @Before
    public void setUp() throws Exception {
        this.game = new Game();
        this.freezeItem = new FreezeItem(game, 1, 4);
        Maze maze = MazeBuilder.buildDefaultMaze();
        pacman = new Pacman(maze);
    }

    @Test
    public void isVisible_shouldBeFalseAfterItemPickedUp() throws Exception {
        freezeItem.pickedUp();
        assertThat(freezeItem.isVisible(), is(false));
    }

    @Test
    public void has_PauseTimeBeenSet() throws Exception {
        assertThat(game.setPauseTimeAfterCollide(), is(true));
    }

    @Test
    public void isVisible_shouldBeFalseByDefault() throws Exception {
        assertThat(freezeItem.isVisible(), is(false));
    }

    @Test
    public void ColorAndFreezeTimeMatch() throws Exception {
        game.setPauseTimeAfterCollide();
        if(freezeItem.getColor() == 0)
            assertThat(game.getPauseTime(), is(50));
        else if (freezeItem.getColor() == 1)
            assertThat(game.getPauseTime(), is(100));
        else if (freezeItem.getColor() == 2)
            assertThat(game.getPauseTime(), is(150));
    }

}
