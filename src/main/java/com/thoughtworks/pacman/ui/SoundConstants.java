package com.thoughtworks.pacman.ui;

import java.io.File;

public class SoundConstants {
    private String loader ;
    private File pacmanFile;
    private String pacmanPath;

    public SoundConstants(String loaderName) {
       this.loader = loaderName ;
       pacmanFile = new File("pacman_workshop");
       pacmanPath = (pacmanFile.getAbsolutePath().substring(0, pacmanFile.getAbsolutePath().indexOf("pacman_workshop")))+ "pacman_workshop/src/main/resources/com/thoughtworks/pacman/ui/";// to add to relative path
    }

    public File getPath() {
        if (loader.equals("IntroSoundLoader")) 
            return new File(pacmanPath + "pacman_beginning.wav");                  
        if (loader.equals("BackgroundSoundLoader"))
            return new File(pacmanPath + "Pac-man-theme-remix-By-Arsenic1987.wav");   
        if (loader.equals("WinnerSoundLoader"))
            return new File(pacmanPath + "The Final Countdown .wav");
        if (loader.equals("FinalSoundLoader"))
            return new File(pacmanPath + "pacman_death.wav");
        return null;    
    }

}