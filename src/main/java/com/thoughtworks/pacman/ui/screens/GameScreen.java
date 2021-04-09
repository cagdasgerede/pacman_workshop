package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class GameScreen implements Screen {
    private final Game game;
    private final GamePresenter gamePresenter;
    private long lastFrameAt;
    private boolean saveGame = false;

    public GameScreen() throws Exception {
        this(new Game());
    }

    GameScreen(Game game) {
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

    public Screen getNextScreen() throws Exception {
        if (game.won()) {
            return new WinScreen(game);
        } 
        else if (game.lost() && !gamePresenter.isDying()) {
            return new LostScreen(game);
        }
        else if(saveGame) {
            String location = JOptionPane.showInputDialog("Give an absolute path to save\nAdd the file name to the end : .../save1");
            if(location != null && !location.equals("")){
                FileOutputStream fos = new FileOutputStream(location);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(game);
                oos.flush();
                oos.close();
                JOptionPane.showMessageDialog(null,"SAVED!","INFO",JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null,"NOT ENTERED A PATH TO SAVE!","Warning",JOptionPane.WARNING_MESSAGE);
            return new IntroScreen(new Game());
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_S:
            saveGame = true;
            break;
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
