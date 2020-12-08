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
public class WinScreenTest {
    @Mock
    private Graphics2D graphics;

   /* @Test
    public void draw_shouldDrawSplashImageAcrossWidth() throws Exception {
        Game game = new Game();
        WinScreen winScreen = new WinScreen(game);

        winScreen.draw(graphics);

        verify(graphics).drawImage(WinScreen.WIN_SCREEN_IMAGE, 0, 0, 448, 467, null);
    }*/

    @Test
    public void nextScreen_shouldReturnWinScreen_whenNotClicked() throws Exception {
        Game game = new Game();
        WinScreen winScreen = new WinScreen(game);

        assertThat(winScreen.getNextScreen(), instanceOf(WinScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnIntroScreen_whenReturnToMainMenuIsClicked() throws Exception {
        Game game = new Game();
        WinScreen winScreen = new WinScreen(game);

        winScreen.setStartGame(true);

        assertThat(winScreen.getNextScreen(), instanceOf(IntroScreen.class));
    }
}
