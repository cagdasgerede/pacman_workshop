package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.Graphics2D;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ScoreScreenTest {
    @Mock
    private Graphics2D graphics;

    @Test
    public void draw_shouldDrawSplashImageAcrossWidth() throws Exception {
        Game game = new Game();
        ScoreScreen scoreScreen = new ScoreScreen(game);

        scoreScreen.draw(graphics);

        verify(graphics).drawImage(ScoreScreen.SCORE_SCREEN_IMAGE, 0, 0, 448, 448, null);
    }

    @Test
    public void nextScreen_shouldReturnLostScreen_whenKeyNotPressed() throws Exception {
        Game game = new Game();
        ScoreScreen scoreScreen = new ScoreScreen(game,true);

        assertThat(scoreScreen.getNextScreen(), instanceOf(ScoreScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnIntroScreen_whenKeyPressed() throws Exception {
        Game game = new Game();
        ScoreScreen lostScreen = new ScoreScreen(game);

        lostScreen.keyPressed(null);

        assertThat(lostScreen.getNextScreen(), instanceOf(IntroScreen.class));
        assertTrue(true);
    }

    @Test
    public void listInitialization_ShouldEmptyScoresAndNames(){

    }
}
