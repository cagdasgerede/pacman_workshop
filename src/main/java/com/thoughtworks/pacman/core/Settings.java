package com.thoughtworks.pacman.core;

import java.awt.Dimension;
import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.Pacman;

public class Settings {
    private final Game game;
    private final Pacman pacman;
    private final Ghost[] ghosts;
    private final int cornerSize = 20;
    private final int width = 15;
    private final int height = 25;

    public Settings() throws Exception{
        this.game = new Game();
        this.pacman=game.getPacman();
        this.ghosts=game.getGhosts();
    }
    public Dimension getDimension() {
        return new Dimension(width * cornerSize, height * cornerSize);
    }
}
