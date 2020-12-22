package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.Tile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.Graphics2D;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MultiplayerEndScreenTest {
    @Mock
    private Graphics2D graphics;

    @Test
    public void draw_shouldDrawSplashImageAcrossWidth() throws Exception {
        Game game1 = new Game();
        Game game2 = new Game();

        MultiplayerEndScreen multiplayerEndScreen = new MultiplayerEndScreen(game1, game2);
        multiplayerEndScreen.draw(graphics);

        verify(graphics).drawImage(LostScreen.LOST_SCREEN_IMAGE, 0, 0, 448, 448, null);
        verify(graphics).drawImage(LostScreen.LOST_SCREEN_IMAGE, 448 + Tile.SIZE * 2, 0, 448, 448, null);
    }

    @Test
    public void nextScreen_shouldReturnWinScreen_whenKeyNotPressed() throws Exception {
        Game game1 = new Game();
        Game game2 = new Game();
        MultiplayerEndScreen multiplayerEndScreen = new MultiplayerEndScreen(game1, game2);

        assertThat(multiplayerEndScreen.getNextScreen(), instanceOf(MultiplayerEndScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnIntroScreen_whenKeyPressed() throws Exception {
        Game game1 = new Game();
        Game game2 = new Game();
        MultiplayerEndScreen multiplayerEndScreen = new MultiplayerEndScreen(game1, game2);

        multiplayerEndScreen.keyPressed(null);

        assertThat(multiplayerEndScreen.getNextScreen(), instanceOf(IntroScreen.class));
    }
}
