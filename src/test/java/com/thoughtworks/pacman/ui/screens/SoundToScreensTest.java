package com.thoughtworks.pacman.ui.screens;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import javax.sound.sampled.Clip;
import org.mockito.Mockito;

@RunWith(MockitoJUnitRunner.class)
public class SoundToScreensTest {
    SoundToScreens screens;
    @Mock
    Clip clip;
    
    @Test
    public void startWithNoParameter() {
        screens = new SoundToScreens();
    }

    @Test
    public void checkTheSound_returnsTrue() {
        screens = Mockito.spy(new SoundToScreens());
        Mockito.doNothing().when(screens).stop();
      
        assertTrue(screens.checkTheSound(true));
    }

    @Test
    public void checkTheSound_returnsFalse() {
        screens = new SoundToScreens();

        assertFalse(screens.checkTheSound(false));
    }

    @Test
    public void chooseTheSoundPlay() {
        screens = Mockito.spy(new SoundToScreens());
        Mockito.doNothing().when(screens).play();
        screens.chooseTheSound();
        verify(screens).play();
    }

    @Test
    public void testStop() {
        screens = mock(SoundToScreens.class);
        Mockito.doNothing().when(clip).stop();
        screens.stop();
        verify(screens).stop();
    }

}
