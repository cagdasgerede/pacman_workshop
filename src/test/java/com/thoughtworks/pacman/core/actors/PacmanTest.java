package com.thoughtworks.pacman.core.actors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

public class PacmanTest {
    private Pacman pacman;
    Maze maze;

    @Before
    public void setUp() throws Exception {
        maze = MazeBuilder.buildDefaultMaze();
        pacman = new Pacman(maze, "initialize");
    }

    @Test
    public void shouldBeginInStartingPositionFacingLeft() throws Exception {
        assertThat(pacman.getCenter(), equalTo(new SpacialCoordinate(14 * Tile.SIZE, 26 * Tile.SIZE + Tile.SIZE / 2)));
        assertThat(pacman.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void isDead_shouldBeFalseByDefault() throws Exception {
        assertThat(pacman.isDead(), is(false));
    }

    @Test
    public void isDead_shouldBeTrueAfterDead() throws Exception {
        pacman.die();
        assertThat(pacman.isDead(), is(true));
    }

    @Test
    public void isHalted_shouldBeFalseByDefault() throws Exception {
        assertThat(pacman.isHalted(), is(false));
    }

    @Test
    public void isHalted_shouldBeTrueAfterDead() throws Exception {
        pacman.die();
        assertThat(pacman.isHalted(), is(true));
    }

    @Test
    public void isSpeedChangesAccordingToDifficultyLevel() throws Exception {
        pacman = new Pacman(maze, "easy");
        assertThat(pacman.getSpeed(), is(110));

        pacman = new Pacman(maze, "medium");
        assertThat(pacman.getSpeed(), is(95));

        pacman = new Pacman(maze, "hard");
        assertThat(pacman.getSpeed(), is(80));
    }
}
