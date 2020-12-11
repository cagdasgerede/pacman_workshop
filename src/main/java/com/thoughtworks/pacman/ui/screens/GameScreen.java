package com.thoughtworks.pacman.ui.screens;
import java.awt.event.*;
import java.awt.font.TextLayout;
import java.awt.*;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;
import com.thoughtworks.pacman.ui.ImageLoader;

public class GameScreen implements Screen {

    static final Image SETTINGS_IMAGE = ImageLoader.loadImage(Screen.class, "settings.png");

    private Rectangle invisibleImageClickBox; // cannot click on images so this imageclick box is a invisible rectangle that has the same height and width as the image 
    private Rectangle settingsBlock;
    private Rectangle returnClickBox;
    private Rectangle resumeClickBox;

    private Color buttonMainColor; // main color theme: Yellow
    private Color buttonOnHoverColor; // when on hover color theme: Darker Yellow

    private final Game game;

    private final GamePresenter gamePresenter;
    private long lastFrameAt;

    private State currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_NOT_PRESSED;
    private State currentStateOfResumeButton = State.RELEASED_RESUME_BUTTON;
    private State currentStateOfReturnButton = State.RELEASED_RETURN_BUTTON;

    private boolean areClickBoxesVisible = false; // to control the visibility of our blocks
    private boolean returnToIntroScreen = false; // to control when to return to the main screen
    private boolean stopTheGame = false; // to pause the game while the pause menu is open

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

        buttonMainColor = new Color(255, 255, 0);
        buttonOnHoverColor = new Color(156, 156, 2);

