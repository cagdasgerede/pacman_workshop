package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.Graphics2D;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

public class ScoreBoardTest {

    @Test
    public void ScoreBoardTest() throws Exception {
        ScoreBoard score = new ScoreBoard();
    }

    @Test
    public void ScoreBoardTextTest() throws Exception {
        ScoreBoard score = new ScoreBoard();
        assertEquals(score.Text.length(), 0);
    }

    @Test
    public void ScoreBoardTitleTest() throws Exception {
        ScoreBoard score = new ScoreBoard();
        assertEquals(score.getTitle(), "ScoreBoard");
    }

}
