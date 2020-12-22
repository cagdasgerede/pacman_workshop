package com.thoughtworks.pacman.core.maze;

import javax.swing.JOptionPane;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Achievements {
    ArrayList <FinishAchievement> finishTimeAchievementsList;
    ArrayList <TimeAchievement> totalTimeAchievementsList;
    ArrayList <CollectingAchievement> collectingAchievementsList;
    ArrayList <TurnsAchievement> turnsAchievementsList;
    FinishAchievement finish;
    TimeAchievement playTime;
    CollectingAchievement collect;
    TurnsAchievement turns;
    boolean isWon,isReadFromFile,isInitialized;
    int timeFinished;
    public static int timePlayed;
    int itemsCollected;
    int turnsTook;
    boolean isReseted;
    public Achievements() {
        finishTimeAchievementsList = new ArrayList<FinishAchievement>();
        totalTimeAchievementsList = new ArrayList <TimeAchievement>();
        collectingAchievementsList = new ArrayList <CollectingAchievement> ();
        turnsAchievementsList = new ArrayList <TurnsAchievement> ();
        timeFinished = 0;
        timePlayed = 0;
        itemsCollected = 0;
        turnsTook = 0;
        isWon = false;
        isReseted = false;
        isInitialized = false;
        isReadFromFile = readFromFile();
        if(!isReadFromFile){
            finish = new FinishAchievement(180);
            playTime = new TimeAchievement(150);
            collect = new CollectingAchievement(100);
            turns = new TurnsAchievement(50);
            TurnsAchievement turns2 = new TurnsAchievement(20);
            finishTimeAchievementsList.add(finish);
            totalTimeAchievementsList.add(playTime);
            collectingAchievementsList.add(collect);
            turnsAchievementsList.add(turns);
            turnsAchievementsList.add(turns2);
        }
    }

    boolean readFromFile(){
        int lineNumber = 0;
        try{
            ObjectInputStream inputStream;
            inputStream = new ObjectInputStream(new FileInputStream("achievements.txt"));
            Object obj = null;
            while ((obj = inputStream.readObject()) != null) {
                lineNumber ++;
                if (obj instanceof FinishAchievement ) {
                    finishTimeAchievementsList.add((FinishAchievement) obj);
                }
                else if ( obj instanceof TimeAchievement){ 
                    totalTimeAchievementsList.add( (TimeAchievement) obj);
                }
                else if (obj instanceof CollectingAchievement){
                    collectingAchievementsList.add( (CollectingAchievement) obj);
                }
                else if (obj instanceof TurnsAchievement){
                    turnsAchievementsList.add( (TurnsAchievement) obj);
                }
            }
            inputStream.close();
        }
        catch (EOFException e) {
            if(lineNumber > 2)
                return true;
        }
        catch(Exception e){
            return false;
        }
        if(lineNumber > 2)
            return true;
        else
            return false;
    }

    void initializeAchievements() {
        if(!isInitialized){
            try {
                FileOutputStream output = new FileOutputStream(new File("achievements.txt"));
                ObjectOutputStream o = new ObjectOutputStream(output);
    
                for(FinishAchievement f : finishTimeAchievementsList){
                    if (timeFinished < f.getTargetFinishTime() && isWon){
                        System.out.println("New achievement finish time " + f.getClass());
                        f.setIsAchieved(true);
                        if(f.getIsNewlyAchieved() == false){
                            JOptionPane.showMessageDialog(null, f.toString());
                            f.setIsNewlyAchieved(true);
                        }
                    }
                    o.writeObject(f);
                }
    
                for(TimeAchievement t : totalTimeAchievementsList){
                    if (timePlayed > t.getTargetTime()){
                        System.out.println("New achievement total time " + t.toString());
                        t.setIsAchieved(true);
                        if(t.getIsNewlyAchieved() == false){
                            JOptionPane.showMessageDialog(null, t.toString());
                            t.setIsNewlyAchieved(true);
                        }
                    }  
                    o.writeObject(t);
    
                }
    
                for(CollectingAchievement c : collectingAchievementsList){
                    if (itemsCollected > c.getTargetCollection()){
                        System.out.println("New achievement collecting ac " + c.toString());
                        c.setIsAchieved(true);
                        if(c.getIsNewlyAchieved() == false){
                            JOptionPane.showMessageDialog(null, c.toString());
                            c.setIsNewlyAchieved(true);
                        }
                    }
                    o.writeObject(c);
                }
    
                for(TurnsAchievement t : turnsAchievementsList){
                    if (turnsTook > t.getTargetTurns()){
                        System.out.println("New achievement turns tookke " + t.toString());
                        t.setIsAchieved(true);
                        if(t.getIsNewlyAchieved() == false){
                            JOptionPane.showMessageDialog(null, t.toString());
                            t.setIsNewlyAchieved(true);
                        }
                    }
                    o.writeObject(t);
                }
                o.close();
                output.close();
            } 
            catch (IOException e) {
                FileWriter fileStream;
                PrintWriter out;
                try {
                    fileStream = new FileWriter("AchievementsError.txt", true);
                    out = new PrintWriter(fileStream);
                    e.printStackTrace(out);
                    out.close();
                    fileStream.close();
                } 
                catch (IOException e1) {    
                    e1.printStackTrace();
                }
            } 
            isInitialized = true;
        }
    } 
    void resetAchievements(){
        FileWriter fwOb;
        PrintWriter pwOb;
        try {
            fwOb = new FileWriter("achievements.txt", false);
            pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch (IOException e) {
            FileWriter fileStream;
            PrintWriter out;
                try {
                    fileStream = new FileWriter("AchievementsError.txt", true);
                    out = new PrintWriter(fileStream);
                    out.println("No achievements to reset.");
                    out.close();
                    fileStream.close();
                } 
                catch (IOException e1) {    
                    e1.printStackTrace();
                }
        }
        
        for(FinishAchievement f : finishTimeAchievementsList){
             f.setIsAchieved(false);
             f.setIsNewlyAchieved(false);
        }
        for(TimeAchievement t : totalTimeAchievementsList){
              t.setIsAchieved(false);
             t.setIsNewlyAchieved(false);
         }
         for(CollectingAchievement c : collectingAchievementsList){
            c.setIsAchieved(false);
             c.setIsNewlyAchieved(false);
         }
         for(TurnsAchievement t : turnsAchievementsList){
             t.setIsAchieved(false);
             t.setIsNewlyAchieved(false);
         }
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
    
    public void showAchievements(){
        String message = "";
        for(FinishAchievement f : finishTimeAchievementsList){
            message += f.toString();
            message += "\n";
        }
        for(TimeAchievement t : totalTimeAchievementsList){
            message += t.toString();
            message += "\n";
        }
        for(CollectingAchievement c : collectingAchievementsList){
            message += c.toString();
            message += "\n";
        }
        for(TurnsAchievement t : turnsAchievementsList){
            message += t.toString();
            message += "\n";
        }
        JOptionPane.showMessageDialog(null, "Achievements:\n" + message,"Achievements List",JOptionPane.INFORMATION_MESSAGE);
    }
    public void setWon(boolean isWonTheGame){
        isWon = isWonTheGame;
    }
}