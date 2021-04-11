package com.thoughtworks.pacman.core.achievements;

import com.thoughtworks.pacman.core.Game;
import java.io.Serializable;
import java.sql.Time;

public class TimeAchievement extends Achievements implements Serializable {

    private static final long serialVersionUID = 1L;
    transient private Game game;
    private int level1, level2, level3;
    private boolean level1Achieved;
    private boolean level2Achieved;
    private boolean level3Achieved;
    private boolean firstTime = true;

    public TimeAchievement() {

    }

    public TimeAchievement(Game game) {
        this.game = game;
        defineAchievements(30, 60, 120);

        TimeAchievement tempObj = getProgress("TimeProgress.txt");
        if(firstTime && tempObj == null) {
            firstTime = false;
            setProgressLocally("TimeProgress.txt", this);
        }

        tempObj = getProgress("TimeProgress.txt");
        this.level1 = tempObj.level1; 
        this.level2 = tempObj.level2;
        this.level3 = tempObj.level3;
        this.firstTime = tempObj.firstTime;
        
        this.level1Achieved = tempObj.level1Achieved;
        this.level2Achieved = tempObj.level2Achieved;
        this.level3Achieved = tempObj.level3Achieved;
    }

    private void defineAchievements(int level1, int level2, int level3) {
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
    }

    
    public void setLevel1Achieved(boolean l1) {
        this.level1Achieved = l1;
    }
    public void setLevel2Achieved(boolean l2) {
        this.level2Achieved = l2;
    }
    public void setLevel3Achieved(boolean l3) {
        this.level3Achieved = l3;
    }
    public boolean getLevel1Achieved() {
        return this.level1Achieved;
    }
    public boolean getLevel2Achieved() {
        return this.level2Achieved;
    }
    public boolean getLevel3Achieved() {
        return this.level3Achieved;
    }
 

    public TimeAchievement getProgress(String fileName) {
       return (TimeAchievement) super.getProgress("TimeProgress.txt");
    }


    public void setProgressLocally(String fileName, Achievements achievementObject) {
        super.setProgressLocally("TimeProgress.txt", this);
    }

    public String getTimeAchievementInfo() {
        double time = game.getGameplayTime() / 1000000000;
        String status = "";

        if(!level1Achieved) {
            if(time < level1) {
                status = "You died before "+ level1 + " seconds. If you survive more than " + level1 + " seconds, you will unlock Level 1. Try again!";
                return status;
            }
            else {
                if(time >= level1 && time < level2) {
                    status = "Congratulations. You have unlocked Level 1 with surviving " + time + " seconds";
                    this.level1Achieved = true;
                }
                else if(time >= level2 && time < level3) {
                    status = "Congratulations. You have unlocked both Level 1 and Level 2 with surviving " + time + " seconds";
                    this.level1Achieved = true;
                    this.level2Achieved = true;
                }
                else if(time > level3) {
                    status = "Congratulations. You have unlocked all Level 1, Level 2 and Level 3 at same time with surviving" + time + " seconds";
                    this.level1Achieved = true;
                    this.level2Achieved = true;
                    this.level3Achieved = true;
                }
                setProgressLocally("TimeProgress.txt", this);
            }
            return status;
        }
        else if(!level2Achieved && level1Achieved) {
            if(time < level2)
                status = "You survived: " + time + " seconds. You are close to unlock Level 2.Try again!";
            else {
                if(time < level3) {
                    status = "Congratulations. You survived " + time + " seconds. Now you are Level 2";
                    this.level2Achieved = true;
                }
                else {
                    status = "Congratulations. You have unlocked Level 2 and Level 3 at the same time with surviving" + time + " seconds";
                    this.level2Achieved = true;
                    this.level3Achieved = true;
                }
                setProgressLocally("TimeProgress.txt", this);
            }
            return status;
        }
        else if(!level3Achieved && level2Achieved && level1Achieved) {
            if(time < level3)
                status = "You survived " + time + " seconds. You are close to unlock Level 3. Try again!";
            else {
                status = "Congratulations. You have unlocked Level 3 with surviving " + time + " seconds";
                this.level3Achieved = true;
                setProgressLocally("TimeProgress.txt", this);
            }
            return status;
        }
        return "You have unlocked all achievements time related. ";
    }

}
