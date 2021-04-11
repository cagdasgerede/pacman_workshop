package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.Graphics2D;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LevelScreenTest {
    @Mock
    private Graphics2D graphics;

    @Test
    public void draw_shouldDrawSplashImageAcrossWidth() throws Exception {
        Game game = new Game();
        LevelScreen levelScreen = new LevelScreen(game);

        levelScreen.draw(graphics);
        //
    }

    @Test
    public void nextScreen_shouldReturnLevelScreen_whenKeyNotPressed() throws Exception {
        Game game = new Game();
        LevelScreen levelScreen = new LevelScreen(game);

        assertThat(levelScreen.getNextScreen(), instanceOf(LevelScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnNewGameScreen_whenKeyPressed() throws Exception {
        Game game = new Game();
        LevelScreen levelScreen = new LevelScreen(game);

        levelScreen.keyPressed(null);

        assertThat(levelScreen.getNextScreen(), instanceOf(GameScreen.class));
    }
}
