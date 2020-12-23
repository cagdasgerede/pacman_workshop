package com.thoughtworks.pacman.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
    @Mock
    private Ghosts ghosts;
    private Pacman pacman;
    private Maze maze;

    @Before
    public void setUp() throws Exception {
        this.maze = MazeBuilder.buildDefaultMaze();
        this.pacman = spy(new Pacman(maze));
    }

    @Test 
    public void shouldDropFreezingBomb_whenHasEnoughItem() throws Exception {
        Game game = new Game(maze, pacman, ghosts);
        game.getItemController().setFreezingItemCount(1);
        game.getItemController().dropBomb();

        assertThat(game.getItemController().getAliveFreezingItemBombs().size()>0, is(true));
    }

    @Test 
    public void shouldNotDropFreezingBomb_whenHasNotEnoughItem() throws Exception {
        Game game = new Game(maze, pacman, ghosts);
        game.getItemController().dropBomb();

        assertThat(game.getItemController().getAliveFreezingItemBombs().size()==0, is(true));
    }

    @Test
    public void getFreezingItemCount_shouldCalculateEatenFreezingItemCount() throws Exception {
        String mazeDescription = "++++\n+..+\n++++";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        Game game = new Game(maze, pacman, ghosts);

        assertThat(game.getItemController().getFreezingItemCount(), equalTo(0));

        game.getItemController().insertFreezingItem(new TileCoordinate(1, 1));
        game.getItemController().eatFreezingItem();

        assertThat(game.getItemController().getFreezingItemCount(), equalTo(1));

        game.getItemController().insertFreezingItem(new TileCoordinate(2, 1));
        game.getItemController().eatFreezingItem();

        assertThat(game.getItemController().getFreezingItemCount(), equalTo(2));
    }
   
}
