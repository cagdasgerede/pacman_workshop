package com.thoughtworks.pacman.core.maze;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
public class Achievements {
    boolean isFinished,isWon,isPlayed,isCollected,isTook;
     int timeFinished;
     public static int timePlayed;
     int itemsCollected;
     int turnsTook;

    public Achievements() {

        timeFinished = 0;
        timePlayed = 0;
        itemsCollected = 0;
        turnsTook = 0;
        isWon = false;
        if(readFromFile()){

        }

    }

    boolean readFromFile(){
        int lineNbr = 1;
        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"achievements.txt"));
			String line = reader.readLine();
			while (line != null) {
                
                if(line.charAt(line.length()-1) == '!'){
                    if(lineNbr == 1){
                        isFinished = true;
                    }
                    if(lineNbr == 2){
                        isPlayed = true;
                    }
                    if(lineNbr == 3){
                        isCollected = true;
                    }
                    if(lineNbr == 4){
                        isTook = true;
                    }
                }
                

				
                line = reader.readLine();
                lineNbr++;
			}
            reader.close();
            return true;
		} catch (IOException e) {
			return false;
		}


    }

    void initializeAchievements() {
        if(!isFinished)
         isFinished = (timeFinished <=120 && isWon);

         if(!isPlayed)
         isPlayed = (timePlayed >=180);

         if(!isCollected)
         isCollected = (itemsCollected >=200);
         if(!isTook)
         isTook = (turnsTook >=150);
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
        
        System.out.println("Time finished:" + timeFinished);
        System.out.println("Time played:" + timePlayed);
        System.out.println("items collected:" + itemsCollected);
        System.out.println("turns took:" + turnsTook);



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
    void resetAchievements(){
        File myObj = new File("achievements.txt");
        if (myObj.delete()) { 
            try {
                FileWriter myWriter = new FileWriter("achievements.txt");
                myWriter.write("Level finished in 2 minutes: NOT ACHIEVED");
                myWriter.write("\r\n");

                myWriter.write("Total Time Played 3 minutes: NOT ACHIEVED" );
                myWriter.write("\r\n");
    
                myWriter.write("200 items collected: NOT ACHIEVED" );
                myWriter.write("\r\n");
    
                myWriter.write("Pacman took 150 turns: NOT ACHIEVED");
                myWriter.close();

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            System.out.println("Achievements Reseted. ");
          } else {
            System.out.println("No achievements to be resetted.");
          } 
          isFinished = false;
          isPlayed = false;
          isCollected = false;
          isTook = false;

    }
    public boolean isNewlyAchievedFinished(){
        if(!isFinished && timeFinished <=120){
            JOptionPane.showMessageDialog(null, "Congratrulations ! Finishing the game under 2 minutes achievement unlocked");
            return true;
        }
        return false;
    }
    public  boolean isNewlyAchievedPlayed(){
        if(!isPlayed && timePlayed  >=180){
            System.out.println("ACHIVEMENT newly UNLOCKED");
            JOptionPane.showMessageDialog(null, "Congratrulations ! Playing over 3 minutes achievement unlocked");

            return true;
        }
        return false;
    }
    public   boolean isNewlyAchievedCollected(){
        if(!isCollected && itemsCollected >=200){
            JOptionPane.showMessageDialog(null, "Congratrulations ! Collecting 200 items achievement unlocked");

            return true;
        }
        return false;
        

    }
    public boolean isNewlyAchievedTook(){
        if(!isTook && turnsTook >=150) {
            JOptionPane.showMessageDialog(null, "Congratrulations ! Tooking 150 Turns achievement unlocked");
            return true;
        }
        return false;
        

    }
    public void setWon(boolean isWonTheGame){
        isWon = isWonTheGame;
    }

}
