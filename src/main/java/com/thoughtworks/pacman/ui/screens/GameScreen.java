package com.thoughtworks.pacman.ui.screens;

import java.awt.Graphics2D;
import java.awt.Image;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.ParentScreen;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;

public class GameScreen extends ParentScreen {
    static final Image SETTINGS_IMAGE = ImageLoader.loadImage(Screen.class, "settings.png");
    
    private final Game game;
    private final GamePresenter gamePresenter;
    
    public GameScreen() throws Exception {
        this(new Game());
    }

    private GameScreen(Game game) {
        this(game, new GamePresenter(game));
    }

    GameScreen(Game game, GamePresenter gamePresenter) {
        super(game);
        this.game = game;
        this.gamePresenter = gamePresenter;    

    }

    public void draw(Graphics2D graphics) {
        gamePresenter.draw(graphics);
        graphics.drawImage(SETTINGS_IMAGE, 5, 5, 40, 40, null);
        super.draw(graphics);

    }

    public Screen getNextScreen() {
        if (game.won()) {
            return new WinScreen(game);
        } else if (game.lost() && !gamePresenter.isDying()) {
            return new LostScreen(game);
        } else if (super.getreturnToIntroScreen())
            return new IntroScreen(game);
        return this;
    }

}
