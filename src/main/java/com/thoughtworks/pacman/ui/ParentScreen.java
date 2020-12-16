package com.thoughtworks.pacman.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.screens.GameScreen;
import com.thoughtworks.pacman.ui.screens.IntroScreen;
import com.thoughtworks.pacman.ui.screens.LostScreen;
import com.thoughtworks.pacman.ui.screens.WinScreen;

public class ParentScreen implements Screen {

    // Win Screen and Lost Screen States
    private State currentStateOfReturnButtonInWinAndLostScreen = State.RELEASED_RETURN_BUTTON_WIN_LOST_SCREEN;
    private State currentStateOfQuitButtonInWinAndLostScreen = State.RELEASED_QUIT_BUTTON_WIN_LOST_SCREEN;
    // Intro Screen States
    private State currentStateofPlayButton = State.RELEASED_PLAY_BUTTON;
    private State currentStateOfSettingsButton = State.RELEASED_SETTINGS_BUTTON;
    private State currentStateOfQuitButtonInIntroScreen = State.RELEASED_QUIT_BUTTON_INTRO_SCREEN;
    // Game Screen States
    private State currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_NOT_PRESSED;
    private State currentStateOfResumeButton = State.RELEASED_RESUME_BUTTON;
    private State currentStateOfReturnButtonInGameScreen = State.RELEASED_RETURN_BUTTON_IN_GAME_SCREEN;

    private final Game game;

    private Color buttonMainColor = Color.YELLOW; // main color theme: Yellow
    private Color buttonOnHoverColor = new Color(156, 156, 2); // when on hover color theme:Darker Yellow

    // Win Screen and Lost Screen Buttons
    private Rectangle returnClickBoxWinAndLostScreen = new Rectangle(130, 450, 200, 30);
    private Rectangle exitClickBoxWinAndLostScreen = new Rectangle(180, 490, 100, 30);
    // Intro Screen Buttons
    private Rectangle playClickBox = new Rectangle(180, 310, 100, 30);
    private Rectangle settingsClickBox = new Rectangle(180, 350, 100, 30);
    private Rectangle exitClickBoxIntroScreen = new Rectangle(180, 390, 100, 30);
    // Game Screen Buttons
    private Rectangle invisibleImageClickBox = new Rectangle(5, 5, 40, 40);
    private Rectangle settingsBlock = new Rectangle(70, 100, 300, 350);
    private Rectangle returnClickBoxGameScreen = new Rectangle(120, 300, 200, 30);
    private Rectangle resumeClickBox = new Rectangle(170, 340, 100, 30);

    private RectangleButton drawRectangle = new RectangleButton();

    private boolean startGame = false; // to control to start a new game in intro screen
    private boolean returnToMainMenu = false; // to control to whether return to main menu in win screen and lost screen
    private boolean areClickBoxesVisible = false; // to control the visibility of our blocks
    private boolean returnToIntroScreen = false; // to control when to return to the main screen
    private boolean stopTheGame = false; // to pause the game while the pause menu is open

    private long lastFrameAt;

    public ParentScreen(Game game) {
        this.game = game;
        this.lastFrameAt = System.currentTimeMillis();
    }

