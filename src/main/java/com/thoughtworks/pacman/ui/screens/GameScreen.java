package com.thoughtworks.pacman.ui.screens;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Rectangle;

import java.awt.font.TextLayout;
import java.awt.*;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;



import com.thoughtworks.pacman.ui.ImageLoader;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.Image;




public class GameScreen  implements Screen {

    static final Image SETTINGS_IMAGE = ImageLoader.loadImage(Screen.class, "settings.png");

    private Rectangle imageClickBox;    //cannot click on images so this imageclick box is a invisible rectangle taht has the same height and width as the image
    private Rectangle settingsBlock;
    private Rectangle returnClickBox;
    private Rectangle resumeClickBox;

    private Color main; // main color theme
    private Color hover; // when on hover color theme
    
    private final Game game;
    
 
    private final GamePresenter gamePresenter;
    private long lastFrameAt;

    //the current States
    private State currentStateSETTINGS = State.SETTINGS_GONE;   
    private State currentStateRESUME = State.RELEASED__RESUME;
    private State currentStateRETURN = State.RELEASED__RETURN;

    private boolean areClickBoxesVisible=false; //to control the visibility of our blocks
    private boolean returnIntroScreen = false;  //to control  when to return to the main screen 
    private boolean stopTheGame =false;         //to pause the game while the pause menu is open 

    //Counts the amount of clicks on pause menu and counts the amount od escape is pressed
    
   

   
    public boolean getStopTheGame(){
        return stopTheGame;
    }

    public GameScreen() throws Exception {
        this(new Game());
    }

    private GameScreen(Game game) {
        this(game, new GamePresenter(game));
    }

