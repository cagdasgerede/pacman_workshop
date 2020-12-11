package com.thoughtworks.pacman.ui.screens;

import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Rectangle;

import java.awt.font.TextLayout;


import com.thoughtworks.pacman.core.Game;
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
    

    public IntroScreen(Game game) {
        this.dimension = game.getDimension();
        buttonMainColor = new Color(255, 255, 0);
        buttonOnHoverColor = new Color(156, 156, 2);
        playClickBox = new Rectangle(180, 310, 100, 30);
        settingsClickBox = new Rectangle(180, 350, 100, 30);
        exitClickBox =new Rectangle(180, 390, 100, 30);
        this.startGame = false;

    }

    public void draw(Graphics2D graphics) {
        int height = TITLE_SCREEN_IMAGE.getHeight(null) * dimension.width / TITLE_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(TITLE_SCREEN_IMAGE, 0, 0, dimension.width, height, null);

        if(currentStateofPlayButton == State.RELEASED_PLAY_BUTTON){
            graphics.setColor(buttonMainColor);
            graphics.fill(playClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int)graphics.getFontMetrics().getStringBounds("PLAY", graphics).getWidth();
            TextLayout tl = new TextLayout("PLAY",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("PLAY",  playClickBox.x + playClickBox.width / 2  - textWidth / 2,playClickBox.y + playClickBox.height / 2  + textHeight / 2 );

        }
        if(currentStateOfSettingsButton == State.RELEASED_SETTINGS_BUTTON){
            graphics.setColor(buttonMainColor);
            graphics.fill(settingsClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int)graphics.getFontMetrics().getStringBounds("SETTINGS", graphics).getWidth();
            TextLayout tl = new TextLayout("SETTINGS",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("SETTINGS",  settingsClickBox.x + settingsClickBox.width / 2  - textWidth / 2,settingsClickBox.y + settingsClickBox.height / 2  + textHeight / 2 );

        }
        if(currentStateOfQuitButton == State.RELEASED_QUIT_BUTTON){
            graphics.setColor(buttonMainColor);
            graphics.fill(exitClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int)graphics.getFontMetrics().getStringBounds("QUIT", graphics).getWidth();
            TextLayout tl = new TextLayout("QUIT",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("QUIT",  exitClickBox.x + exitClickBox.width / 2  - textWidth / 2,exitClickBox.y + exitClickBox.height / 2  + textHeight / 2 );
		}
		if(currentStateofPlayButton == State.HOVER_ON_PLAY_BUTTON){
			graphics.setColor(buttonOnHoverColor);
            graphics.fill(playClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int)graphics.getFontMetrics().getStringBounds("PLAY", graphics).getWidth();
            TextLayout tl = new TextLayout("PLAY",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("PLAY",  playClickBox.x + playClickBox.width / 2  - textWidth / 2,playClickBox.y + playClickBox.height / 2  + textHeight / 2 );
        }
        if(currentStateOfSettingsButton == State.HOVER_ON_SETTINGS_BUTTON){
			graphics.setColor(buttonOnHoverColor);
            graphics.fill(settingsClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int)graphics.getFontMetrics().getStringBounds("SETTINGS", graphics).getWidth();
            TextLayout tl = new TextLayout("SETTINGS",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("SETTINGS",  settingsClickBox.x + settingsClickBox.width / 2  - textWidth / 2,settingsClickBox.y + settingsClickBox.height / 2  + textHeight / 2 );
        }
        if(currentStateOfQuitButton == State.HOVER_ON_QUIT_BUTTON){
			graphics.setColor(buttonOnHoverColor);
            graphics.fill(exitClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int)graphics.getFontMetrics().getStringBounds("QUIT", graphics).getWidth();
            TextLayout tl = new TextLayout("QUIT",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("QUIT",  exitClickBox.x + exitClickBox.width / 2  - textWidth / 2,exitClickBox.y + exitClickBox.height / 2  + textHeight / 2 );
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
		HOVER_ON_PLAY_BUTTON, RELEASED_PLAY_BUTTON,HOVER_ON_SETTINGS_BUTTON, RELEASED_SETTINGS_BUTTON,HOVER_ON_QUIT_BUTTON, RELEASED_QUIT_BUTTON
	}

    @Override
    public void eventDispatcher(MouseEvent event) {
        if (event instanceof MouseEvent) {
            MouseEvent e = (MouseEvent) event;

            if(playClickBox.contains(e.getPoint())){
                currentStateofPlayButton = State.HOVER_ON_PLAY_BUTTON;
            }
            else{
                currentStateofPlayButton = State.RELEASED_PLAY_BUTTON;
            }
            
            if(settingsClickBox.contains(e.getPoint())){
                currentStateOfSettingsButton = State.HOVER_ON_SETTINGS_BUTTON;
            }
            else{
                currentStateOfSettingsButton = State.RELEASED_SETTINGS_BUTTON;
            }
            if(exitClickBox.contains(e.getPoint())){
                currentStateOfQuitButton = State.HOVER_ON_QUIT_BUTTON;
            }
            else{
                currentStateOfQuitButton = State.RELEASED_QUIT_BUTTON;
            }       
            if(playClickBox.contains(e.getPoint()) && e.getClickCount()>=1){
                startGame=true;
            }
            if(exitClickBox.contains(e.getPoint()) && e.getClickCount()>=1){
                System.exit(0);
            }
            
        }
    }
   
    
}
