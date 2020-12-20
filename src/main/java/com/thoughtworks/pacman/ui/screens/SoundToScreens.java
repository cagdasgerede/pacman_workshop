package com.thoughtworks.pacman.ui.screens;

import java.util.concurrent.locks.ReentrantLock;
import com.thoughtworks.pacman.ui.SoundLoader;

public class SoundToScreens {
    private String screenName;
    private ReentrantLock lock = new ReentrantLock();
    private SoundLoader soundLoader;
    private Thread threadSounds ;

    public SoundToScreens() {
       this("IntroSoundLoader");
    }

    public SoundToScreens(String screenName) {
       this.screenName = screenName;
       soundLoader = new SoundLoader(screenName);
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
        } catch (Exception e) {}
    }

    public void stop() {
        soundLoader.setStop(); 
    }

}