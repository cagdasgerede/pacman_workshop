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
        assertThat(presenter.getBounds(), equalTo(
            new Ellipse2D.Double(
                /* 
                    The next two arguments represent the 
                    corrdinate of the presenter on canvas. 16 is 
                    the tile coordinates (given in line 15) and 
                    is multiplied with two times plus one (because
                    of maze boundaries).After that, simply subtracting
                    the radius will give us the proper coordinates of 
                    the item on the game canvas.

                    Simply:
                        CANVAS_COORDINATE = TILE_SIZE * ( 2 * TILE_X_OR_Y_COORDINATE + 1 ) / 2 - ( DIAMETER / 2 )

                */
                34, // = 16*(2*2+1)/2-(12/2)  
                50, // = 16*(2*3+1)/2-(12/2)
                12, // radius of the ellipse
                12
            )
        ));
    }
}
