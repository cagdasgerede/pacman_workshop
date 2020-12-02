package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextLayout;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;
import javax.swing.JPanel;


public class LostScreen extends JPanel implements Screen {
    static final Image LOST_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "EditedgameOver.jpg");

    private final Dimension dimension;
    private final Game game;
    private boolean startGame;

    private Color main;
	private Color hover;

    private State currentStateRETURN = State.RELEASED__RETURN;
    private State currentStateQUIT = State.RELEASED__QUIT;

    private Rectangle returnClickBox;
    private Rectangle exitClickBox;

    public LostScreen(Game game) {
        this.dimension = game.getDimension();
        main = new Color(255, 255, 0);
        hover = new Color(156, 156, 2);
        returnClickBox = new Rectangle(130, 350, 200, 30);
        exitClickBox = new Rectangle(180, 390, 100, 30);
        this.game = game;

        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                if (event instanceof MouseEvent) {
                    MouseEvent e = (MouseEvent) event;

                    if(returnClickBox.contains(e.getPoint())){
                        currentStateRETURN = State.HOVER_RETURN;
                    }
                    else{
                        currentStateRETURN = State.RELEASED__RETURN;
                    }
                    if(exitClickBox.contains(e.getPoint())){
                        currentStateQUIT = State.HOVER_QUIT;
                    }
                    else{
                        currentStateQUIT = State.RELEASED__QUIT;
                    }       
                    if(returnClickBox.contains(e.getPoint()) && e.getClickCount()==1){
                        startGame=true;
                    }
                    if(exitClickBox.contains(e.getPoint()) && e.getClickCount()==1){
                        System.exit(0);
                    }
                    
                }
            
                
                
            }
        }, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);

        this.startGame = false;
    }

    public void draw(Graphics2D graphics) {
        int height = LOST_SCREEN_IMAGE.getHeight(null) * dimension.width / LOST_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(LOST_SCREEN_IMAGE, 0, 0, dimension.width, height, null);

        if(currentStateRETURN == State.RELEASED__RETURN){
            graphics.setColor(main);
            graphics.fill(returnClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int)graphics.getFontMetrics().getStringBounds("RETURN TO MAIN MENU", graphics).getWidth();
            TextLayout tl = new TextLayout("RETURN TO MAIN MENU",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("RETURN TO MAIN MENU",  returnClickBox.x + returnClickBox.width / 2  - textWidth / 2,returnClickBox.y + returnClickBox.height / 2  + textHeight / 2 );


        }
        if(currentStateRETURN == State.HOVER_RETURN){
            graphics.setColor(hover);
            graphics.fill(returnClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int)graphics.getFontMetrics().getStringBounds("RETURN TO MAIN MENU", graphics).getWidth();
            TextLayout tl = new TextLayout("RETURN TO MAIN MENU",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("RETURN TO MAIN MENU",  returnClickBox.x + returnClickBox.width / 2  - textWidth / 2,returnClickBox.y + returnClickBox.height / 2  + textHeight / 2 );


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

    public Screen getNextScreen() {
        if (startGame) {
            return new IntroScreen(game);
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        startGame = true;
    }

    private enum State{
		HOVER_RETURN, RELEASED__RETURN,HOVER_QUIT, RELEASED__QUIT
	}
}
