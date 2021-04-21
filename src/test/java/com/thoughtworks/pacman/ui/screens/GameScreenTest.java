package com.thoughtworks.pacman.ui.screens;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.Ghosts;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.ui.Settings;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;

@RunWith(MockitoJUnitRunner.class)
public class GameScreenTest {
    @Mock
    private Graphics2D graphics;
    @Mock
    private KeyEvent keyEvent;
    @Mock
    private GamePresenter gamePresenter;
    @Mock
    private Maze maze;
    @Mock
    private Ghosts ghosts;
    @Mock
    private Settings settings;

    @Test
    public void draw_shouldAdvanceGameWithTimeDelta() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        Pacman pacman = spy(new Pacman(maze));
        Game game = new Game(maze, pacman, ghosts);
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        Thread.sleep(1); // Some time for pacman to move
        gameScreen.draw(graphics);

        verify(pacman).advance(gt(0L));
    }

    @Test
    public void nextScreen_shouldReturnGameScreen_whenGameNotOver() throws Exception {
        Game game = new Game();
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        assertThat(gameScreen.getNextScreen(), instanceOf(GameScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnWinScreen_whenGameWon() throws Exception {
        Game game = new Game(MazeBuilder.buildMaze("+ +"), mock(Pacman.class), mock(Ghosts.class));
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        assertThat(gameScreen.getNextScreen(), instanceOf(WinScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnLostScreen_whenGameLostAndDyingAnimationFinished() throws Exception {
        Game game = new Game();
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        game.getPacman().die();
        when(gamePresenter.isDying()).thenReturn(false);

        assertThat(gameScreen.getNextScreen(), instanceOf(LostScreen.class));
    }

    @Test
    public void keyPressed_shouldMovePacmanLeft() throws Exception {
        settings = new Settings();
        Pacman pacman = spy(new Pacman(maze));
        Game game = new Game(maze, pacman, ghosts);
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        when(keyEvent.getKeyCode()).thenReturn(settings.getKeyEventNumb()[3]);
        gameScreen.keyPressed(keyEvent);

        verify(pacman).setNextDirection(eq(Direction.LEFT));
    }

    @Test
    public void keyPressed_shouldMovePacmanRight() throws Exception {
        settings = new Settings();
        Pacman pacman = spy(new Pacman(maze));
        Game game = new Game(maze, pacman, ghosts);
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        when(keyEvent.getKeyCode()).thenReturn(settings.getKeyEventNumb()[2]);
        gameScreen.keyPressed(keyEvent);

        verify(pacman).setNextDirection(eq(Direction.RIGHT));
    }

    @Test
    public void keyPressed_shouldMovePacmanUp() throws Exception {
        settings = new Settings();
        Pacman pacman = spy(new Pacman(maze));
        Game game = new Game(maze, pacman, ghosts);
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        when(keyEvent.getKeyCode()).thenReturn(settings.getKeyEventNumb()[0]);
        gameScreen.keyPressed(keyEvent);

        verify(pacman).setNextDirection(eq(Direction.UP));
    }

    @Test
    public void keyPressed_shouldMovePacmanDown() throws Exception {
        settings = new Settings();
        Pacman pacman = spy(new Pacman(maze));
        Game game = new Game(maze, pacman, ghosts);
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        when(keyEvent.getKeyCode()).thenReturn(settings.getKeyEventNumb()[1]);
        gameScreen.keyPressed(keyEvent);

        verify(pacman).setNextDirection(eq(Direction.DOWN));
    }
}
