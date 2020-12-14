package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.CloneItem;
import org.junit.Test;

import java.awt.geom.Ellipse2D;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CloneItemPresenterTest {
    @Test
    public void shouldCalculateDotBoundsInPixels() throws Exception {
        CloneItemPresenter presenter = new CloneItemPresenter(new CloneItem(new TileCoordinate(2, 3)));
        assertThat(presenter.getBounds(), equalTo(new Ellipse2D.Double(16*(2*2+1)/2-(12/2), 16*(2*3+1)/2-(12/2), 12, 12)));
    }
}
