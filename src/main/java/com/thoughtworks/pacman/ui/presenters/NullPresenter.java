package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Graphics2D;
import java.io.Serializable;

public class NullPresenter implements Presenter , Serializable {
    public void draw(Graphics2D graphics) {
    }
}
