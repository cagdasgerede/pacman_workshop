package com.thoughtworks.pacman.core.achievements;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class OverallTimeAchievementTest {
    OverallTimeAchievement testObj = new OverallTimeAchievement();

    public void init(boolean first, boolean second, boolean third) {
        testObj.setLevel1Achieved(first);
        testObj.setLevel2Achieved(second);
        testObj.setLevel3Achieved(third);
    }

    @Test
    public void testLevel1AchievedIfTrue() {
        init(true, true, false);
        assertTrue(testObj.getLevel1Achieved());
    }

    @Test
    public void testLevel1NotAchievedIfFalse() {
        init(false, false, false);
        assertFalse(testObj.getLevel1Achieved());
    }

    @Test
    public void testLevel2AchievedIfTrue() {
        init(true, true, false);
        assertTrue(testObj.getLevel2Achieved());
    }

    @Test
    public void testLevel2NotAchievedIfFalse() {
        init(true, false, false);
        assertFalse(testObj.getLevel2Achieved());
    }

    @Test
    public void testLevel3AchievedIfTrue () {
        init(true, true, true);
        assertTrue(testObj.getLevel3Achieved());
    }

    @Test
    public void testLevel3NotAchievedIfFalse () {
        init(true, true, false);
        assertFalse(testObj.getLevel3Achieved());
    }
}
