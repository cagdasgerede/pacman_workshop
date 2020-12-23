package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class GameScreen implements Screen {
    private final Game game;
    private final GamePresenter gamePresenter;
    private long lastFrameAt;
    private long timeDeltaPacman;
    private long timeDeltaGhost;
    private ScreenIO screenIO = new ScreenIO();

    private static int dirleft = KeyEvent.VK_LEFT;
    private static int dirright = KeyEvent.VK_RIGHT;
    private static int dirup = KeyEvent.VK_UP;
    private static int dirdown = KeyEvent.VK_DOWN;

    public long getTimeDeltaPacman(){
        return this.timeDeltaPacman;
    }
    public long getTimeDeltaGhost(){
        return this.timeDeltaPacman;
    }
    public int getDirLeft(){
        return this.dirleft;
    }
    public int getDirRight(){
        return this.dirright;
    }
    public int getDirUp(){
        return this.dirup;
    }
    public int getDirDown(){
        return this.dirdown;
    }


    public void setTimeDeltaPacman( long timeDeltaPacmanSet ){
        this.timeDeltaPacman = timeDeltaPacmanSet;
    }
    public void setTimeDeltaGhost( long timeDeltaGhostSet ){
        this.timeDeltaGhost = timeDeltaGhostSet;
    }
    public void setDirLeft( int dirleftset ){
        this.dirleft = dirleftset ;
    }
    public void setDirRight( int dirrightset ){
        this.dirright = dirrightset;
    }
    public void setDirUp( int dirupset ){
        this.dirup = dirupset;
    }
    public void setDirDown( int dirdownset ){
        this.dirdown = dirdownset;
    }

    public GameScreen() throws Exception {
        this(new Game());

        screenIO.setSettings(this);
        
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
        
        game.advance(timeDeltaPacman, timeDeltaGhost);
        gamePresenter.draw(graphics);

        lastFrameAt = currentFrameAt;
    }

    public Screen getNextScreen() {
        if (game.won()) {
            return new WinScreen(game);
        } else if (game.lost() && !gamePresenter.isDying()) {
            return new LostScreen(game);
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {

        if ( e.getKeyCode() == dirleft){
            game.getPacman().setNextDirection(Direction.LEFT);
        }
        else if ( e.getKeyCode() == dirright){
            game.getPacman().setNextDirection(Direction.RIGHT);
        }
        else if ( e.getKeyCode() == dirup){
            game.getPacman().setNextDirection(Direction.UP);
        }
        else if ( e.getKeyCode() == dirdown){
            game.getPacman().setNextDirection(Direction.DOWN);
        }
    }
}
