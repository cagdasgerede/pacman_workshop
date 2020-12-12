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

public class ScorePanelTest {

    @Test
    public void ScoreBoardTest() throws Exception {
        ScorePanel panel = new ScorePanel(10);
    }

    @Test
    public void ScoreBoardTextTest() throws Exception {
        ScorePanel panel = new ScorePanel(10);
        assertEquals(panel.score, 10);
    }

    @Test
    public void ScoreBoardTitleTest() throws Exception {
        ScorePanel panel = new ScorePanel(10);
        assertEquals(panel.getTitle(), "Create new user");
    }

}
