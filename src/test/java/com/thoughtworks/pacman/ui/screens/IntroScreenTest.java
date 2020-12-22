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
public class IntroScreenTest {

    @Test	   
    public void draw_shouldDrawSplashImageAcrossWidth() throws Exception {
        Game game = new Game();	
        IntroScreen introScreen = new IntroScreen(game);

        Graphics2D graphics = Mockito.mock(Graphics2D.class, Mockito.RETURNS_DEEP_STUBS);
        RectangleButton rectangleButton = Mockito.mock(RectangleButton.class);
        introScreen.setDrawRectangle(rectangleButton);

        Color buttonMainColor = Color.YELLOW;
        Rectangle playClickBox = new Rectangle(180, 310, 100, 30);
        Rectangle settingsClickBox = new Rectangle(180, 350, 100, 30);
        Rectangle exitClickBox =new Rectangle(180, 390, 100, 30);

        introScreen.draw(graphics);	

        verify(graphics).drawImage(IntroScreen.TITLE_SCREEN_IMAGE, 0, 0, 448, 448, null);
        verify(rectangleButton).drawButtons("PLAY", buttonMainColor, playClickBox, graphics);
        verify(rectangleButton).drawButtons("SETTINGS", buttonMainColor, settingsClickBox, graphics);
        verify(rectangleButton).drawButtons("QUIT", buttonMainColor, exitClickBox, graphics);

    }

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
