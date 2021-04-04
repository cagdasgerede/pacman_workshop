package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.event.MouseEvent;

import java.awt.Graphics2D;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IntroScreenTest {
    @Mock
    private Graphics2D graphics;

    @Test
    public void draw_shouldDrawSplashImageAcrossWidth() throws Exception {
        Game game = new Game("initialize");
        IntroScreen introScreen = new IntroScreen(game);

        introScreen.draw(graphics);

        verify(graphics).drawImage(IntroScreen.TITLE_SCREEN_IMAGE, 0, 0, 448, 448, null);
    }

    @Test
    public void nextScreen_shouldReturnIntroScreen_whenKeyNotPressed() throws Exception {
        Game game = new Game("initialize");
        IntroScreen introScreen = new IntroScreen(game);

        assertThat(introScreen.getNextScreen(), instanceOf(IntroScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnGameScreen_whenKeyPressed() throws Exception {
        Game game = new Game("initialize");
        IntroScreen introScreen = new IntroScreen(game);

        introScreen.keyPressed(null);

        assertThat(introScreen.getNextScreen(), instanceOf(GameScreen.class));
    }

    @Test
    public void isDifficultySetCorrectly() throws Exception {
        MouseEvent e = mock(MouseEvent.class);
        Game game = new Game("initialize");
        IntroScreen introScreen = new IntroScreen(game);
        introScreen.setSettingsMenu(true);

        when(e.getX()).thenReturn(200);
        when(e.getY()).thenReturn(180);
        introScreen.mouseClicked(e);
        assertTrue(introScreen.getDifficultyMode().equals("easy"));

        when(e.getY()).thenReturn(250);
        introScreen.mouseClicked(e);
        assertTrue(introScreen.getDifficultyMode().equals("medium"));

        when(e.getY()).thenReturn(300);
        introScreen.mouseClicked(e);
        assertTrue(introScreen.getDifficultyMode().equals("hard"));
    }
}
