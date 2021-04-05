package com.thoughtworks.pacman.ui;

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
       
    public Sound(String r1,String r2, String r3, String r4,String r5,String r6){
   
        URL url;
        AudioInputStream audioIn;
        
        try{
            url = this.getClass().getClassLoader().getResource(r1);
            audioIn = AudioSystem.getAudioInputStream(url);
            eatDotEffect = AudioSystem.getClip();
            eatDotEffect.open(audioIn);
        
            url = this.getClass().getClassLoader().getResource(r2);
            audioIn = AudioSystem.getAudioInputStream(url);
            introSound = AudioSystem.getClip();
            introSound.open(audioIn);
      
            url = this.getClass().getClassLoader().getResource(r3);
            audioIn = AudioSystem.getAudioInputStream(url);
            deathEffect = AudioSystem.getClip();
            deathEffect.open(audioIn);


            url = this.getClass().getClassLoader().getResource(r4);
            audioIn = AudioSystem.getAudioInputStream(url);
            backgroundSound = AudioSystem.getClip();
            backgroundSound.open(audioIn);

            url = this.getClass().getClassLoader().getResource(r5);
            audioIn = AudioSystem.getAudioInputStream(url);
            gameOver = AudioSystem.getClip();
            gameOver.open(audioIn);

            url = this.getClass().getClassLoader().getResource(r6);
            audioIn = AudioSystem.getAudioInputStream(url);
            winScreenSound = AudioSystem.getClip();
            winScreenSound.open(audioIn);


        }catch(Exception e){}
    }
    
    
    public int eatDot(){
        eatDotEffect.setFramePosition(0);
        eatDotEffect.start();
        return 1;
    }

    public int playIntroSound(){
        introSound.setFramePosition(0);
        introSound.start();
        introSound.loop(Clip.LOOP_CONTINUOUSLY);
        return 2;
    }
    public int playBackgroundSound(){
        backgroundSound.setFramePosition(0);
        backgroundSound.start();
        backgroundSound.loop(Clip.LOOP_CONTINUOUSLY);
        return 3;
    }
    public int playGameOverScreenSound(){
        gameOver.setFramePosition(0);
        gameOver.start();
        gameOver.loop(Clip.LOOP_CONTINUOUSLY);
        return 4;
    }
    
    public int playWinScreenSound(){
        winScreenSound.setFramePosition(0);
        winScreenSound.start();
        winScreenSound.loop(Clip.LOOP_CONTINUOUSLY);
        return 5;
    }

    public int deathEffect(){
        deathEffect.setFramePosition(0);
        deathEffect.start();
        return 6;
    }

    public int stopIntroSound(){
        introSound.stop();
        return 7;
    }
    public int stopBackgroundSound(){
        backgroundSound.stop();
        return 8;
    }
    public int stopDeathSound(){
        deathEffect.stop();
        return 9;
    }
    public int gameOverSoundStop(){
        gameOver.stop();
        return 10;
    }
    public int stopWinScreenSound(){
        winScreenSound.stop();
        return 11;
    }
}