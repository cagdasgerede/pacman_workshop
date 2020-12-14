package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.actors.ClonePacman;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.awt.Rectangle;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClonePacmanPresenterTest {
    @Test
    public void shouldCalculateBoundInPixels() throws Exception {
        ClonePacmanPresenter presenter = new ClonePacmanPresenter(new ClonePacman(MazeBuilder.buildDefaultMaze()));
        assertThat(presenter.getBounds(), equalTo(new Rectangle(214, 414, ClonePacmanPresenter.DIAMETER, ClonePacmanPresenter.DIAMETER)));
    }

    @Test
    public void shouldAnimateMouth() throws Exception {
        ClonePacmanPresenter presenter = new ClonePacmanPresenter(new ClonePacman(MazeBuilder.buildDefaultMaze()));
        for (int i = 0; i < 5; i++) {
            assertThat(presenter.getArcAngle(), equalTo(ClonePacmanPresenter.MOUTH_CLOSED));
        }
        for (int i = 0; i < 5; i++) {
            assertThat(presenter.getArcAngle(), equalTo(ClonePacmanPresenter.MOUTH_OPENED));
        }
        for (int i = 0; i < 5; i++) {
            assertThat(presenter.getArcAngle(), equalTo(ClonePacmanPresenter.MOUTH_CLOSED));
        }
    }

    @Test
    public void shouldAnimateDying() throws Exception {
        ClonePacman pacman = new ClonePacman(MazeBuilder.buildDefaultMaze());
        pacman.die();
        ClonePacmanPresenter presenter = new ClonePacmanPresenter(pacman);

        for (int i = 1; i <= ClonePacmanPresenter.MOUTH_CLOSED / ClonePacmanPresenter.DEATH_FRAMES; i++) {
            assertThat(presenter.getArcAngle(), equalTo(ClonePacmanPresenter.MOUTH_CLOSED - i * ClonePacmanPresenter.DEATH_FRAMES));
        }
        assertThat(presenter.getArcAngle(), equalTo(0));
        assertThat(presenter.getArcAngle(), equalTo(0));
    }

    @Test
    public void isDying_shouldBeFalseByDefault() throws Exception {
        ClonePacmanPresenter presenter = new ClonePacmanPresenter(new ClonePacman(MazeBuilder.buildDefaultMaze()));
        Assert.assertThat(presenter.isDying(), is(false));
    }

    @Test
    public void die_shouldKillClonePacmanSlowly() throws Exception {
        ClonePacman pacman = new ClonePacman(MazeBuilder.buildDefaultMaze());
        ClonePacmanPresenter presenter = new ClonePacmanPresenter(pacman);

        pacman.die();

        int totalDeathFrames = ClonePacmanPresenter.MOUTH_CLOSED / ClonePacmanPresenter.DEATH_FRAMES + ClonePacmanPresenter.DELAY_AFTER_DEAD;
        for (int times = 1; times <= totalDeathFrames + 1; times++) {
            assertThat(times + "th time", presenter.isDying(), is(true));
        }

        assertThat(presenter.isDying(), is(false));
    }
}
