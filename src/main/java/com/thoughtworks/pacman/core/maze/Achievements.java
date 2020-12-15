package com.thoughtworks.pacman.core.maze;

import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
            finish = new FinishAchievement(60);
            playTime = new TimeAchievement(120);
            collect = new CollectingAchievement(100);
            turns = new TurnsAchievement(50);
            finishTimeAchievementsList.add(finish);
            totalTimeAchievementsList.add(playTime);
            collectingAchievementsList.add(collect);
            turnsAchievementsList.add(turns);
        }
    }

    boolean readFromFile(){
        try{
            ObjectInputStream inputStream;

            inputStream = new ObjectInputStream(new FileInputStream("achievements.txt"));

            Object obj = null;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof FinishAchievement ) {
                    finishTimeAchievementsList.add((FinishAchievement) obj);
                    System.out.println("Added " + obj.getClass());

                }
                else if ( obj instanceof TimeAchievement){ 
                    totalTimeAchievementsList.add( (TimeAchievement) obj);
                    System.out.println("Added " + obj.getClass());

                }
                else if (obj instanceof CollectingAchievement){
                    collectingAchievementsList.add( (CollectingAchievement) obj);
                    System.out.println("Added " + obj.getClass());

                }
                else if (obj instanceof TurnsAchievement){
                    turnsAchievementsList.add( (TurnsAchievement) obj);
                    System.out.println("Added " + obj.getClass());

                }

            }



        }
        catch (EOFException e) {
            return true;
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

    void initializeAchievements() {
        if(!isInitialized){
            System.out.println("initing");

            try {
                FileOutputStream output = new FileOutputStream(new File("achievements.txt"));
                ObjectOutputStream o = new ObjectOutputStream(output);
                System.out.println("finish alist length:" + finishTimeAchievementsList.size());
    
                for(FinishAchievement f : finishTimeAchievementsList){
                    if (timeFinished < f.getTargetFinishTime() && isWon){
                        System.out.println("New achievement " + f.getClass());
    
                        f.setIsAchieved(true);
                        if(f.getIsNewlyAchieved() == false){
                            f.setIsNewlyAchieved(true);
                        }
                    }
                    o.writeObject(f);
                }
                System.out.println("time alist length:" + totalTimeAchievementsList.size());
    
                for(TimeAchievement t : totalTimeAchievementsList){
                    if (timePlayed > t.getTargetTime()){
                        System.out.println("New achievement " + t.getClass());
    
                        t.setIsAchieved(true);
                        t.setIsNewlyAchieved(true);
                        if(t.getIsNewlyAchieved() == false){
                            t.setIsNewlyAchieved(true);
                        }
                    }  
                    o.writeObject(t);
    
                }
                System.out.println("collect alist length:" + collectingAchievementsList.size());
    
                for(CollectingAchievement c : collectingAchievementsList){
                    if (itemsCollected > c.getTargetCollection()){
                        System.out.println("New achievement " +c.getTargetCollection()+ c.getClass());
    
                        c.setIsAchieved(true);
                        c.setIsNewlyAchieved(true);
                        if(c.getIsNewlyAchieved() == false){
                            c.setIsNewlyAchieved(true);
                        }
                    }
                    o.writeObject(c);
                }
                System.out.println("turns alist length:" + turnsAchievementsList.size());
    
                for(TurnsAchievement t : turnsAchievementsList){
                    if (turnsTook > t.getTargetTurns()){
                        System.out.println("New achievement " + t.getClass());
    
                        t.setIsAchieved(true);
                        t.setIsNewlyAchieved(true);
                        if(t.getIsNewlyAchieved() == false){
                            t.setIsNewlyAchieved(true);
                        }
                    }
                    o.writeObject(t);
                }
                o.close();
                output.close();
            } 
            catch (IOException e) {
               e.printStackTrace();
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
            e.printStackTrace();
        }
        
        

        if (true) { 
            System.out.println("file deleted.");

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
          else {
            System.out.println("No achievements to be resetted.");
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
    
    public void isNewlyAchievedFinished(){
        for(FinishAchievement f : finishTimeAchievementsList){
            if(f.getIsNewlyAchieved()){
                JOptionPane.showMessageDialog(null, "Congratrulations ! " + f.toString());
                f.setIsNewlyAchieved(false);
            }
        }
    }
    public  void isNewlyAchievedPlayed(){
        for(TimeAchievement t : totalTimeAchievementsList){
            if(t.getIsNewlyAchieved()){
                JOptionPane.showMessageDialog(null, "Congratrulations ! " + t.toString());
                t.setIsNewlyAchieved(false);
            }
        }
    }
    public   void isNewlyAchievedCollected(){
        for(CollectingAchievement c : collectingAchievementsList){
            if(c.getIsNewlyAchieved()){
                JOptionPane.showMessageDialog(null, "Congratrulations ! " + c.toString());
                c.setIsNewlyAchieved(false);
            }
        }
        
    }
    public void isNewlyAchievedTook(){
        for(TurnsAchievement t : turnsAchievementsList){
            if(t.getIsNewlyAchieved()){
                JOptionPane.showMessageDialog(null, "Congratrulations ! " + t.toString());
                t.setIsNewlyAchieved(false);
            }
        }
    }
    public void setWon(boolean isWonTheGame){
        isWon = isWonTheGame;
    }
    
    

}
