package com.thoughtworks.pacman.core.maze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.screens.LostScreen;
import org.mockito.Mock;
import org.junit.AfterClass;
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
    public void ifNoAchievementFileExists_CreateItsOwnAchievements() throws Exception {      
        if(!maze.getAchievement().isReadFromFile){
            assertTrue(maze.getAchievement().finishTimeAchievementsList.size()>0);      
            assertTrue(maze.getAchievement().totalTimeAchievementsList.size()>0);      
            assertTrue(maze.getAchievement().turnsAchievementsList.size()>0);      
            assertTrue(maze.getAchievement().collectingAchievementsList.size()>0);      

        }
    }

    @AfterClass
    public static void cleanUp(){
        FileWriter fwOb;
        PrintWriter pwOb;
        try {
            fwOb = new FileWriter("achievements.txt", false);
            pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch (IOException e) {
            FileWriter fileStream;
            PrintWriter out;
                try {
                    fileStream = new FileWriter("AchievementsError.txt", true);
                    out = new PrintWriter(fileStream);
                    out.println("No achievements to reset.");
                    out.close();
                    fileStream.close();
                } 
                catch (IOException e1) {    
                    e1.printStackTrace();
                }
        }
    }
}
