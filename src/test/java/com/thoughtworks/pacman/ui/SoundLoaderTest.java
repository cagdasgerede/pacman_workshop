package com.thoughtworks.pacman.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.io.File;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import javax.sound.sampled.Clip;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)

public class SoundLoaderTest{
    private SoundLoader soundLoader;
    private File f = null;
    @Mock
    Clip clip;
    
    @Test  (expected = ThreadDeath.class)
    public void killAfterRunning_ThrowsException() throws Exception {
        soundLoader = new SoundLoader ("IntroSoundLoader",clip);
        soundLoader.run();
        assertTrue(throwException());
    }


    @Test (expected = Exception.class)
    public void playWithNullPath() throws Exception {
        soundLoader = mock(SoundLoader.class);
        soundLoader.playTheSound(f);
        assertTrue(throwException());
    }

    @Test
    public void playIntroWithPath() throws Exception {
        soundLoader = new SoundLoader("IntroSoundLoader",clip);
        f = new File("/home/irem/pacman_workshop/src/main/resources/com/thoughtworks/pacman/ui/pacman_beginning.wav");  
    
        assertTrue(soundLoader.playTheSound(f));
    }

    @Test
    public void playBackgroundWithPath() throws Exception {
        soundLoader = new SoundLoader("BackgroundSoundLoader",clip);

        assertTrue(soundLoader.playTheSound(mock(File.class)));
    }

    @Test
    public void testStop() {
        soundLoader = mock(SoundLoader.class);
        soundLoader.setStop();
        verify(soundLoader).setStop();
    }

    private boolean throwException() throws Exception{
        throw new Exception();
    }
    
}