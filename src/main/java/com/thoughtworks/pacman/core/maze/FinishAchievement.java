package com.thoughtworks.pacman.core.maze;

import java.io.Serializable;

public class FinishAchievement implements Serializable {
    private int targetFinishTime;
    private boolean isAchieved;
    private boolean isNewlyAchieved ;

    public FinishAchievement(int time){
        targetFinishTime = time;
        isAchieved = false;
        isNewlyAchieved = false;
    }
    void setTargetFinishTime(int time){
        targetFinishTime = time;
    }
    int getTargetFinishTime(){
        return targetFinishTime;
    }
    void setIsAchieved(boolean b){
        isAchieved = b;
    }
    boolean getIsAchieved(){
        return isAchieved;
    }
    void setIsNewlyAchieved(boolean b){
        isNewlyAchieved = b;
    }
    boolean getIsNewlyAchieved(){
        return isNewlyAchieved;
    }
    @Override
    public String toString(){
        String time = String.valueOf(this.targetFinishTime/60);
        if(isAchieved)
            return "Level finished in " + time + " minutes : ACHIEVEMENT UNLOCKED !";
        else
            return "Level finished in " + time + " minutes : NOT ACHIEVED";
    }
}
