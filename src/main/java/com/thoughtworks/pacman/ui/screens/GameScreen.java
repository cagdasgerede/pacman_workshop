package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.Settings;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


public class GameScreen implements Screen {
    private final Game game;
    private final GamePresenter gamePresenter;
    private int[] keyEventIndex = new int[4];
    private int pacmanSpeed;
    private int ghostSpeed;
    private Settings settings = new Settings();


    public GameScreen() throws Exception {
        this(new Game());
    }

    private GameScreen(Game game) {
        this(game, new GamePresenter(game));
    }

    GameScreen(Game game, GamePresenter gamePresenter) {
        this.game = game;
        this.gamePresenter = gamePresenter;
        keyEventIndex=settings.getKeyEventNumb();
        pacmanSpeed=settings.getPacmanSpeed();
        ghostSpeed=settings.getGhostSpeed();
    }

    public void draw(Graphics2D graphics) {
        game.advance(pacmanSpeed,ghostSpeed);
        gamePresenter.draw(graphics);
    }

    public Screen getNextScreen() {
        if (game.won()) {
            return new WinScreen(game);
        } else if (game.lost() && !gamePresenter.isDying()) {
            return new LostScreen(game);
        }
        return this;
    }
    
    public void keyPressed(KeyEvent e){
        System.out.println(e.getKeyCode()+" "+keyEventIndex[2]);
        if(e.getKeyCode()== keyEventIndex[0])
            game.getPacman().setNextDirection(Direction.UP);
        else if(e.getKeyCode()== keyEventIndex[1])
            game.getPacman().setNextDirection(Direction.DOWN);
        else if(e.getKeyCode()== keyEventIndex[2])   
            game.getPacman().setNextDirection(Direction.RIGHT);
        else if(e.getKeyCode()== keyEventIndex[3])
            game.getPacman().setNextDirection(Direction.LEFT);
    }
}
