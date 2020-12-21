package com.thoughtworks.pacman.ui.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.RectangleButton;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.ParentScreen;
import com.thoughtworks.pacman.ui.Screen;

public class LostScreen extends ParentScreen {
    static final Image LOST_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "gameOver.jpg");

    private final Dimension dimension;
    private final Game game;
    private boolean startGame;

    private Color buttonMainColor = Color.YELLOW; // main color theme: Yellow
    private Color buttonOnHoverColor = new Color(156, 156, 2); // when on hover color theme:Darker Yellow

    private State currentStateOfReturnButton = State.RELEASED_RETURN_BUTTON;
    private State currentStateOfQuitButton = State.RELEASED_QUIT_BUTTON;

    private Rectangle returnClickBox = new Rectangle(130, 350, 200, 30);
    private Rectangle exitClickBox = new Rectangle(180, 390, 100, 30);
    private RectangleButton drawRectangle = new RectangleButton();

    public LostScreen(Game game) {
        this.dimension = game.getDimension();
        this.game = game;
        this.startGame = false;
    }

    public void draw(Graphics2D graphics) {
        int height = LOST_SCREEN_IMAGE.getHeight(null) * dimension.width / LOST_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(LOST_SCREEN_IMAGE, 0, 0, dimension.width, height, null);

        if (currentStateOfReturnButton == State.RELEASED_RETURN_BUTTON) {
            drawRectangle.draw("RETURN TO MAIN MENU", buttonMainColor, returnClickBox, graphics);
        }
        if (currentStateOfReturnButton == State.HOVER_ON_RETURN_BUTTON) {
            drawRectangle.draw("RETURN TO MAIN MENU", buttonOnHoverColor, returnClickBox, graphics);
        }

        if (currentStateOfQuitButton == State.RELEASED_QUIT_BUTTON) {
            drawRectangle.draw("QUIT", buttonMainColor, exitClickBox, graphics);
        }

        if (currentStateOfQuitButton == State.HOVER_ON_QUIT_BUTTON) {
            drawRectangle.draw("QUIT", buttonOnHoverColor, exitClickBox, graphics);
        }
    }

    public Screen getNextScreen() {
        if (startGame) {
            return new IntroScreen(game);
        }
        return this;
    }

    public void setStartGame(boolean bool) {
        this.startGame = bool;
    }

    private enum State {
        HOVER_ON_RETURN_BUTTON, 
        RELEASED_RETURN_BUTTON, 
        HOVER_ON_QUIT_BUTTON, 
        RELEASED_QUIT_BUTTON
    }

    @Override
    public void eventDispatcher(MouseEvent event) {
        if (event instanceof MouseEvent) {
            MouseEvent e = (MouseEvent) event;

            if (returnClickBox.contains(e.getPoint())) {
                currentStateOfReturnButton = State.HOVER_ON_RETURN_BUTTON;
                if(e.getID() == MouseEvent.MOUSE_CLICKED)
                    startGame = true;
            }
            else if (exitClickBox.contains(e.getPoint())) {
                currentStateOfQuitButton = State.HOVER_ON_QUIT_BUTTON;
                if(e.getID() == MouseEvent.MOUSE_CLICKED)
                    System.exit(0);
            } 
            else {
                currentStateOfReturnButton = State.RELEASED_RETURN_BUTTON;
                currentStateOfQuitButton = State.RELEASED_QUIT_BUTTON;
            }
        }
    }
}