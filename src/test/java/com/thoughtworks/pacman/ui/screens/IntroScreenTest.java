package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class IntroScreenTest {

    @Test
    public void nextScreen_shouldReturnIntroScreen_whenPlayNotClicked() throws Exception {
        Game game = new Game();
        IntroScreen introScreen = new IntroScreen(game);

        assertThat(introScreen.getNextScreen(), instanceOf(IntroScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnGameScreen_whenPlayButtonPressed() throws Exception {
        Game game = new Game();
        IntroScreen introScreen = new IntroScreen(game);

        introScreen.setStartGame(true);

        assertThat(introScreen.getNextScreen(), instanceOf(GameScreen.class));
    }

    
}
