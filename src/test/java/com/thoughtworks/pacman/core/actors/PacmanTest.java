package com.thoughtworks.pacman.core.actors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

public class PacmanTest {
    private Maze maze;

    @Before
    public void setUp() throws Exception {
        maze = MazeBuilder.buildDefaultMaze();
    }

    @Test
    public void shouldBeginInStartingPositionFacingLeft() throws Exception {
        final Pacman pacman = new Pacman(maze);
        assertThat(pacman.getCenter(), equalTo(new SpacialCoordinate(14 * Tile.SIZE, 26 * Tile.SIZE + Tile.SIZE / 2)));
        assertThat(pacman.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldMaintainCurrentDirectionIfNextIsNotSpecified() throws Exception {
        Pacman pacman = new Pacman(maze);
        assertThat(pacman.getNextTile(new TileCoordinate(14, 26)), equalTo(new TileCoordinate(13, 26)));
        assertThat(pacman.isMoving(), is(true));
        assertThat(pacman.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldMaintainCurrentDirectionIfTurnToNextIsImpossible() throws Exception {
        Pacman pacman = new Pacman(maze);
        pacman.setNextDirection(Direction.DOWN);
        assertThat(pacman.getNextTile(new TileCoordinate(14, 26)), equalTo(new TileCoordinate(13, 26)));
        assertThat(pacman.isMoving(), is(true));
        assertThat(pacman.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldTurnDirectionIfPossible() throws Exception {
        Pacman pacman = new Pacman(maze);
        pacman.setNextDirection(Direction.RIGHT);
        assertThat(pacman.isMoving(), is(true));
        assertThat(pacman.getNextTile(new TileCoordinate(14, 26)), equalTo(new TileCoordinate(15, 26)));
        assertThat(pacman.getDirection(), equalTo(Direction.RIGHT));
    }

    @Test
    public void shouldStopAndRememberPreviousDirectionIfNextInDesiredDirectionTileIsWall() throws Exception {
        Pacman pacman = new Pacman(maze);
        assertThat(pacman.getNextTile(new TileCoordinate(6, 26)), equalTo(new TileCoordinate(6, 26)));
        assertThat(pacman.isMoving(), is(false));
        assertThat(pacman.getDirection(), equalTo(Direction.NONE));
        assertThat(pacman.getPreviousDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldStopAndRememberPreviousDirectionIfNextInCurrentAndDesiredDirectionTileIsWall() throws Exception {
        Pacman pacman = new Pacman(maze);
        pacman.setNextDirection(Direction.UP);
        assertThat(pacman.getNextTile(new TileCoordinate(15, 29)), equalTo(new TileCoordinate(15, 29)));
        assertThat(pacman.isMoving(), is(false));
        assertThat(pacman.getDirection(), equalTo(Direction.NONE));
        assertThat(pacman.getPreviousDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void isDead_shouldBeFalseByDefault() throws Exception {
        final Pacman pacman = new Pacman(maze);
        assertFalse(pacman.isDead());
    }

    @Test
    public void die_shouldKillPacman() throws Exception {
        final Pacman pacman = new Pacman(maze);

        pacman.die();

        assertTrue(pacman.isDead());
    }
}