    @Override
    public void draw(Graphics2D graphics) {
        if (this.getClass().isInstance(new IntroScreen(game))) {
            if (currentStateofPlayButton == State.RELEASED_PLAY_BUTTON) {
                drawRectangle.draw("PLAY", buttonMainColor, playClickBox, graphics);
            }
            if (currentStateofPlayButton == State.HOVER_ON_PLAY_BUTTON) {
                drawRectangle.draw("PLAY", buttonOnHoverColor, playClickBox, graphics);
            }
            if (currentStateOfSettingsButton == State.RELEASED_SETTINGS_BUTTON) {
                drawRectangle.draw("SETTINGS", buttonMainColor, settingsClickBox, graphics);
            }
            if (currentStateOfSettingsButton == State.HOVER_ON_SETTINGS_BUTTON) {
                drawRectangle.draw("SETTINGS", buttonOnHoverColor, settingsClickBox, graphics);
            }
            if (currentStateOfQuitButtonInIntroScreen == State.RELEASED_QUIT_BUTTON_INTRO_SCREEN) {
                drawRectangle.draw("QUIT", buttonMainColor, exitClickBoxIntroScreen, graphics);
            }
            if (currentStateOfQuitButtonInIntroScreen == State.HOVER_ON_QUIT_BUTTON_INTRO_SCREEN) {
                drawRectangle.draw("QUIT", buttonOnHoverColor, exitClickBoxIntroScreen, graphics);
            }
        } else if (this.getClass().isInstance(new WinScreen(game))
                || this.getClass().isInstance(new LostScreen(game))) {
            if (currentStateOfReturnButtonInWinAndLostScreen == State.RELEASED_RETURN_BUTTON_WIN_LOST_SCREEN) {
                drawRectangle.draw("RETURN TO MAIN MENU", buttonMainColor, returnClickBoxWinAndLostScreen, graphics);
            }
            if (currentStateOfReturnButtonInWinAndLostScreen == State.HOVER_ON_RETURN_BUTTON_WIN_LOST_SCREEN) {
                drawRectangle.draw("RETURN TO MAIN MENU", buttonOnHoverColor, returnClickBoxWinAndLostScreen, graphics);
            }
            if (currentStateOfQuitButtonInWinAndLostScreen == State.RELEASED_QUIT_BUTTON_WIN_LOST_SCREEN) {
                drawRectangle.draw("QUIT", buttonMainColor, exitClickBoxWinAndLostScreen, graphics);
            }
            if (currentStateOfQuitButtonInWinAndLostScreen == State.HOVER_ON_QUIT_BUTTON_WIN_LOST_SCREEN) {
                drawRectangle.draw("QUIT", buttonOnHoverColor, exitClickBoxWinAndLostScreen, graphics);
            }
        } else {
            try {
                if (this.getClass().isInstance(new GameScreen())) {
                    graphics.setColor(new Color(255, 255, 255, 0));
                    graphics.fill(invisibleImageClickBox);
                    if (currentStateofSettingsButton == State.SETTINGS_BUTTON_IS_PRESSED) {
                        areClickBoxesVisible = true;
                        drawRectangle.draw("" + game.getScore(), new Color(0, 0, 0, 230), settingsBlock, graphics);
                        if (currentStateOfReturnButtonInGameScreen == State.RELEASED_RETURN_BUTTON_IN_GAME_SCREEN) {
                            drawRectangle.draw("RETURN TO MAIN MENU", buttonMainColor, returnClickBoxGameScreen,
                                    graphics);
                        }
                        if (currentStateOfReturnButtonInGameScreen == State.HOVER_ON_RETURN_BUTTON_IN_GAME_SCREEN) {
                            drawRectangle.draw("RETURN TO MAIN MENU", buttonOnHoverColor, returnClickBoxGameScreen,
                                    graphics);
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
                        graphics.fill(returnClickBoxGameScreen); // clear return to main menu button
                        graphics.fill(resumeClickBox); // clear resume button
                        areClickBoxesVisible = false;
                        ;
                    }

                    long currentFrameAt = System.currentTimeMillis();
                    long timeDelta = currentFrameAt - lastFrameAt;
                    game.advance(timeDelta, stopTheGame);
                    lastFrameAt = currentFrameAt;

                }
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    @Override
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

    @Override
    public Screen getNextScreen() throws Exception {
        if (returnToMainMenu) {
            return new IntroScreen(game);
        } else if (startGame) {
            return new GameScreen();
        }
        return this;
    }

    public void setReturnToMainMenu(boolean bool) {
        this.returnToMainMenu = bool;
    }

    public void setStartGame(boolean bool) {
        this.startGame = bool;
    }

    public boolean getStopTheGame() {
        return stopTheGame;
    }

    public void setReturnToIntroScreen(boolean returnToIntroScreen) {
        this.returnToIntroScreen = returnToIntroScreen;
    }

    public boolean getreturnToIntroScreen() {
        return returnToIntroScreen;
    }

    private enum State {
        //Win Screen and Lost Screen states
        HOVER_ON_RETURN_BUTTON_WIN_LOST_SCREEN, 
        RELEASED_RETURN_BUTTON_WIN_LOST_SCREEN,
        HOVER_ON_QUIT_BUTTON_WIN_LOST_SCREEN,
        RELEASED_QUIT_BUTTON_WIN_LOST_SCREEN,
        //Intro Screen states
        HOVER_ON_PLAY_BUTTON,
        RELEASED_PLAY_BUTTON,
        HOVER_ON_SETTINGS_BUTTON,
        RELEASED_SETTINGS_BUTTON,
        HOVER_ON_QUIT_BUTTON_INTRO_SCREEN,
        RELEASED_QUIT_BUTTON_INTRO_SCREEN,
        //Game Screen states
        HOVER_ON_RESUME_BUTTON,
        RELEASED_RESUME_BUTTON,
        HOVER_ON_RETURN_BUTTON_IN_GAME_SCREEN,
        RELEASED_RETURN_BUTTON_IN_GAME_SCREEN,
        SETTINGS_BUTTON_IS_PRESSED,
        SETTINGS_BUTTON_IS_NOT_PRESSED
    }

    @Override
    public void eventDispatcher(MouseEvent event) {
        if (event instanceof MouseEvent) {
            MouseEvent e = (MouseEvent) event;

            try {
                if (this.getClass().isInstance(new GameScreen())) {
                    if (invisibleImageClickBox.contains(e.getPoint()) && e.getID() == MouseEvent.MOUSE_CLICKED) {
                        if (currentStateofSettingsButton == State.SETTINGS_BUTTON_IS_NOT_PRESSED) {
                            currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_PRESSED;
                            stopTheGame = true;
                        } else {
                            currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_NOT_PRESSED;
                            stopTheGame = false;
                        }
                    }
                    if (areClickBoxesVisible && returnClickBoxGameScreen.contains(e.getPoint())) {
                        currentStateOfReturnButtonInGameScreen = State.HOVER_ON_RETURN_BUTTON_IN_GAME_SCREEN;
                        if (e.getID() == MouseEvent.MOUSE_CLICKED) {
                            currentStateOfReturnButtonInGameScreen = State.HOVER_ON_RETURN_BUTTON_IN_GAME_SCREEN;
                            setReturnToIntroScreen(true);
                        }
                    } 
                    else if (areClickBoxesVisible && resumeClickBox.contains(e.getPoint())) {
                        currentStateOfResumeButton = State.HOVER_ON_RESUME_BUTTON;
                        if (e.getID() == MouseEvent.MOUSE_CLICKED) {
                            currentStateOfResumeButton = State.HOVER_ON_RESUME_BUTTON;
                            currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_NOT_PRESSED;
                            stopTheGame = false;
                        }
                    } 
                    else {
                        currentStateOfReturnButtonInGameScreen = State.RELEASED_RETURN_BUTTON_IN_GAME_SCREEN;
                        currentStateOfResumeButton = State.RELEASED_RESUME_BUTTON;
                    }
                }
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }

            if (returnClickBoxWinAndLostScreen.contains(e.getPoint())) {
                currentStateOfReturnButtonInWinAndLostScreen = State.HOVER_ON_RETURN_BUTTON_WIN_LOST_SCREEN;
                if(e.getID() == MouseEvent.MOUSE_CLICKED)
                    returnToMainMenu = true;
            }
            else if (exitClickBoxWinAndLostScreen.contains(e.getPoint())) {
                currentStateOfQuitButtonInWinAndLostScreen = State.HOVER_ON_QUIT_BUTTON_WIN_LOST_SCREEN;
                if(e.getID() == MouseEvent.MOUSE_CLICKED)
                    System.exit(0);
            }
            else if(playClickBox.contains(e.getPoint())){
                currentStateofPlayButton = State.HOVER_ON_PLAY_BUTTON;
                if(e.getID() == MouseEvent.MOUSE_CLICKED)
                    startGame = true;
            }
            else if(settingsClickBox.contains(e.getPoint())){
                currentStateOfSettingsButton = State.HOVER_ON_SETTINGS_BUTTON;
            }
            else if(exitClickBoxIntroScreen.contains(e.getPoint())){
                currentStateOfQuitButtonInIntroScreen = State.HOVER_ON_QUIT_BUTTON_INTRO_SCREEN;
                if(e.getID() == MouseEvent.MOUSE_CLICKED)
                    System.exit(0);
            } 
            else {
                currentStateOfReturnButtonInWinAndLostScreen = State.RELEASED_RETURN_BUTTON_WIN_LOST_SCREEN;
                currentStateOfQuitButtonInWinAndLostScreen = State.RELEASED_QUIT_BUTTON_WIN_LOST_SCREEN;
                currentStateOfSettingsButton = State.RELEASED_SETTINGS_BUTTON;
                currentStateofPlayButton = State.RELEASED_PLAY_BUTTON;
                currentStateOfQuitButtonInIntroScreen = State.RELEASED_QUIT_BUTTON_INTRO_SCREEN;
            }
        }
    }
}
