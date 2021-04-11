package com.thoughtworks.pacman.core.achievements;

import com.thoughtworks.pacman.core.Game;

public class TurnAchievement extends Achievements {
    private static final long serialVersionUID = 1L;
    transient private Game game;
    private int level1, level2, level3;
    private boolean level1Achieved;
    private boolean level2Achieved;
    private boolean level3Achieved;
    private boolean firstTime = true;

    public TurnAchievement() {}
    
    public TurnAchievement(Game game){
        this.game = game;
        defineAchievements(75, 50, 25);

        TurnAchievement tempObj = getProgress("TurnProgress.txt");
        if(firstTime && tempObj == null) {
            this.firstTime = false;
            this.setProgressLocally("TurnProgress", this);
        }
        
        tempObj = getProgress("TurnProgress.txt");
        this.level1 = tempObj.level1; 
        this.level2 = tempObj.level2;
        this.level3 = tempObj.level3;

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

    public TurnAchievement getProgress(String fileName) {
        return (TurnAchievement) super.getProgress("TurnProgress.txt");
    }


    public void setProgressLocally(String fileName, Achievements achievementObj) {
       super.setProgressLocally("TurnProgress.txt", this);
    }


    public String getTurnAchievementInfo() {
        String keyPress =  "Yön tuşlarına "+  game.totalPressNumber() + " kez basıldı,\n";
        keyPress += "Up: " +  game.getUpKeyPress() + ", ";
        keyPress += "Down: " +  game.getDownKeyPress() + ", ";
        keyPress += "Left: " +  game.getLeftKeyPress() + ", ";
        keyPress += "Right: " +  game.getRigthKeyPress() + "\n";

        String status = "";
        if(!level1Achieved) {
            if(game.totalPressNumber() > level1)
                status = "Failed achievement.You exceeded the limit with " + (game.totalPressNumber() - level1) + " move for Level 1.Try again!";
            else {
                if(game.totalPressNumber() > level2) {
                    status = "Congratulations. You have unlocked Level 1 with " + game.totalPressNumber() + " moves";
                    this.level1Achieved = true;
                }
                else if(game.totalPressNumber() < level2 && game.totalPressNumber() > level3) {
                    status = "Congratulations. You have unlocked both Level 1 and Level 2 with " + game.totalPressNumber() + " moves";
                    this.level1Achieved = true;
                    this.level2Achieved = true;
                }
                else if(game.totalPressNumber() < level2 && game.totalPressNumber() < level3) {
                    status = "Congratulations. You have unlocked all Level 1, Level 2 and Level 3 at same time with " + game.totalPressNumber() + " moves";
                    this.level1Achieved = true;
                    this.level2Achieved = true;
                    this.level3Achieved = true;
                }
                setProgressLocally("TurnProgress.txt", this);
            }
            return keyPress + status;
        }
        else if(!level2Achieved && level1Achieved) {
            if(game.totalPressNumber() > level2)
                status = "You are close. If you make  " + (game.totalPressNumber() - level2) + " less move you will unlock Level 2. Try again!";
            else {
                if(game.totalPressNumber() < level2 && game.totalPressNumber() > level3) {
                    status = "Congratulations. You have unlocked Level 2 with " + game.totalPressNumber() + " moves";
                    this.level2Achieved = true;
                }
                else if(game.totalPressNumber() < level3) {
                    status = "Congratulations. You have unlocked both Level 2 and Level 3 with " + game.totalPressNumber() + " moves";
                    this.level2Achieved = true;
                    this.level3Achieved = true;
                }
                setProgressLocally("TurnProgress.txt", this);
            }
            return keyPress + status;
        }
        else if(!level3Achieved && level2Achieved && level1Achieved) {
            if(game.totalPressNumber() > level3)
                status = "You are close. If you make " + (game.totalPressNumber() - level3) + " less move you will unlock Level 3. Try again!";
            else {
                status = "Congratulations. You have unlocked Level 3 with " + game.totalPressNumber() + " moves";
                this.level3Achieved = true;
                setProgressLocally("TurnProgress.txt", this);
            }
            return keyPress + status;
        }
        return "You have unlocked all achievements related move number.";
    }

}
