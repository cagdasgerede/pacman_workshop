package com.thoughtworks.pacman.core.maze;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.mockito.Mock;

import com.thoughtworks.pacman.core.Ghosts;
import com.thoughtworks.pacman.core.actors.Pacman;

import org.junit.Before;
import org.junit.Test;
public class AchievementsTest {
    @Mock
    private Ghosts ghosts;
    private Pacman pacman;
    private Maze maze;
    

    @Before
    public void setUp() throws Exception {
        this.maze = MazeBuilder.buildDefaultMaze();
    }



    
    @Test
    public void ifWon_itemsCollected_shouldBe245() throws Exception {      
        
        Maze maze = MazeBuilder.buildMaze("+ +");
        boolean b = maze.hasDotsLeft();
                
        assertThat(maze.getAchievement().itemsCollected==245, is(true));
    }



    @Test
    public void ifTook150Turns_isTookShouldBeTrue()throws Exception{
        Maze maze = MazeBuilder.buildMaze("+ +");
        for(int i = 0;i<200;i++){
            maze.getAchievement().incrementTurnsTook();
        }
        maze.getAchievement().checkTurnsTook();
        assertThat(maze.getAchievement().isTook, is(true));
    }


    @Test
    public void ifReseted_variablesInAchievements_ShouldBeReseted() throws Exception {      
        
        Maze maze = MazeBuilder.buildMaze("+ +");
        boolean before = maze.getAchievement().isReseted;
        maze.getAchievement().resetAchievements();
        boolean after = maze.getAchievement().isReseted;
        assertThat(before != after , is(true));
    }


}
