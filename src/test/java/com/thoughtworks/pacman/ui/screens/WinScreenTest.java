package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class WinScreenTest {

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