        invisibleImageClickBox = new Rectangle(5, 5, 40, 40);
        settingsBlock = new Rectangle(70, 100, 300, 350);
        returnClickBox = new Rectangle(120, 300, 200, 30);
        resumeClickBox = new Rectangle(170, 340, 100, 30);


    }

    public void draw(Graphics2D graphics) {

        gamePresenter.draw(graphics);
        graphics.drawImage(SETTINGS_IMAGE, 5, 5, 40, 40, null);
        graphics.setColor(new Color(255, 255, 255, 0));
        graphics.fill(invisibleImageClickBox);
        long timeDelta;
        long currentFrameAt;

        if (currentStateofSettingsButton == State.SETTINGS_BUTTON_IS_PRESSED) {
            graphics.setColor(new Color(0, 0, 0, 230));
            graphics.fill(settingsBlock);
            areClickBoxesVisible = true;
            stopTheGame = true;
            graphics.setColor(Color.WHITE);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 80));
            int textWidth = (int) graphics.getFontMetrics().getStringBounds("" + game.Score, graphics).getWidth();
            TextLayout tl = new TextLayout("" + game.Score, new java.awt.Font("Yu Gothic UI Semibold", 1, 14),
                    graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString("" + game.Score, settingsBlock.x + settingsBlock.width / 2 - textWidth / 2,
                    settingsBlock.y + settingsBlock.height / 2 + textHeight / 2 - 50);

            if (currentStateOfReturnButton == State.RELEASED_RETURN_BUTTON) {
                graphics.setColor(buttonMainColor);
                graphics.fill(returnClickBox);
                graphics.setColor(Color.BLACK);
                graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
                textWidth = (int) graphics.getFontMetrics().getStringBounds("RETURN TO MAIN MENU", graphics).getWidth();
                tl = new TextLayout("RETURN TO MAIN MENU", new java.awt.Font("Yu Gothic UI Semibold", 1, 14),
                        graphics.getFontRenderContext());
                textHeight = (int) tl.getBounds().getHeight();
                graphics.drawString("RETURN TO MAIN MENU", returnClickBox.x + returnClickBox.width / 2 - textWidth / 2,
                        returnClickBox.y + returnClickBox.height / 2 + textHeight / 2);

            }
            if (currentStateOfReturnButton == State.HOVER_ON_RETURN_BUTTON) {
                graphics.setColor(buttonOnHoverColor);
                graphics.fill(returnClickBox);
                graphics.setColor(Color.BLACK);
                graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
                textWidth = (int) graphics.getFontMetrics().getStringBounds("RETURN TO MAIN MENU", graphics).getWidth();
                tl = new TextLayout("RETURN TO MAIN MENU", new java.awt.Font("Yu Gothic UI Semibold", 1, 14),
                        graphics.getFontRenderContext());
                textHeight = (int) tl.getBounds().getHeight();
                graphics.drawString("RETURN TO MAIN MENU", returnClickBox.x + returnClickBox.width / 2 - textWidth / 2,
                        returnClickBox.y + returnClickBox.height / 2 + textHeight / 2);

            }
            if (currentStateOfResumeButton == State.RELEASED_RESUME_BUTTON) {
                graphics.setColor(buttonMainColor);
                graphics.fill(resumeClickBox);
                graphics.setColor(Color.BLACK);
                graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));

                textWidth = (int) graphics.getFontMetrics().getStringBounds("RESUME", graphics).getWidth();
                tl = new TextLayout("RESUME", new java.awt.Font("Yu Gothic UI Semibold", 1, 14),
                        graphics.getFontRenderContext());
                textHeight = (int) tl.getBounds().getHeight();
                graphics.drawString("RESUME", resumeClickBox.x + resumeClickBox.width / 2 - textWidth / 2,
                        resumeClickBox.y + resumeClickBox.height / 2 + textHeight / 2);
            }
            if (currentStateOfResumeButton == State.HOVER_ON_RESUME_BUTTON) {
                graphics.setColor(buttonOnHoverColor);
                graphics.fill(resumeClickBox);
                graphics.setColor(Color.BLACK);
                graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));

                textWidth = (int) graphics.getFontMetrics().getStringBounds("RESUME", graphics).getWidth();
                tl = new TextLayout("RESUME", new java.awt.Font("Yu Gothic UI Semibold", 1, 14),
                        graphics.getFontRenderContext());
                textHeight = (int) tl.getBounds().getHeight();
                graphics.drawString("RESUME", resumeClickBox.x + resumeClickBox.width / 2 - textWidth / 2,
                        resumeClickBox.y + resumeClickBox.height / 2 + textHeight / 2);
            }

        } else if (currentStateofSettingsButton == State.SETTINGS_BUTTON_IS_NOT_PRESSED) { 
            graphics.setColor(new Color(0, 0, 0, 0));
            graphics.fill(settingsBlock); // clear settings button
            graphics.fill(returnClickBox); // clear return to main menu button
            graphics.fill(resumeClickBox); // clear resume button
            areClickBoxesVisible = false;
            stopTheGame = false;

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
                    game.getPacman().setNextDirection(Direction.NONE);
                    currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_PRESSED;
                } else {
                    currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_NOT_PRESSED;
                }

                break;
        }
    }

    private enum State {
        HOVER_ON_RESUME_BUTTON, RELEASED_RESUME_BUTTON, HOVER_ON_RETURN_BUTTON, RELEASED_RETURN_BUTTON, SETTINGS_BUTTON_IS_PRESSED, SETTINGS_BUTTON_IS_NOT_PRESSED
    }

	@Override
	public void eventDispatcher(MouseEvent event) {
		if (event instanceof MouseEvent) {
            MouseEvent e = (MouseEvent) event;
            
            if (invisibleImageClickBox.contains(e.getPoint()) && e.getID() == MouseEvent.MOUSE_CLICKED) {
                if (currentStateofSettingsButton == State.SETTINGS_BUTTON_IS_NOT_PRESSED) {
                    game.getPacman().setNextDirection(Direction.NONE);
                    currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_PRESSED;

                } else { 
                    currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_NOT_PRESSED;

                }
            }
            if (returnClickBox.contains(e.getPoint())) {
                currentStateOfReturnButton = State.HOVER_ON_RETURN_BUTTON;
            } else {
                currentStateOfReturnButton = State.RELEASED_RETURN_BUTTON;
            }
            if (resumeClickBox.contains(e.getPoint())) {
                currentStateOfResumeButton = State.HOVER_ON_RESUME_BUTTON;
            } else {
                currentStateOfResumeButton = State.RELEASED_RESUME_BUTTON;
            }

            if (areClickBoxesVisible && returnClickBox.contains(e.getPoint()) && e.getID() == MouseEvent.MOUSE_CLICKED) {
                currentStateOfReturnButton = State.HOVER_ON_RETURN_BUTTON;
                returnToIntroScreen = true;

            }

            if (areClickBoxesVisible && resumeClickBox.contains(e.getPoint()) && e.getID() == MouseEvent.MOUSE_CLICKED) {
                currentStateOfResumeButton = State.HOVER_ON_RESUME_BUTTON;
                currentStateofSettingsButton = State.SETTINGS_BUTTON_IS_NOT_PRESSED;

            }

        }
    
		
	}

    
}
