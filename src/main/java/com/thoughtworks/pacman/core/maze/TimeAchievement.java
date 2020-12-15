package com.thoughtworks.pacman.core.maze;

import java.io.Serializable;

public class TimeAchievement implements Serializable {
    private int targetTime;
    private boolean isAchieved;
    private boolean isNewlyAchieved ;

    public TimeAchievement(int time){
        targetTime = time;
        isAchieved = false;
        isNewlyAchieved = false;
    }
    void setTargetTime(int time){
        targetTime = time;
    }
    int getTargetTime(){
        return targetTime;
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
        String time = String.valueOf(this.targetTime);
        if(isAchieved)
            return "Reached " + time + " ingame seconds : ACHIEVEMENT UNLOCKED !";
        else
            return "Reached " + time + " ingame seconds : NOT ACHIEVED";
    }
}
