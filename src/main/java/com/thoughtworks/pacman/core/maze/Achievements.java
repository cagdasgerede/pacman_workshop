package com.thoughtworks.pacman.core.maze;


import java.io.IOException;
import java.io.FileWriter;

public class Achievements {
    public static int timeFinished;
    public static int timePlayed;
    public static int itemsCollected;
    public static int turnsTook;

    public Achievements() {
        timeFinished = 0;
        timePlayed = 0;
        itemsCollected = 0;
        turnsTook = 0;

    }

    void initializeAchievements() {
        boolean isFinished = (timeFinished <=120);
        boolean isPlayed = (timePlayed >=180);
        boolean isCollected = (itemsCollected >=200);
        boolean isTook = (turnsTook >=150);
        String finishDone,playDone,collectDone,tookDone;
        if(isFinished)
            finishDone = "ACHIEVEMENT UNLOCKED !";
         else
            finishDone = "NOT ACHIEVED";

        if(isPlayed)
            playDone = "ACHIEVEMENT UNLOCKED !";
         else
            playDone = "NOT ACHIEVED";

        if(isCollected)
         collectDone = "ACHIEVEMENT UNLOCKED !";
         else
          collectDone = "NOT ACHIEVED";

        if(isTook)
            tookDone = "ACHIEVEMENT UNLOCKED !";
         else
             tookDone = "NOT ACHIEVED";
        

        try {
            FileWriter myWriter = new FileWriter("achievements.txt");
            myWriter.write("Level finished in 2 minutes: " + finishDone);
            myWriter.write("\r\n");
            myWriter.write("Total Time Played 3 minutes: " + playDone);
            myWriter.write("\r\n");

            myWriter.write("200 items collected: " + collectDone);
            myWriter.write("\r\n");

            myWriter.write("Pacman took 150 turns: " + tookDone);
            

            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("timeFinished :" + timeFinished);
        System.out.println("timePlayed :" + timePlayed);
        System.out.println("itemsCollected :" + itemsCollected);
        System.out.println("turnsTook :" + turnsTook);


    }

    void setTimeFinished(int time) {
        timeFinished = time;
        
    }
    void setTimeplayed(int time){
        timePlayed = time;
    }
    void setItemsCollected(int items){
        itemsCollected = items;
    }
    void setTurnsTook(int turns){
        turnsTook = turns;
    }
    void incrementTurnsTook(){
        turnsTook++;
    }


}
