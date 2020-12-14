package com.thoughtworks.pacman.core.tiles;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CloneItemTest {
    @Test
    public void shouldBeMovable() {
        assertTrue(new CloneItem(null, null).isMovable());
    }

    @Test
    public void isEaten_shouldBeFalseByDefault() {
        final CloneItem cloneItem = new CloneItem(null, null);
        assertFalse(cloneItem.isEaten());
    }

    @Test
    public void isEaten_shouldBeTrueAfterEat() {
        final CloneItem cloneItem = new CloneItem(null, null);

        cloneItem.eat();

        assertTrue(cloneItem.isEaten());
    }

    @Test
    public void toString_shouldReturnStar() {
        assertThat(new CloneItem(null, null).toString(), equalTo("*"));
    }
}
