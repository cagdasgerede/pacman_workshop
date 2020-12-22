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

    private static int dirleft = KeyEvent.VK_LEFT;
    private static int dirright = KeyEvent.VK_RIGHT;
    private static int dirup = KeyEvent.VK_UP;
    private static int dirdown = KeyEvent.VK_DOWN;

    public GameScreen() throws Exception {
        this(new Game());


        try {
        File file=new File("config.txt");    //creates a new file instance  
        FileReader fr=new FileReader(file);   //reads the file  
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  

        String line=br.readLine();
        switch(line) {
            case "low":
                timeDeltaGhost = 100; 
                break;
            case "medium":
                timeDeltaGhost = 300;
                break;
            case "high":
                timeDeltaGhost = 500;
                break;
        }


        line=br.readLine();

        switch(line) {
            case "low":
                timeDeltaPacman = 100; 
                break;
            case "medium":
                timeDeltaPacman = 300;
                break;
            case "high":
                timeDeltaPacman = 500;
                break;
        }

        line=br.readLine();
        line=br.readLine();

        switch (line) {
        case "UP KEY":
            dirup = KeyEvent.VK_UP;
            break;
        case "RIGHT KEY":
            dirup = KeyEvent.VK_RIGHT;
            break;
        case "LEFT KEY":
            dirup = KeyEvent.VK_LEFT;
            break;
        case "DOWN KEY":
            dirup = KeyEvent.VK_DOWN;
            break;
        case "Num8":
            dirup = KeyEvent.VK_NUMPAD8;
            break;
        case "Num6":
            dirup = KeyEvent.VK_NUMPAD6;
            break;
        case "Num4":
            dirup = KeyEvent.VK_NUMPAD4;
            break;
        case "Num2":
            dirup = KeyEvent.VK_NUMPAD2;
            break;        
        }       

        line=br.readLine();

        switch (line) {
        case "UP KEY":
            dirdown = KeyEvent.VK_UP;
            break;
        case "RIGHT KEY":
            dirdown = KeyEvent.VK_RIGHT;
            break;
        case "LEFT KEY":
            dirdown = KeyEvent.VK_LEFT;
            break;
        case "DOWN KEY":
            dirdown = KeyEvent.VK_DOWN;
            break;
        case "Num8":
            dirdown = KeyEvent.VK_NUMPAD8;
            break;
        case "Num6":
            dirdown = KeyEvent.VK_NUMPAD6;
            break;
        case "Num4":
            dirdown = KeyEvent.VK_NUMPAD4;
            break;
        case "Num2":
            dirdown = KeyEvent.VK_NUMPAD2;
            break;        
        } 

        line=br.readLine();

        switch (line) {
        case "UP KEY":
            dirleft = KeyEvent.VK_UP;
            break;
        case "RIGHT KEY":
            dirleft = KeyEvent.VK_RIGHT;
            break;
        case "LEFT KEY":
            dirleft = KeyEvent.VK_LEFT;
            break;
        case "DOWN KEY":
            dirleft = KeyEvent.VK_DOWN;
            break;
        case "Num8":
            dirleft = KeyEvent.VK_NUMPAD8;
            break;
        case "Num6":
            dirleft = KeyEvent.VK_NUMPAD6;
            break;
        case "Num4":
            dirleft = KeyEvent.VK_NUMPAD4;
            break;
        case "Num2":
            dirleft = KeyEvent.VK_NUMPAD2;
            break;        
        } 
        line=br.readLine();

        switch (line) {
        case "UP KEY":
            dirright = KeyEvent.VK_UP;
            break;
        case "RIGHT KEY":
            dirright = KeyEvent.VK_RIGHT;
            break;
        case "LEFT KEY":
            dirright = KeyEvent.VK_LEFT;
            break;
        case "DOWN KEY":
            dirright = KeyEvent.VK_DOWN;
            break;
        case "Num8":
            dirright = KeyEvent.VK_NUMPAD8;
            break;
        case "Num6":
            dirright = KeyEvent.VK_NUMPAD6;
            break;
        case "Num4":
            dirright = KeyEvent.VK_NUMPAD4;
            break;
        case "Num2":
            dirright = KeyEvent.VK_NUMPAD2;
            break;        
        }      


        }catch (IOException ioe){
            ioe.printStackTrace();  
        }



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


        /*
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            game.getPacman().setNextDirection(Direction.LEFT);
            break;
        case KeyEvent.VK_RIGHT:
            game.getPacman().setNextDirection(Direction.RIGHT);
            break;
        case dirup:
            game.getPacman().setNextDirection(Direction.UP);
            break;
        case dirdown:
            game.getPacman().setNextDirection(Direction.DOWN);
            break;
        }
        */
    }
}
