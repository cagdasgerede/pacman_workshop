package com.thoughtworks.pacman.ui;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundLoader implements Runnable {
    private Clip clip ;
    private String soundName;
    private File pacmanFile;
    private String pacmanPath;
    private File musicPath ;

    public SoundLoader(String soundName){
          this.soundName = soundName;
          pacmanFile = new File("pacman_workshop");
          pacmanPath = (pacmanFile.getAbsolutePath().substring(0, pacmanFile.getAbsolutePath().indexOf("pacman_workshop")))+ "pacman_workshop/";// to add to relative path
          
          if (soundName.equals("IntroSoundLoader")) 
              musicPath = new File(pacmanPath+"src/main/resources/com/thoughtworks/pacman/ui/pacman_beginning.wav");                  
          if (soundName.equals("BackgroundSoundLoader"))
              musicPath = new File(pacmanPath+"src/main/resources/com/thoughtworks/pacman/ui/Pac-man-theme-remix-By-Arsenic1987.wav");   
          if (soundName.equals("WinnerSoundLoader"))
              musicPath = new File(pacmanPath+"src/main/resources/com/thoughtworks/pacman/ui/The Final Countdown .wav");
          if (soundName.equals("FinalSoundLoader"))
              musicPath = new File(pacmanPath+"src/main/resources/com/thoughtworks/pacman/ui/pacman_death.wav");
                            
    }

    public void run(){
      try {
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
                }else {
                    throw new Exception();
                   }
            } catch (Exception e) {}
            return true; 
    }
}

