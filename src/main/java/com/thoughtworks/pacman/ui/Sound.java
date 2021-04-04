package com.thoughtworks.pacman.ui;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;


public class Sound{
    Clip win;
    Clip backgroundSound;
    Clip eatDotEffect;
    Clip introSound;
    Clip deathEffect;
    Clip gameOver;
    Clip winScreenSound;
       
    public Sound(){
   
        URL url;
        AudioInputStream audioIn;
        
        try{
            url = this.getClass().getClassLoader().getResource("sounds/mixkit-arcade-game-jump-coin-216.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            eatDotEffect = AudioSystem.getClip();
            eatDotEffect.open(audioIn);
        
            url = this.getClass().getClassLoader().getResource("sounds/mixkit-video-game-bomb-alert-2803.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            introSound = AudioSystem.getClip();
            introSound.open(audioIn);
      
            url = this.getClass().getClassLoader().getResource("sounds/mixkit-8-bit-lose-2031.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            deathEffect = AudioSystem.getClip();
            deathEffect.open(audioIn);


            url = this.getClass().getClassLoader().getResource("sounds/background2.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            backgroundSound = AudioSystem.getClip();
            backgroundSound.open(audioIn);

            url = this.getClass().getClassLoader().getResource("sounds/133283__leszek-szary__game-over.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            gameOver = AudioSystem.getClip();
            gameOver.open(audioIn);

            url = this.getClass().getClassLoader().getResource("sounds/mixkit-video-game-win-2016.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            winScreenSound = AudioSystem.getClip();
            winScreenSound.open(audioIn);


        }catch(Exception e){}
    }
    
    
    public void eatDot(){
        eatDotEffect.setFramePosition(0);
        eatDotEffect.start();
    }

    public void playIntroSound(){
        introSound.setFramePosition(0);
        introSound.start();
        introSound.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void playBackgroundSound(){
        backgroundSound.setFramePosition(0);
        backgroundSound.start();
        backgroundSound.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void playGameOverScreenSound(){
        gameOver.setFramePosition(0);
        gameOver.start();
        gameOver.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void playWinScreenSound(){
        winScreenSound.setFramePosition(0);
        winScreenSound.start();
        winScreenSound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void deathEffect(){
        deathEffect.setFramePosition(0);
        deathEffect.start();
    }

    public void stopIntroSound(){
        introSound.stop();
       
    }
    public void stopBackgroundSound(){
        backgroundSound.stop();
       
    }
    public void stopDeathSound(){
        deathEffect.stop();
       
    }
    public void gameOverSoundStop(){
        gameOver.stop();
    }
    public void stopWinScreenSound(){
        winScreenSound.stop();
    }
}