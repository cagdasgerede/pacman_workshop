package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Button;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;


public class IntroScreen implements Screen {
    static final Image TITLE_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "titleScreen.jpg");

    private final Dimension dimension;
    private boolean startGame;
    private State currentStateofPlayButton = State.RELEASED_PLAY_BUTTON;
    private State currentStateOfSettingsButton = State.RELEASED_SETTINGS_BUTTON;
    private State currentStateOfQuitButton = State.RELEASED_QUIT_BUTTON;
    private Color buttonMainColor; // main color theme: Yellow
    private Color buttonOnHoverColor; // when on hover color theme:Darker Yellow
    
    private Rectangle playClickBox;
    private Rectangle settingsClickBox;
    private Rectangle exitClickBox;
    private Button drawRectangle;
    

    public IntroScreen(Game game) {
        this.dimension = game.getDimension();
        buttonMainColor = new Color(255, 255, 0);
        buttonOnHoverColor = new Color(156, 156, 2);
        playClickBox = new Rectangle(180, 310, 100, 30);
        settingsClickBox = new Rectangle(180, 350, 100, 30);
        exitClickBox =new Rectangle(180, 390, 100, 30);
        drawRectangle = new Button();
        this.startGame = false;

    }

    public void draw(Graphics2D graphics) {
        int height = TITLE_SCREEN_IMAGE.getHeight(null) * dimension.width / TITLE_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(TITLE_SCREEN_IMAGE, 0, 0, dimension.width, height, null);

        if(currentStateofPlayButton == State.RELEASED_PLAY_BUTTON){
            drawRectangle.draw("PLAY", buttonMainColor, playClickBox, graphics);
        }
        if(currentStateOfSettingsButton == State.RELEASED_SETTINGS_BUTTON){
            drawRectangle.draw("SETTINGS", buttonMainColor, settingsClickBox, graphics);
        }
        if(currentStateOfQuitButton == State.RELEASED_QUIT_BUTTON){
            drawRectangle.draw("QUIT", buttonMainColor, exitClickBox, graphics);
		}
		if(currentStateofPlayButton == State.HOVER_ON_PLAY_BUTTON){
            drawRectangle.draw("PLAY", buttonOnHoverColor, playClickBox, graphics);
        }
        if(currentStateOfSettingsButton == State.HOVER_ON_SETTINGS_BUTTON){
            drawRectangle.draw("SETTINGS", buttonOnHoverColor, settingsClickBox, graphics);
        }
        if(currentStateOfQuitButton == State.HOVER_ON_QUIT_BUTTON){
            drawRectangle.draw("QUIT", buttonOnHoverColor, exitClickBox, graphics);

		}
        
    }

    public Screen getNextScreen() throws Exception {
        if (startGame) {
            return new GameScreen();
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
       // startGame = true; 
    }

    public void setStartGame(boolean bool){
        this.startGame = bool;
    }


    private enum State{
        HOVER_ON_PLAY_BUTTON, 
        RELEASED_PLAY_BUTTON,
        HOVER_ON_SETTINGS_BUTTON, 
        RELEASED_SETTINGS_BUTTON,
        HOVER_ON_QUIT_BUTTON, 
        RELEASED_QUIT_BUTTON
	}

    @Override
    public void eventDispatcher(MouseEvent event) {
        if (event instanceof MouseEvent) {
            MouseEvent e = (MouseEvent) event;

            if(playClickBox.contains(e.getPoint())){
                currentStateofPlayButton = State.HOVER_ON_PLAY_BUTTON;
                if(e.getID() == MouseEvent.MOUSE_CLICKED)
                    startGame = true;
            }
            else if(settingsClickBox.contains(e.getPoint())){
                currentStateOfSettingsButton = State.HOVER_ON_SETTINGS_BUTTON;
            }
            else if(exitClickBox.contains(e.getPoint())){
                currentStateOfQuitButton = State.HOVER_ON_QUIT_BUTTON;
                if(e.getID() == MouseEvent.MOUSE_CLICKED)
                    System.exit(0);
            }
            else{
                currentStateOfSettingsButton = State.RELEASED_SETTINGS_BUTTON;
                currentStateofPlayButton = State.RELEASED_PLAY_BUTTON;
                currentStateOfQuitButton = State.RELEASED_QUIT_BUTTON;
            }            
        }
    }
}
