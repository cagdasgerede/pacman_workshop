package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.RectangleButton;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class LostScreenTest {

    @Test	   
    public void draw_shouldDrawSplashImageAcrossWidth() throws Exception {
        Game game = new Game();	
        LostScreen lostScreen = new LostScreen(game);

        Graphics2D graphics = Mockito.mock(Graphics2D.class, Mockito.RETURNS_DEEP_STUBS);
        RectangleButton rectangleButton = Mockito.mock(RectangleButton.class);
        lostScreen.setDrawRectangle(rectangleButton);

        Color buttonMainColor = Color.YELLOW;
        Rectangle returnClickBox = new Rectangle(130, 350, 200, 30);
        Rectangle exitClickBox = new Rectangle(180, 390, 100, 30);

        lostScreen.draw(graphics);	

        verify(graphics).drawImage(LostScreen.LOST_SCREEN_IMAGE, 0, 0, 448, 448, null);
        verify(rectangleButton).drawButtons("RETURN TO MAIN MENU", buttonMainColor, returnClickBox, graphics);
        verify(rectangleButton).drawButtons("QUIT", buttonMainColor, exitClickBox, graphics);

    }

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

        lostScreen.setStartGame(true);

        assertThat(lostScreen.getNextScreen(), instanceOf(IntroScreen.class));
    }
}