    GameScreen(Game game, GamePresenter gamePresenter) {
        this.game = game;
        this.gamePresenter = gamePresenter;
        this.lastFrameAt = System.currentTimeMillis();

       
        main = new Color(255, 255, 0);
        hover = new Color(156, 156, 2);

        imageClickBox = new Rectangle(5,5,40,40);
        settingsBlock = new Rectangle(70,100,300,350);
        returnClickBox = new Rectangle(120,300,200,30);
        resumeClickBox = new Rectangle(170,340,100,30);

        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {


            @Override
            public void eventDispatched(AWTEvent event) {
                if (event instanceof MouseEvent) {
                    
                    MouseEvent e = (MouseEvent) event;
              
                    if(imageClickBox.contains(e.getPoint()) && e.getID()== MouseEvent.MOUSE_CLICKED){
                        if(currentStateSETTINGS == State.SETTINGS_GONE) {//ilk defa click ediyo settingse
                            currentStateSETTINGS = State.SETTINGS_PRESSED;
                            
                        }else { //click etmis ve acik menu suan
                            currentStateSETTINGS = State.SETTINGS_GONE;
                            
                            
                        }
                    }
                    if(returnClickBox.contains(e.getPoint())){
                        currentStateRETURN = State.HOVER_RETURN;
                    }
                    else{
                        currentStateRETURN = State.RELEASED__RETURN;
                    }
                    if(resumeClickBox.contains(e.getPoint())){
                        currentStateRESUME = State.HOVER_RESUME;
                    }
                    else{
                        currentStateRESUME = State.RELEASED__RESUME;
                    }

                    if(areClickBoxesVisible && returnClickBox.contains(e.getPoint()) && e.getID()== MouseEvent.MOUSE_CLICKED){
                        currentStateRETURN = State.HOVER_RETURN;
                        returnIntroScreen =true;

                    }

                    if(areClickBoxesVisible && resumeClickBox.contains(e.getPoint()) && e.getID()== MouseEvent.MOUSE_CLICKED){
                        currentStateRESUME = State.HOVER_RESUME;
                        currentStateSETTINGS = State.SETTINGS_GONE; 

                    }
                    
                }           
            }
        }, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);

    }

    public void draw(Graphics2D graphics) {
        
        gamePresenter.draw(graphics);
        graphics.drawImage(SETTINGS_IMAGE, 5, 5, 40, 40, null); 
        
        graphics.setColor(new Color(255, 255, 255,0));
        graphics.fill(imageClickBox);
        long timeDelta;
        long currentFrameAt;
        
        
        if(currentStateSETTINGS==State.SETTINGS_PRESSED){
            
            graphics.setColor(new Color(0, 0, 0,230));
            graphics.fill(settingsBlock);
            areClickBoxesVisible =true;
            stopTheGame=true;
            graphics.setColor(Color.WHITE);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 80));
            int textWidth = (int)graphics.getFontMetrics().getStringBounds(""+game.Score, graphics).getWidth();
            TextLayout tl = new TextLayout(""+game.Score,new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString(""+game.Score,  settingsBlock.x + settingsBlock.width / 2  - textWidth / 2,settingsBlock.y + settingsBlock.height / 2  + textHeight / 2 - 50 );
            if(currentStateRETURN == State.RELEASED__RETURN){
                graphics.setColor(main);
                graphics.fill(returnClickBox);
                graphics.setColor(Color.BLACK);
                graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
                textWidth = (int)graphics.getFontMetrics().getStringBounds("RETURN TO MAIN MENU", graphics).getWidth();
                tl = new TextLayout("RETURN TO MAIN MENU",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
                textHeight = (int) tl.getBounds().getHeight();
                graphics.drawString("RETURN TO MAIN MENU",  returnClickBox.x + returnClickBox.width / 2  - textWidth / 2,returnClickBox.y + returnClickBox.height / 2  + textHeight / 2 );
    
    
            }
            if(currentStateRETURN == State.HOVER_RETURN){
                graphics.setColor(hover);
                graphics.fill(returnClickBox);
                graphics.setColor(Color.BLACK);
                graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
                textWidth = (int)graphics.getFontMetrics().getStringBounds("RETURN TO MAIN MENU", graphics).getWidth();
                tl = new TextLayout("RETURN TO MAIN MENU",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
                textHeight = (int) tl.getBounds().getHeight();
                graphics.drawString("RETURN TO MAIN MENU",  returnClickBox.x + returnClickBox.width / 2  - textWidth / 2,returnClickBox.y + returnClickBox.height / 2  + textHeight / 2 );
    
    
            }
            if(currentStateRESUME == State.RELEASED__RESUME){
                graphics.setColor(main);
                graphics.fill(resumeClickBox);
                graphics.setColor(Color.BLACK);
                graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
    
              
                textWidth = (int)graphics.getFontMetrics().getStringBounds("RESUME", graphics).getWidth();
                tl = new TextLayout("RESUME",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
                textHeight = (int) tl.getBounds().getHeight();
                graphics.drawString("RESUME",  resumeClickBox.x + resumeClickBox.width / 2  - textWidth / 2,resumeClickBox.y + resumeClickBox.height / 2  + textHeight / 2 );
            }
            if(currentStateRESUME == State.HOVER_RESUME){
                graphics.setColor(hover);
                graphics.fill(resumeClickBox);
                graphics.setColor(Color.BLACK);
                graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
    
                
                textWidth = (int)graphics.getFontMetrics().getStringBounds("RESUME", graphics).getWidth();
                tl = new TextLayout("RESUME",new java.awt.Font("Yu Gothic UI Semibold", 1, 14), graphics.getFontRenderContext());
                textHeight = (int) tl.getBounds().getHeight();
                graphics.drawString("RESUME",  resumeClickBox.x + resumeClickBox.width / 2  - textWidth / 2,resumeClickBox.y + resumeClickBox.height / 2  + textHeight / 2 );
            }
    
            
        }else if(currentStateSETTINGS == State.SETTINGS_GONE ){ // yani onceden click edilmis menunun kapanmasini istiyoruz
            graphics.setColor(new Color(0, 0, 0,0));
            graphics.fill(settingsBlock);   //setting block clear
            graphics.fill(returnClickBox);  //return to main menu clear
            graphics.fill(resumeClickBox);  //resume clear
            areClickBoxesVisible =false;
            stopTheGame=false;
           

        }
        
        currentFrameAt = System.currentTimeMillis();

        timeDelta = currentFrameAt - lastFrameAt;
        
        game.advance(timeDelta,stopTheGame);
        
        lastFrameAt = currentFrameAt;
        

        
        
    }

    public Screen getNextScreen() {
        if (game.won()) {
            return new WinScreen(game);
        } else if (game.lost() && !gamePresenter.isDying()) {
            return new LostScreen(game);
        }else if(returnIntroScreen)
            return new IntroScreen(game);
        return this;
    }

    public void keyPressed(KeyEvent e) { //buraya escape key i ekle
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            game.getPacman().setNextDirection(Direction.LEFT);
            break;
        case KeyEvent.VK_RIGHT:
            game.getPacman().setNextDirection(Direction.RIGHT);
            break;
        case KeyEvent.VK_UP:
            game.getPacman().setNextDirection(Direction.UP);
            break;
        case KeyEvent.VK_DOWN:
            game.getPacman().setNextDirection(Direction.DOWN);
            break;
        case KeyEvent.VK_ESCAPE:
            if(currentStateSETTINGS == State.SETTINGS_GONE){
                currentStateSETTINGS = State.SETTINGS_PRESSED; 
            }else{
                currentStateSETTINGS = State.SETTINGS_GONE; 
            }
            
            break;
        }
    }

    private enum State{
		HOVER_RESUME, RELEASED__RESUME,HOVER_RETURN, RELEASED__RETURN,SETTINGS_PRESSED, SETTINGS_GONE
	}
}
