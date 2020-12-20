package com.thoughtworks.pacman.ui.screens;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.thoughtworks.pacman.ui.SoundLoader;

import static org.mockito.Mockito.spy;
import org.mockito.Mockito;


@RunWith(MockitoJUnitRunner.class)
public class SoundToScreensTest {
    SoundToScreens screens;
    @Mock
    Thread threadSounds;
    @Mock
    SoundLoader soundLoader;

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

   /* @Test
    public void play() {
        screens = new SoundToScreens();
        screens.play();
        verify(threadSounds).start();
    }

    @Test
    public void testStop() {
        screens = mock(SoundToScreens.class);
        soundLoader = Mockito.spy(new SoundLoader("something"));
        Mockito.doNothing().when(soundLoader).setStop();
        screens.stop();
        verify(soundLoader).setStop();
    }

*/
}
