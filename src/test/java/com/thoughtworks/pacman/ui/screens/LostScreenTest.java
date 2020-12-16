package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class LostScreenTest {

    @Test
    public void nextScreen_shouldReturnLostScreen_whenNotClicked() throws Exception {
        Game game = new Game();
        LostScreen lostScreen = new LostScreen(game);

        assertThat(lostScreen.getNextScreen(), instanceOf(LostScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnIntroScreen_whenReturnToMainMenuIsClicked() throws Exception {
        Game game = new Game();
        LostScreen lostScreen = new LostScreen(game);

        lostScreen.setReturnToMainMenu(true);

        assertThat(lostScreen.getNextScreen(), instanceOf(IntroScreen.class));
    }
}
