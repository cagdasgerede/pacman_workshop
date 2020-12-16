package com.thoughtworks.pacman.ui.screens;

import java.util.concurrent.locks.ReentrantLock;
import com.thoughtworks.pacman.ui.SoundLoader;

public class SoundToScreens{
    private String screenName;
    private ReentrantLock lock = new ReentrantLock();
    private SoundLoader soundLoader;
    private Thread threadSounds ;

    public SoundToScreens(){
       this("IntroSoundLoader");
    }

    public SoundToScreens(String screenName){
       this.screenName = screenName;
       soundLoader = new SoundLoader(screenName);

       if (screenName.equals("IntroSoundLoader"))
          threadSounds = new Thread(soundLoader, "IntroSoundLoader");
       if (screenName.equals("BackgroundSoundLoader"))
          threadSounds = new Thread(soundLoader, "BackgroundSoundLoader"); 
       if (screenName.equals("WinnerSoundLoader"))
          threadSounds = new Thread(soundLoader, "WinnerSoundLoader");
       if (screenName.equals("FinalSoundLoader"))
          threadSounds = new Thread(soundLoader, "FinalSoundLoader");
    }

    public boolean checkTheSound(boolean check){
        if(check){
             stop();
            return true;
        }else 
            return false;
    }

    public void chooseTheSound(){
        if (screenName.equals("IntroSoundLoader")) 
            play();
        if (screenName.equals("BackgroundSoundLoader"))   
            play();
        if (screenName.equals("WinnerSoundLoader"))
            play();
        if (screenName.equals("FinalSoundLoader"))
            play();
    }

    public void play (){
        try {
            lock.lock();
            threadSounds.start();
            lock.unlock();
        } catch (Exception e) {}
    }

    public void stop(){
        soundLoader.setStop();  
    }
}