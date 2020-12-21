package com.thoughtworks.pacman.ui.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;
import com.thoughtworks.pacman.ui.RectangleButton;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.ParentScreen;

public class GameScreen extends ParentScreen {

    static final Image SETTINGS_IMAGE = ImageLoader.loadImage(Screen.class, "settings.png");

    private final Game game;
    private final GamePresenter gamePresenter;

    private Color buttonMainColor = Color.YELLOW; // main color theme: Yellow
    private Color buttonOnHoverColor = new Color(156, 156, 2); // when on hover color theme:Darker Yellow

    private State currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_NOT_PRESSED;
    private State currentStateOfResumeButton = State.RELEASED_RESUME_BUTTON;
    private State currentStateOfReturnButton = State.RELEASED_RETURN_BUTTON;

    private Rectangle invisibleImageClickBox = new Rectangle(5, 5, 40, 40); 
    private Rectangle settingsBlock = new Rectangle(70, 100, 300, 350);
    private Rectangle returnClickBox = new Rectangle(120, 300, 200, 30);
    private Rectangle resumeClickBox = new Rectangle(170, 340, 100, 30);

    private RectangleButton drawRectangle = new RectangleButton();

    private boolean areClickBoxesVisible = false; // to control the visibility of our blocks
    private boolean returnToIntroScreen = false; // to control when to return to the main screen
    private boolean stopTheGame = false; // to pause the game while the pause menu is open
    private long lastFrameAt;

    public boolean getStopTheGame() {
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
    }

    public void draw(Graphics2D graphics) {
        gamePresenter.draw(graphics);
        graphics.drawImage(SETTINGS_IMAGE, 5, 5, 40, 40, null);
        graphics.setColor(new Color(255, 255, 255, 0));
        graphics.fill(invisibleImageClickBox);
        long timeDelta;
        long currentFrameAt;

        if (currentStateofSettingsButton == State.SETTINGS_BUTTON_IS_PRESSED) {
            areClickBoxesVisible = true;
            drawRectangle.draw("" + game.getScore(), new Color(0, 0, 0, 230), settingsBlock, graphics);
            if (currentStateOfReturnButton == State.RELEASED_RETURN_BUTTON) {
                drawRectangle.draw("RETURN TO MAIN MENU", buttonMainColor, returnClickBox, graphics);
            }
            if (currentStateOfReturnButton == State.HOVER_ON_RETURN_BUTTON) {
                drawRectangle.draw("RETURN TO MAIN MENU", buttonOnHoverColor, returnClickBox, graphics);
            }
            if (currentStateOfResumeButton == State.RELEASED_RESUME_BUTTON) {
                drawRectangle.draw("RESUME", buttonMainColor, resumeClickBox, graphics);

            }
            if (currentStateOfResumeButton == State.HOVER_ON_RESUME_BUTTON) {
                drawRectangle.draw("RESUME", buttonOnHoverColor, resumeClickBox, graphics);
            }

        } else if (currentStateofSettingsButton == State.SETTINGS_BUTTON_IS_NOT_PRESSED) { 
            graphics.setColor(new Color(0, 0, 0, 0));
            graphics.fill(settingsBlock); // clear settings button
            graphics.fill(returnClickBox); // clear return to main menu button
            graphics.fill(resumeClickBox); // clear resume button
            areClickBoxesVisible = false;;
        }

        currentFrameAt = System.currentTimeMillis();
        timeDelta = currentFrameAt - lastFrameAt;
        game.advance(timeDelta, stopTheGame);
        lastFrameAt = currentFrameAt;

    }

    public Screen getNextScreen() {
        if (game.won()) {
            return new WinScreen(game);
        } else if (game.lost() && !gamePresenter.isDying()) {
            return new LostScreen(game);
        } else if (returnToIntroScreen)
            return new IntroScreen(game);
        return this;
    }

    public void keyPressed(KeyEvent e) { 
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
                if (currentStateofSettingsButton == State.SETTINGS_BUTTON_IS_NOT_PRESSED) {
                    currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_PRESSED;
                    stopTheGame = true;
                } else {
                    currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_NOT_PRESSED;
                    stopTheGame = false;
                }
                break;
        }
    }

    private enum State {
        HOVER_ON_RESUME_BUTTON,
        RELEASED_RESUME_BUTTON,
        HOVER_ON_RETURN_BUTTON, 
        RELEASED_RETURN_BUTTON, 
        SETTINGS_BUTTON_IS_PRESSED, 
        SETTINGS_BUTTON_IS_NOT_PRESSED
    }

    public void setReturnToIntroScreen(boolean returnToIntroScreen) {
        this.returnToIntroScreen = returnToIntroScreen;
    }
    
	@Override
	public void eventDispatcher(MouseEvent event) {
		if (event instanceof MouseEvent) {
            MouseEvent e = (MouseEvent) event;
            
            if (invisibleImageClickBox.contains(e.getPoint()) && e.getID() == MouseEvent.MOUSE_CLICKED) {
                if (currentStateofSettingsButton == State.SETTINGS_BUTTON_IS_NOT_PRESSED) {
                    currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_PRESSED;
                    stopTheGame = true;
                } else { 
                    currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_NOT_PRESSED;
                    stopTheGame = false;
                }
            }
            if (areClickBoxesVisible && returnClickBox.contains(e.getPoint())) {
                currentStateOfReturnButton = State.HOVER_ON_RETURN_BUTTON;
                if(e.getID() == MouseEvent.MOUSE_CLICKED){
                    currentStateOfReturnButton = State.HOVER_ON_RETURN_BUTTON;
                    setReturnToIntroScreen(true);
                }
            }
            else if (areClickBoxesVisible && resumeClickBox.contains(e.getPoint())) {
                currentStateOfResumeButton = State.HOVER_ON_RESUME_BUTTON;
                if(e.getID() == MouseEvent.MOUSE_CLICKED){
                    currentStateOfResumeButton = State.HOVER_ON_RESUME_BUTTON;
                    currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_NOT_PRESSED;
                    stopTheGame = false;
                }
            } 
            else {
                currentStateOfReturnButton = State.RELEASED_RETURN_BUTTON;
                currentStateOfResumeButton = State.RELEASED_RESUME_BUTTON;
            }
        }
	}
}