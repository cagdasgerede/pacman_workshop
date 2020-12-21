package com.thoughtworks.pacman.ui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ParentScreen implements Screen {

    @Override
    public void draw(Graphics2D graphics) { }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public Screen getNextScreen() throws Exception { 
        return this;
    }

    @Override
    public void eventDispatcher(MouseEvent event) { }
}
