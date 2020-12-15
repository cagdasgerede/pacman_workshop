package com.thoughtworks.pacman.core.maze;

import java.io.Serializable;

public class CollectingAchievement implements Serializable {
    private int targetCollection;
    private boolean isAchieved;
    private boolean isNewlyAchieved ;

    public CollectingAchievement(int collection){
        targetCollection = collection;
        isAchieved = false;
        isNewlyAchieved = false;
    }
    void setTargetCollection(int collection){
        targetCollection = collection;
    }
    int getTargetCollection(){
        return targetCollection;
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
        String collection = String.valueOf(this.targetCollection);
        if(isAchieved)
            return collection + " items collected : ACHIEVEMENT UNLOCKED !";
        else
            return collection + " items collected : NOT ACHIEVED";
    }
}
