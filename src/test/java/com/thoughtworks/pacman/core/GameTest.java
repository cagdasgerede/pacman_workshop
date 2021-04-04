package com.thoughtworks.pacman.core;

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
public class GameTest {
    @Mock
    private Ghosts ghosts;
    private Pacman pacman;
    private Maze maze;

    @Before
    public void setUp() throws Exception {
        this.maze = MazeBuilder.buildDefaultMaze();
        this.pacman = spy(new Pacman(maze, "initialize"));
    }

    @Test
    public void won_shouldBeTrue_whenNoDotsLeftInMaze() throws Exception {
        Maze maze = MazeBuilder.buildMaze("+ +");
        Game game = new Game(maze, pacman, ghosts);

        assertThat(game.won(), is(true));
    }

    @Test
    public void won_shouldBeFalse_whenDotsLeftInMaze() throws Exception {
        Maze maze = MazeBuilder.buildMaze("+.+");
        Game game = new Game(maze, pacman, ghosts);

        assertThat(game.won(), is(false));
    }

    @Test
    public void lost_shouldBeTrue_whenPacmanIsDead() throws Exception {
        Game game = new Game("initialize");

        game.getPacman().die();

        assertThat(game.lost(), is(true));
    }

    @Test
    public void lost_shouldBeFalse_whenPacmanIsAlive() throws Exception {
        Game game = new Game("initialize");

        assertThat(game.lost(), is(false));
    }

    @Test
    public void advance_shouldDoNothing_whenPacmanIsDead() throws Exception {
        Game game = new Game(maze, pacman, ghosts);
        when(pacman.isDead()).thenReturn(true);

        game.advance(10);

        verify(pacman).isDead();
        verifyNoMoreInteractions(pacman, ghosts);
    }

    @Test
    public void advance_shouldFreeGhostsAndAdvanceActors_whenPacmanIsNotDead() throws Exception {
        Game game = new Game(maze, pacman, ghosts);
        when(pacman.isDead()).thenReturn(false);

        game.advance(10);

        verify(ghosts).freeGhostsBasedOnScore(0);
        verify(pacman).advance(10);
        verify(ghosts).advance(10);
    }

    @Test
    public void advance_shouldTellPacmanToDie_whenGhostsKillPacman() throws Exception {
        Game game = new Game(maze, pacman, ghosts);
        when(pacman.isDead()).thenReturn(false);
        when(ghosts.killed(pacman)).thenReturn(true);

        game.advance(10);

        verify(pacman).die();
    }

    @Test
    public void areSpeedsOfPacmanAndGhostsUpdatedAccordingToDotsLeft() throws Exception {
        String mazeDescription = "++++\n+..................+\n++++";

        Game game = new Game(mazeDescription, "easy");
        game.advance(90);
        assertThat(game.getPacman().getSpeed(), is(105));
        assertThat(game.getGhosts()[0].getSpeed(), is(105));

        game = new Game(mazeDescription, "medium");
        game.advance(90);
        assertThat(game.getPacman().getSpeed(), is(90));
        assertThat(game.getGhosts()[0].getSpeed(), is(110));

        game = new Game(mazeDescription, "hard");
        game.advance(90);
        assertThat(game.getPacman().getSpeed(), is(75));
        assertThat(game.getGhosts()[0].getSpeed(), is(115));

        mazeDescription = "++++\n+......+\n++++";

        game = new Game(mazeDescription, "easy");
        game.advance(90);
        assertThat(game.getPacman().getSpeed(), is(100));
        assertThat(game.getGhosts()[0].getSpeed(), is(110));

        game = new Game(mazeDescription, "medium");
        game.advance(90);
        assertThat(game.getPacman().getSpeed(), is(85));
        assertThat(game.getGhosts()[0].getSpeed(), is(115));

        game = new Game(mazeDescription, "hard");
        game.advance(90);
        assertThat(game.getPacman().getSpeed(), is(70));
        assertThat(game.getGhosts()[0].getSpeed(), is(120));
    }
}
