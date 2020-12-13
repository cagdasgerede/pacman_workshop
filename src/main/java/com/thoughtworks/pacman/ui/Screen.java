package com.thoughtworks.pacman.ui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Screen {
    void draw(Graphics2D graphics);
    void keyPressed(KeyEvent e);
    Screen getNextScreen() throws Exception;
    void eventDispatcher(MouseEvent event);
}
