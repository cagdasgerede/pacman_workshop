package com.thoughtworks.pacman.ui;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import com.thoughtworks.pacman.ui.Sound;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
class SoundTest{

    @Test 
    void throwsNullPointerExceptionWhenSoundObjectIsNull() throws Exception{ 
        Sound s=null;
        assertThrows(NullPointerException.class,()->{s.playIntroSound();});
        assertThrows(NullPointerException.class,()->{s.eatDot();});
        assertThrows(NullPointerException.class,()->{s.playBackgroundSound();});
        assertThrows(NullPointerException.class,()->{s.playWinScreenSound();});
        assertThrows(NullPointerException.class,()->{s.deathEffect();});
        assertThrows(NullPointerException.class,()->{s.playGameOverScreenSound();});
    }
    @Test
    void throwsNullPointerExceptionWhenAnotherTypeOfFileisGivenInsteadOfSoundFile() throws Exception{
            //parameter 1 is an image file
        Sound s=new Sound("com/thoughtworks/pacman/ui/gameOver.png","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
   
        assertThrows(NullPointerException.class,()->{s.playIntroSound();});
        assertThrows(NullPointerException.class,()->{ s.eatDot();});
        assertThrows(NullPointerException.class,()->{s.playBackgroundSound();});
        assertThrows(NullPointerException.class,()->{s.playWinScreenSound();});
        assertThrows(NullPointerException.class,()->{s.deathEffect();});
        assertThrows(NullPointerException.class,()->{s.playGameOverScreenSound();});

            //parameter 2 is an image file
        Sound s2=new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","com/thoughtworks/pacman/ui/gameOver.png","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        assertThrows(NullPointerException.class,()->{s2.playIntroSound();});
        assertThrows(NullPointerException.class,()->{s2.playBackgroundSound();});
        assertThrows(NullPointerException.class,()->{s2.playWinScreenSound();});
        assertThrows(NullPointerException.class,()->{s2.deathEffect();});
        assertThrows(NullPointerException.class,()->{s2.playGameOverScreenSound();});

            //parameter 3 is an image file
        Sound s3=new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","com/thoughtworks/pacman/ui/gameOver.png","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        assertThrows(NullPointerException.class,()->{s3.playBackgroundSound();});
        assertThrows(NullPointerException.class,()->{s3.playWinScreenSound();});
        assertThrows(NullPointerException.class,()->{s3.deathEffect();});
        assertThrows(NullPointerException.class,()->{s3.playGameOverScreenSound();});

            //parameter 5 is an mp3 file
        Sound s4= new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/gameover.mp3","sounds/mixkit-video-game-win-2016.wav");
        assertThrows(NullPointerException.class,()->{s4.playWinScreenSound();});
        assertThrows(NullPointerException.class,()->{s4.playGameOverScreenSound();});
    }

    @Test
    void playWinScreenSoundSuccessfully(){
        Sound s=new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        assertEquals(5,s.playWinScreenSound());
    }

    @Test
    void stopWinScreenSoundSuccessfully(){
        Sound s=new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        assertEquals(11,s.stopWinScreenSound());
    }
    @Test
    void playGameoverScreenSoundSuccessfully(){
        Sound s=new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        assertEquals(4,s.playGameOverScreenSound());
    }
    @Test
    void stopGameOverSoundSuccesfully(){
        Sound s=new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        assertEquals(10,s.gameOverSoundStop());
    }
    @Test
    void playBackgroundSoundSuccessfully(){
        Sound s=new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        assertEquals(3,s.playBackgroundSound());
    }
    @Test
    void stopBackgroundSoundSuccesfully(){
        Sound s=new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        assertEquals(8,s.stopBackgroundSound());
    }
    @Test
    void playIntroSoundSuccessfully(){
        Sound s=new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        assertEquals(2,s.playIntroSound());
    }
    @Test
    void stopIntroSoundSuccessfully(){
        Sound s=new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        assertEquals(7,s.stopIntroSound());
    }

    @Test
    void playDeathEffectSuccesfully(){
        Sound s=new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        assertEquals(6,s.deathEffect());
    }

    @Test
    void playEatDotSoundSuccessfully(){
        Sound s=new Sound("sounds/mixkit-arcade-game-jump-coin-216.wav","sounds/mixkit-video-game-bomb-alert-2803.wav","sounds/mixkit-8-bit-lose-2031.wav","sounds/background2.wav","sounds/133283__leszek-szary__game-over.wav","sounds/mixkit-video-game-win-2016.wav");
        assertEquals(1,s.eatDot());
    }
}

   