package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

public class GamePresenter implements Presenter {
    private final MazePresenter mazePresenter;
    private final PacmanPresenter pacmanPresenter;
    private final List<GhostPresenter> ghostPresenters;
    private Game game;
    private ClonePacmanPresenter clonePacmanPresenter;

    public GamePresenter(Game game) {
        this.game = game;
        mazePresenter = new MazePresenter(this.game.getMaze());
        pacmanPresenter = new PacmanPresenter(this.game.getPacman());
        ghostPresenters = new LinkedList<GhostPresenter>();
        for (Ghost ghost : this.game.getGhosts()) {
            ghostPresenters.add(new GhostPresenter(ghost));
        }
    }

    public void draw(Graphics2D graphics) {
        mazePresenter.draw(graphics);
        pacmanPresenter.draw(graphics);

        if (this.game.clonePacmanExists()) {
            if (this.clonePacmanPresenter == null) {
                this.clonePacmanPresenter = new ClonePacmanPresenter(game.getClonePacman());
            }
            this.clonePacmanPresenter.draw(graphics);
        }
        else {
            this.clonePacmanPresenter = null;
        }
        
        mazePresenter.drawSpecialCollectableItem(graphics);

        if (!isDying()) {
            for (GhostPresenter ghostPresenter : ghostPresenters) {
                ghostPresenter.draw(graphics);
            }
        }
    }

    public boolean isDying() {
        return pacmanPresenter.isDying();
    }
}