package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.RectangleButton;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class WinScreenTest {

    @Test	   
    public void draw_shouldDrawSplashImageAcrossWidth() throws Exception {
        Game game = new Game();	
        WinScreen winScreen = new WinScreen(game);

        Graphics2D graphics = Mockito.mock(Graphics2D.class, Mockito.RETURNS_DEEP_STUBS);
        RectangleButton rectangleButton = Mockito.mock(RectangleButton.class);
        winScreen.setDrawRectangle(rectangleButton);

        Color buttonMainColor = Color.YELLOW;
        Rectangle returnClickBox = new Rectangle(130, 450, 200, 30);
        Rectangle exitClickBox = new Rectangle(180, 490, 100, 30);

        winScreen.draw(graphics);	

        verify(graphics).drawImage(winScreen.WIN_SCREEN_IMAGE, 0, 0, 448, 448, null);
        verify(rectangleButton).drawButtons("RETURN TO MAIN MENU", buttonMainColor, returnClickBox, graphics);
        verify(rectangleButton).drawButtons("QUIT", buttonMainColor, exitClickBox, graphics);

    }

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
