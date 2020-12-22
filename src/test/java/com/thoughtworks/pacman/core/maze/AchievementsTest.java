package com.thoughtworks.pacman.core.maze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.screens.LostScreen;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.Test;
public class AchievementsTest {
    @Mock
    private Maze maze;
    
    @Before
    public void setUp() throws Exception {
        this.maze = MazeBuilder.buildDefaultMaze();
    }

    @Test
    public void finishTimeAchievementsList_ShouldNotBe_Empty() throws Exception {

        assertNotNull(maze.getAchievement().finishTimeAchievementsList);    
          
    }
    
    @Test
    public void totalTimeAchievementsList_ShouldNotBe_Empty() throws Exception {

        assertNotNull(maze.getAchievement().totalTimeAchievementsList);      
    }

    @Test
    public void collectingAchievementsList_ShouldNotBe_Empty() throws Exception {

        assertNotNull(maze.getAchievement().collectingAchievementsList);      
    }

    public void turnsAchievementsList_ShouldNotBe_Empty() throws Exception {

        assertNotNull(maze.getAchievement().turnsAchievementsList);      
    }

    @Test
    public void incrementTurnsTook_Method_ShouldIncrement_Correctly() throws Exception {      
        for(int i = 0;i<5;i++){
            maze.getAchievement().incrementTurnsTook();
        }
        assertEquals(maze.getAchievement().turnsTook, 5);
    }

    @Test
    public void nextScreen_shouldReturnIntroScreen_whenKeyPressed() throws Exception {
        Game game = new Game();
        LostScreen lostScreen = new LostScreen(game);
        lostScreen.keyPressed(null);
        assertTrue(lostScreen.getNextScreen().getClass().equals(lostScreen.getClass())); 
    }
}
