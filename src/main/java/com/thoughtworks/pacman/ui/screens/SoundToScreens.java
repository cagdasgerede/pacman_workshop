package com.thoughtworks.pacman.ui.screens;

import java.util.concurrent.locks.ReentrantLock;
import com.thoughtworks.pacman.ui.SoundLoader;
import org.apache.log4j.Logger;
import javax.sound.sampled.Clip;

public class SoundToScreens {
    private String screenName;
    private ReentrantLock lock = new ReentrantLock();
    private SoundLoader soundLoader;
    private Thread threadSounds ;
    private Clip clip ;
    private final Logger LOGGER = Logger.getLogger(SoundToScreens.class);

    public SoundToScreens() {
       this("IntroSoundLoader");
    }

    public SoundToScreens(String screenName) {
       this.screenName = screenName;
       soundLoader = new SoundLoader(screenName,clip);
       threadSounds = new Thread(soundLoader, screenName);
    }

    public boolean checkTheSound(boolean check) {
        if(check){
            stop();
            return true;
        } else 
            return false;
    }

    public void chooseTheSound(){
        play();
    }

    public void play() {
        try {
            lock.lock();
            threadSounds.start();
            lock.unlock();
        } catch (Exception e) {
            LOGGER.error("exception", e);
        }
    }

    public void stop() {
        soundLoader.setStop(); 
    }

}