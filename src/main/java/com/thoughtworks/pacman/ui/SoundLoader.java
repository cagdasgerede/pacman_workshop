package com.thoughtworks.pacman.ui;

import java.io.File;
import org.apache.log4j.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundLoader implements Runnable {
    private Clip clip ;
    private String soundName;
    private File musicPath ;
    private SoundConstants soundConstants;
    private final Logger LOGGER = Logger.getLogger(SoundLoader.class);

    public SoundLoader(String soundName, Clip clip){
        this.soundName = soundName;
        this.clip = clip;
        soundConstants = new SoundConstants(soundName); 
    }

    public void run(){
      try {
        musicPath = soundConstants.getPath(); 
        playTheSound(musicPath);
        Thread.currentThread().stop();  
      } catch (Exception e) {
        throw new ThreadDeath();  
      }
    }

    public void setStop(){
        clip.stop();
    } 

    public boolean playTheSound(File path) throws Exception{
        try {
            if (path.exists()){
                  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
                  clip = AudioSystem.getClip();
                  clip.open(audioInputStream);
                  clip.start();
                  clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                    throw new Exception();
            }
        } catch (Exception e) {
                  LOGGER.error("exception", e);
        }
            return true; 
    }

}

