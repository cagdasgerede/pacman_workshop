package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.achievements.*;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;

import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class GameScreen implements Screen {
    private final Game game;
    private final GamePresenter gamePresenter;
    private long lastFrameAt;
    private long gameStart, gameEnd;

    public GameScreen() throws Exception {
        this(new Game());
        gameStart = System.nanoTime();
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
        long currentFrameAt = System.currentTimeMillis();
        long timeDelta = currentFrameAt - lastFrameAt;

        game.advance(timeDelta);
        gamePresenter.draw(graphics);

        lastFrameAt = currentFrameAt;
    }

    public String getTurnAchievement() {
        return new TurnAchievement(game).getTurnAchievementInfo();
    }

    public String getTimeAchievement() {
        return new TimeAchievement(game).getTimeAchievementInfo();
    }

    public String getOverallTimeAchievement() {
        return new OverallTimeAchievement(game).getOverallTimeAchievementInfo();
    }

    public String getItemCollectionAchievement() {
        return new ItemCollectAchievement(game).getItemCollectAchievementInfo();
    }

    public void showAllAchievementRelatedLosss() {
        String message = getTimeAchievement() + "\n";
        message += getOverallTimeAchievement() + "\n";
        message += getItemCollectionAchievement();
        alert(message);
    }

    public void showAllAchievementRelatedWins() {
        String message = getTimeAchievement() + "\n";
        message += getTurnAchievement() + "\n";
        message += getOverallTimeAchievement() + "\n";
        message += getItemCollectionAchievement();
        alert(message);
    }

    protected void alert(String message) {
        JOptionPane.showMessageDialog(null, message, "Achievements", JOptionPane.PLAIN_MESSAGE);
    }

    public Screen getNextScreen() {
        if (game.won()) {
            gameEnd = System.nanoTime();
            game.setGameplayTime(gameEnd - gameStart);
            showAllAchievementRelatedWins();
            return new WinScreen(game);
        } else if (game.lost() && !gamePresenter.isDying()) {
            gameEnd = System.nanoTime();
            game.setGameplayTime(gameEnd - gameStart);
            showAllAchievementRelatedLosss();
            return new LostScreen(game);
        }
        return this;
    }

    public Screen getNextScreenWithoutAlerting() {
        if (game.won()) {
            return new WinScreen(game);
        } else if (game.lost() && !gamePresenter.isDying()) {
            return new LostScreen(game);
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            game.getPacman().setNextDirection(Direction.LEFT);
            game.incrementKeyPress("left");
            break;
        case KeyEvent.VK_RIGHT:
            game.getPacman().setNextDirection(Direction.RIGHT);
            game.incrementKeyPress("right");
            break;
        case KeyEvent.VK_UP:
            game.getPacman().setNextDirection(Direction.UP);
            game.incrementKeyPress("up");
            break;
        case KeyEvent.VK_DOWN:
            game.getPacman().setNextDirection(Direction.DOWN);
            game.incrementKeyPress("down");
            break;
        }
    }
}
