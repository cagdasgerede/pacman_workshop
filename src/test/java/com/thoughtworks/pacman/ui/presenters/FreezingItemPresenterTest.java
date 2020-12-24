package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.FreezingItem;
import org.junit.Test;

import java.awt.Rectangle;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FreezingItemPresenterTest {
    @Test
    public void shouldCalculateFreezingItemBoundsInPixels() throws Exception {
        FreezingItemPresenter freezingItemPresenter = new FreezingItemPresenter(new FreezingItem(new TileCoordinate(24, 11)));
        assertThat(freezingItemPresenter.getBounds(), equalTo(new Rectangle(382, 174, 20, 20)));
    }
}
