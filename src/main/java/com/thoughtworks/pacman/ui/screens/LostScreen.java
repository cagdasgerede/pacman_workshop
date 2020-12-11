package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextLayout;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

public class LostScreen implements Screen {
    static final Image LOST_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "gameOver.jpg");

    private final Dimension dimension;
    private final Game game;
    private boolean startGame;

    private Color buttonMainColor; // main color theme: Yellow
    private Color buttonOnHoverColor; // when on hover color theme:Darker Yellow

    private State currentStateOfReturnButton = State.RELEASED_RETURN_BUTTON;
    private State currentStateOfQuitButton = State.RELEASED_QUIT_BUTTON;

    private Rectangle returnClickBox;
    private Rectangle exitClickBox;

    public LostScreen(Game game) {
        this.dimension = game.getDimension();
        buttonMainColor = new Color(255, 255, 0);
        buttonOnHoverColor = new Color(156, 156, 2);
        returnClickBox = new Rectangle(130, 350, 200, 30);
        exitClickBox = new Rectangle(180, 390, 100, 30);
        this.game = game;

        this.startGame = false;
    }

    public void draw(Graphics2D graphics) {
        int height = LOST_SCREEN_IMAGE.getHeight(null) * dimension.width / LOST_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(LOST_SCREEN_IMAGE, 0, 0, dimension.width, height, null);

        if (currentStateOfReturnButton == State.RELEASED_RETURN_BUTTON) {
            graphics.setColor(buttonMainColor);
            graphics.fill(returnClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int) graphics.getFontMetrics().getStringBounds("RETURN TO MAIN MENU", graphics).getWidth();
            TextLayout tl = new TextLayout("RETURN TO MAIN MENU", new java.awt.Font("Yu Gothic UI Semibold", 1, 14),
                    graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("RETURN TO MAIN MENU", returnClickBox.x + returnClickBox.width / 2 - textWidth / 2,
                    returnClickBox.y + returnClickBox.height / 2 + textHeight / 2);

        }
        if (currentStateOfReturnButton == State.HOVER_ON_RETURN_BUTTON) {
            graphics.setColor(buttonOnHoverColor);
            graphics.fill(returnClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int) graphics.getFontMetrics().getStringBounds("RETURN TO MAIN MENU", graphics).getWidth();
            TextLayout tl = new TextLayout("RETURN TO MAIN MENU", new java.awt.Font("Yu Gothic UI Semibold", 1, 14),
                    graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("RETURN TO MAIN MENU", returnClickBox.x + returnClickBox.width / 2 - textWidth / 2,
                    returnClickBox.y + returnClickBox.height / 2 + textHeight / 2);

        }

        if (currentStateOfQuitButton == State.RELEASED_QUIT_BUTTON) {
            graphics.setColor(buttonMainColor);
            graphics.fill(exitClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int) graphics.getFontMetrics().getStringBounds("QUIT", graphics).getWidth();
            TextLayout tl = new TextLayout("QUIT", new java.awt.Font("Yu Gothic UI Semibold", 1, 14),
                    graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("QUIT", exitClickBox.x + exitClickBox.width / 2 - textWidth / 2,
                    exitClickBox.y + exitClickBox.height / 2 + textHeight / 2);
        }

        if (currentStateOfQuitButton == State.HOVER_ON_QUIT_BUTTON) {
            graphics.setColor(buttonOnHoverColor);
            graphics.fill(exitClickBox);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int) graphics.getFontMetrics().getStringBounds("QUIT", graphics).getWidth();
            TextLayout tl = new TextLayout("QUIT", new java.awt.Font("Yu Gothic UI Semibold", 1, 14),
                    graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("QUIT", exitClickBox.x + exitClickBox.width / 2 - textWidth / 2,
                    exitClickBox.y + exitClickBox.height / 2 + textHeight / 2);
        }
    }

    public Screen getNextScreen() {
        if (startGame) {
            return new IntroScreen(game);
        }
        return this;
    }

    public void keyPressed(KeyEvent e) { 
        // startGame = true;
    }

    public void setStartGame(boolean bool) {
        this.startGame = bool;
    }

    private enum State {
        HOVER_ON_RETURN_BUTTON, RELEASED_RETURN_BUTTON, HOVER_ON_QUIT_BUTTON, RELEASED_QUIT_BUTTON
    }

    @Override
    public void eventDispatcher(MouseEvent event) {
        if (event instanceof MouseEvent) {
            MouseEvent e = (MouseEvent) event;

            if (returnClickBox.contains(e.getPoint())) {
                currentStateOfReturnButton = State.HOVER_ON_RETURN_BUTTON;
            } else {
                currentStateOfReturnButton = State.RELEASED_RETURN_BUTTON;
            }
            if (exitClickBox.contains(e.getPoint())) {
                currentStateOfQuitButton = State.HOVER_ON_QUIT_BUTTON;
            } else {
                currentStateOfQuitButton = State.RELEASED_QUIT_BUTTON;
            }
            if (returnClickBox.contains(e.getPoint()) && e.getClickCount() >= 1) {
                startGame = true;
            }
            if (exitClickBox.contains(e.getPoint()) && e.getClickCount() >= 1) {
                System.exit(0);
            }

        }

    }
}
