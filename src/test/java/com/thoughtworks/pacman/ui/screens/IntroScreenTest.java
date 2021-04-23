package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IntroScreenTest {
    @Mock
    private Graphics2D graphics;
    @Mock
    private KeyEvent keyEvent;

    @Test
    public void draw_shouldDrawSplashImageAcrossWidth() throws Exception {
        Game game = new Game();
        IntroScreen introScreen = new IntroScreen(game);

        introScreen.draw(graphics);

        verify(graphics).drawImage(IntroScreen.TITLE_SCREEN_IMAGE, 0, 0, 448, 448, null);
    }

    @Test
    public void nextScreen_shouldReturnIntroScreen_whenKeyNotPressed() throws Exception {
        Game game = new Game();
        IntroScreen introScreen = new IntroScreen(game);
        
        assertThat(introScreen.getNextScreen(), instanceOf(IntroScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnGameScreen_whenKeyPressed() throws Exception {
        Game game = new Game();
        IntroScreen introScreen = new IntroScreen(game);
        introScreen.keyPressed(keyEvent);
        assertThat(introScreen.getNextScreen(), instanceOf(GameScreen.class));
    }

    @Test
    public void settingsScreen_shouldImplementSettingsWindow_whenKeyPressed() throws Exception {
        Game game = new Game();
        IntroScreen introScreen = new IntroScreen(game);
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_A);
        introScreen.keyPressed(keyEvent);
        assertFalse(introScreen.getStartGame());
    }
}
