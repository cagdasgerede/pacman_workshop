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
    static final Image TITLE_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "deletedText.jpg");

    private final Dimension dimension;
    private boolean startGame;
    private State currentStatePLAY = State.RELEASED__PLAY;
    private State currentStateSETTINGS = State.RELEASED__SETTINGS;
    private State currentStateQUIT = State.RELEASED__QUIT;
    private Color main;
	private Color hover;
    
    private Rectangle playClickBox;
    private Rectangle settingsClickBox;
    private Rectangle exitClickBox;
    

    public IntroScreen(Game game) {
        this.dimension = game.getDimension();
        main = new Color(255, 255, 0);
        hover = new Color(156, 156, 2);
        playClickBox = new Rectangle(180, 310, 100, 30);
        settingsClickBox = new Rectangle(180, 350, 100, 30);
        exitClickBox =new Rectangle(180, 390, 100, 30);
              

        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                if (event instanceof MouseEvent) {
                    MouseEvent e = (MouseEvent) event;

                    if(playClickBox.contains(e.getPoint())){
                        currentStatePLAY = State.HOVER_PLAY;
                    }
                    else{
                        currentStatePLAY = State.RELEASED__PLAY;
                    }
                    
                    if(settingsClickBox.contains(e.getPoint())){
                        currentStateSETTINGS = State.HOVER_SETTINGS;
                    }
                    else{
                        currentStateSETTINGS = State.RELEASED__SETTINGS;
                    }
                    if(exitClickBox.contains(e.getPoint())){
                        currentStateQUIT = State.HOVER_QUIT;
                    }
                    else{
                        currentStateQUIT = State.RELEASED__QUIT;
                    }       
                    if(playClickBox.contains(e.getPoint()) && e.getClickCount()>=1){
                        startGame=true;
                    }
                    if(exitClickBox.contains(e.getPoint()) && e.getClickCount()>=1){
                        System.exit(0);
                    }
                    
                }
            
                
                
            }
        }, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
        
        this.startGame = false;

        
    }

    public void draw(Graphics2D graphics) {
        int height = TITLE_SCREEN_IMAGE.getHeight(null) * dimension.width / TITLE_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(TITLE_SCREEN_IMAGE, 0, 0, dimension.width, height, null);

        if(currentStatePLAY == State.RELEASED__PLAY){
            graphics.setColor(main);
            graphics.fill(playClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int)graphics.getFontMetrics().getStringBounds("PLAY", graphics).getWidth();
            TextLayout tl = new TextLayout("PLAY",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("PLAY",  playClickBox.x + playClickBox.width / 2  - textWidth / 2,playClickBox.y + playClickBox.height / 2  + textHeight / 2 );


        }
        
        if(currentStateSETTINGS == State.RELEASED__SETTINGS){
            graphics.setColor(main);
            graphics.fill(settingsClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int)graphics.getFontMetrics().getStringBounds("SETTINGS", graphics).getWidth();
            TextLayout tl = new TextLayout("SETTINGS",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("SETTINGS",  settingsClickBox.x + settingsClickBox.width / 2  - textWidth / 2,settingsClickBox.y + settingsClickBox.height / 2  + textHeight / 2 );

        }
        if(currentStateQUIT == State.RELEASED__QUIT){
            graphics.setColor(main);
            graphics.fill(exitClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));

          
            int textWidth = (int)graphics.getFontMetrics().getStringBounds("QUIT", graphics).getWidth();
            TextLayout tl = new TextLayout("QUIT",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("QUIT",  exitClickBox.x + exitClickBox.width / 2  - textWidth / 2,exitClickBox.y + exitClickBox.height / 2  + textHeight / 2 );
		}
		
		 if(currentStatePLAY == State.HOVER_PLAY){
			graphics.setColor(hover);
            graphics.fill(playClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));

            int textWidth = (int)graphics.getFontMetrics().getStringBounds("PLAY", graphics).getWidth();
            TextLayout tl = new TextLayout("PLAY",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("PLAY",  playClickBox.x + playClickBox.width / 2  - textWidth / 2,playClickBox.y + playClickBox.height / 2  + textHeight / 2 );

        }
        if(currentStateSETTINGS == State.HOVER_SETTINGS){
			graphics.setColor(hover);
            graphics.fill(settingsClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));

            
            int textWidth = (int)graphics.getFontMetrics().getStringBounds("SETTINGS", graphics).getWidth();
            TextLayout tl = new TextLayout("SETTINGS",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("SETTINGS",  settingsClickBox.x + settingsClickBox.width / 2  - textWidth / 2,settingsClickBox.y + settingsClickBox.height / 2  + textHeight / 2 );
        }

        if(currentStateQUIT == State.HOVER_QUIT){
			graphics.setColor(hover);
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
        startGame = true; //buralari silebilirsin
    }


    private enum State{
		HOVER_PLAY, RELEASED__PLAY,HOVER_SETTINGS, RELEASED__SETTINGS,HOVER_QUIT, RELEASED__QUIT
	}
   
    
}
