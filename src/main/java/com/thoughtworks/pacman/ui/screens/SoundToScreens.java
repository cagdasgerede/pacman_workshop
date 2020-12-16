package com.thoughtworks.pacman.ui.screens;

import java.util.concurrent.locks.ReentrantLock;

import com.thoughtworks.pacman.ui.BackgroundSoundLoader;
import com.thoughtworks.pacman.ui.FinalSoundLoader;
import com.thoughtworks.pacman.ui.IntroSoundLoader;
import com.thoughtworks.pacman.ui.WinnerSoundLoader;

public class SoundToScreens{
    private String screenName;
    private  ReentrantLock lock = new ReentrantLock();
    private IntroSoundLoader IntroSoundLoader = new IntroSoundLoader();
    private BackgroundSoundLoader BackgroundSoundLoader = new BackgroundSoundLoader();
    private FinalSoundLoader FinalSoundLoader = new FinalSoundLoader();
    private WinnerSoundLoader WinnerSoundLoader = new WinnerSoundLoader();
    private Thread threadSounds ;

    public SoundToScreens(){
       this("IntroSoundLoader");
    }

    public SoundToScreens(String screenName){
       this.screenName = screenName;
       System.out.println(screenName + " WOW");

    if (screenName.equals("IntroSoundLoader")) {
        threadSounds = new Thread(IntroSoundLoader, "IntroSoundLoader");
        System.out.println("intro");}
    if (screenName.equals("BackgroundSoundLoader")){ 
        threadSounds = new Thread(BackgroundSoundLoader, "BackgroundSoundLoader");
        System.out.println("background");}   
    if (screenName.equals("WinnerSoundLoader")){ 
        threadSounds = new Thread(WinnerSoundLoader, "WinnerSoundLoader");
        System.out.println("winner");}
    if (screenName.equals("FinalSoundLoader")){ 
        threadSounds = new Thread(FinalSoundLoader, "FinalSoundLoader");
        System.out.println("final");} 
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
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean stop(){
        try {
            System.out.println("stopping");
            if (screenName.equals("IntroSoundLoader"))
                IntroSoundLoader.setStop();
            if (screenName.equals("BackgroundSoundLoader")) 
                BackgroundSoundLoader.setStop();
            if (screenName.equals("WinnerSoundLoader")) 
                WinnerSoundLoader.setStop();
            if (screenName.equals("FinalSoundLoader")) 
                FinalSoundLoader.setStop();  
        } catch (Exception e) {
            return false ;
        }
        return true ; 
    }

}