package com.thoughtworks.pacman.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.io.File;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)

public class SoundConstantsTest{
    private SoundConstants soundConstants;
    private File pacmanFile = new File("pacman_workshop");
    private String pacmanPath = (pacmanFile.getAbsolutePath().substring(0, pacmanFile.getAbsolutePath().indexOf("pacman_workshop")))+ "pacman_workshop/src/main/resources/com/thoughtworks/pacman/ui/";
    
    @Test  
    public void setUp() throws Exception {
        soundConstants = new SoundConstants("");
    }

    @Test 
    public void getPath_BackgroundSoundLoader(){
        soundConstants = new SoundConstants("BackgroundSoundLoader");
        assertEquals(soundConstants.getPath(),new File(pacmanPath + "Pac-man-theme-remix-By-Arsenic1987.wav"));
    }

    @Test 
    public void getPath_WinnerSoundLoader(){
        soundConstants = new SoundConstants("WinnerSoundLoader");
        assertEquals(soundConstants.getPath(),new File(pacmanPath + "The Final Countdown .wav"));
    }

    @Test 
    public void getPath_FinalSoundLoader(){
        soundConstants = new SoundConstants("FinalSoundLoader");
        assertEquals(soundConstants.getPath(),new File(pacmanPath + "pacman_death.wav"));
    }

    @Test 
    public void getPath_returnsNull(){
        soundConstants = new SoundConstants("something");
        assertEquals(soundConstants.getPath(),null);
    }



}