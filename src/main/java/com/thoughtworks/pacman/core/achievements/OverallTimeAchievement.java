package com.thoughtworks.pacman.core.achievements;

import com.thoughtworks.pacman.core.Game;

public class OverallTimeAchievement extends Achievements {
    private static final long serialVersionUID = 1L;
    transient private Game game;
    private int level1, level2, level3;
    private boolean level1Achieved, level2Achieved, level3Achieved;
    private double totalPlayTime;
    private boolean firstTime = true;

    public OverallTimeAchievement() {}
    
    public OverallTimeAchievement(Game game){
        this.game = game;
        defineAchievements(300, 600, 900);
        OverallTimeAchievement tempObj = getProgress("OverallTimeProgress.txt");

        if(firstTime && tempObj == null) {
            firstTime = false;
            this.setProgressLocally("OverallTimeProgress.txt", this);
        }

        tempObj = getProgress("OverallTimeProgress.txt");
        this.level1 = tempObj.level1; 
        this.level2 = tempObj.level2;
        this.level3 = tempObj.level3;
        this.totalPlayTime = tempObj.totalPlayTime;
        this.firstTime = tempObj.firstTime;

        this.level1Achieved = tempObj.level1Achieved;
        this.level2Achieved = tempObj.level2Achieved;
        this.level3Achieved = tempObj.level3Achieved;
    }

    private void defineAchievements(int first, int second, int third) {
        this.level1 = first;
        this.level2 = second;
        this.level3 = third;
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


    public OverallTimeAchievement getProgress(String fileName) {
        return (OverallTimeAchievement) super.getProgress("OverallTimeProgress.txt");
    }

    public void setProgressLocally(String fileName, Achievements achievementObj){
        super.setProgressLocally("OverallTimeProgress.txt", this);
    }

    public String getOverallTimeAchievementInfo() {
        double timePerLevel = game.getGameplayTime() / 1000000000;

        this.totalPlayTime += timePerLevel;
        setProgressLocally("OverallTimeProgress.txt", this);

        String status = "";
        if(!level1Achieved) {
            if(totalPlayTime < level1) {
                status = "You played Pacman for " + totalPlayTime + " seconds. If you play " + (level1 - totalPlayTime)+ " seconds you will unlock Level 1";
                return status;
            }
            else {
                if(totalPlayTime >= level1 && totalPlayTime< level2) {
                    status = "Congratulations. You have unlocked Level 1 with playing Pacman " +totalPlayTime+ " seconds overall";
                    this.level1Achieved = true;
                }
                else if(totalPlayTime >= level2 && totalPlayTime < level3) {
                    status = "Congratulations. You have unlocked both Level 1 and Level 2 with playing Pacman for " + totalPlayTime+ " seconds overall";
                    this.level1Achieved = true;
                    this.level2Achieved = true;
                }
                else if(totalPlayTime > level3) {
                    status = "Congratulations. You have unlocked all Level 1, Level 2 and Level 3 at same time playing Pacman for" +totalPlayTime+ " seconds";
                    this.level1Achieved = true;
                    this.level2Achieved = true;
                    this.level3Achieved = true;
                }
                setProgressLocally("OverallTimeProgress.txt", this);
            }
            return status;
        }
        else if(!level2Achieved && level1Achieved) {
            if(totalPlayTime < level2)
                status = "You played Pacman for " + totalPlayTime + " seconds overall. You are close to unlock Level 2.Try again!";
            else {
                if(totalPlayTime < level3) {
                    status = "Congratulations. You played Pacman for " + totalPlayTime + " seconds. Now you are Level 2";
                    this.level2Achieved = true;
                }
                else {
                    status = "Congratulations. You have unlocked Level 2 and Level 3 at the same playing Pacman for " + totalPlayTime + " seconds overall";
                    this.level2Achieved = true;
                    this.level3Achieved = true;
                }
                setProgressLocally("OverallTimeProgress.txt", this);
            }
            return status;
        }
        else if(!level3Achieved && level2Achieved && level1Achieved) {
            if(totalPlayTime < level3)
                status = "You played Pacman for " + totalPlayTime + " seconds. You are close to unlock Level 3. Try again!";
            else {
                status = "Congratulations. You have unlocked Level 3 with playing Pacman for " + totalPlayTime + " seconds overall";
                this.level3Achieved = true;
                setProgressLocally("OverallTimeProgress.txt", this);
            }
            return status;
        }
        return "You have unlocked all achievements related to overall playing time.";
    }

}
