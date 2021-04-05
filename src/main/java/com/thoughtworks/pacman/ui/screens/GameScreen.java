package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.Sound;

import com.thoughtworks.pacman.ui.presenters.GamePresenter;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class GameScreen implements Screen {
    private final Game game;
    private final GamePresenter gamePresenter;
    private long lastFrameAt;
    Sound  s;
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
        s = new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        s.playBackgroundSound();
    }

    public void draw(Graphics2D graphics) {
       
        long currentFrameAt = System.currentTimeMillis();
        long timeDelta = currentFrameAt - lastFrameAt;
        game.advance(timeDelta);
        gamePresenter.draw(graphics);

        lastFrameAt = currentFrameAt;
    }

    public Screen getNextScreen() {
      
        if (game.won()) {
            s.stopBackgroundSound();
            return new WinScreen(game);
        } else if (game.lost() && !gamePresenter.isDying()) {
            s.stopBackgroundSound();
            return new LostScreen(game);
        }
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
        }
    }
}
