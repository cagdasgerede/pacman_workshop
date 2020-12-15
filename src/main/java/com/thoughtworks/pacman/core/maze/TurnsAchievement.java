package com.thoughtworks.pacman.core.maze;

import java.io.Serializable;

public class TurnsAchievement implements Serializable {
    private int targetTurns;
    private boolean isAchieved;
    private boolean isNewlyAchieved ;

    public TurnsAchievement(int turns){
        targetTurns = turns;
        isAchieved = false;
        isNewlyAchieved = false;
    }
    void setTargetTurns(int turns){
        targetTurns = turns;
    }
    int getTargetTurns(){
        return targetTurns;
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
        String turns = String.valueOf(this.targetTurns);
        if(isAchieved)
            return "Pacman took " + turns + " turns : ACHIEVEMENT UNLOCKED !";
        else
            return "Pacman took " + turns + " turns : NOT ACHIEVED";
    }
}
