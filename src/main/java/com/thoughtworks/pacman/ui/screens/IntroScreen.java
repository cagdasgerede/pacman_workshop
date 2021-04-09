package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

public class IntroScreen implements Screen {
    static final Image TITLE_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "titleScreen.jpg");

    private final Dimension dimension;
    private boolean startGame;
    private boolean loadGame;

    public IntroScreen(Game game) {
        this.dimension = game.getDimension();
        this.startGame = false;
        this.loadGame = false;
    }

    public void draw(Graphics2D graphics) {
        int height = TITLE_SCREEN_IMAGE.getHeight(null) * dimension.width / TITLE_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(TITLE_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
    }

    public Screen getNextScreen() throws Exception {
        if (startGame) {
            return new GameScreen();
        }
        if (loadGame) {
            String location = JOptionPane.showInputDialog("Give an absolute path to load\nAdd the file name to the end : .../save1");
            if(location != null && !location.equals("")) {
                try{
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(location));
                    Game loaded_game = (Game) ois.readObject();
                    ois.close();
                    return new GameScreen(loaded_game);
                }catch(FileNotFoundException e){ 
                    JOptionPane.showMessageDialog(null,"NO saved file called that!","Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
            loadGame = false;
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_L)
            loadGame = true;
        else
            startGame = true;
    }
}
