package com.thoughtworks.pacman.core.tiles;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SpecialCollectableItemTest {
    @Test
    public void eatenIsTrueAfterEating() {
        SpecialCollectableItem specialCollectableItem = new SpecialCollectableItem(null, 1);
        specialCollectableItem.eat();
        assertTrue(specialCollectableItem.isEaten());
    }

    @Test
    public void eatenIsFalseBeforeEating() {
        SpecialCollectableItem specialCollectableItem = new SpecialCollectableItem(null, 3);
        assertFalse(specialCollectableItem.isEaten());
    }

    @Test
    public void shouldBeAbleToMove() {
        SpecialCollectableItem specialCollectableItem = new SpecialCollectableItem(null, 2);
        assertTrue(specialCollectableItem.isMovable());
    }

    @Test
    public void whenTypeIs1ThenToStringIsQ() {
        SpecialCollectableItem specialCollectableItem = new SpecialCollectableItem(null, 1);
        assertThat(specialCollectableItem.toString(), equalTo("q"));
    }

    @Test
    public void whenTypeIs2ThenToStringIsW() {
        SpecialCollectableItem specialCollectableItem = new SpecialCollectableItem(null, 2);
        assertThat(specialCollectableItem.toString(), equalTo("w"));
    }

    @Test
    public void whenTypeIs3ThenToStringIsE() {
        SpecialCollectableItem specialCollectableItem = new SpecialCollectableItem(null, 3);
        assertThat(specialCollectableItem.toString(), equalTo("e"));
    }

    @Test
    public void whenTypeIsUndefinedThenToStringIsQuestionMark() {
        SpecialCollectableItem specialCollectableItem = new SpecialCollectableItem(null, 1234);
        assertThat(specialCollectableItem.toString(), equalTo("?"));
    }
}