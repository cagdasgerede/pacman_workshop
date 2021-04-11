package com.thoughtworks.pacman.core.achievements;

import com.thoughtworks.pacman.core.Game;

public class ItemCollectAchievement extends Achievements{
    private static final long serialVersionUID = 1L;
    transient private Game game;
    private int level1,level2,level3;
    private boolean level1Achieved, level2Achieved, level3Achieved;
    private boolean firstTime = true;

    public ItemCollectAchievement() {}
    
    public ItemCollectAchievement(Game game) {
        this.game = game;
        defineAchievements(25, 50, 75); // parameters indicates percentages of total items to unlock related level

        ItemCollectAchievement tempObj = getProgress("ItemCollectProgress.txt");
        if(firstTime && tempObj == null) {
            firstTime = false;
            setProgressLocally("ItemCollectProgress.txt", this);
        }

        tempObj = getProgress("ItemCollectProgress.txt");
        this.level1 = tempObj.level1; 
        this.level2 = tempObj.level2;
        this.level3 = tempObj.level3;
        this.firstTime = tempObj.firstTime;

        this.level1Achieved = tempObj.level1Achieved;
        this.level2Achieved = tempObj.level2Achieved;
        this.level3Achieved = tempObj.level3Achieved;
    }

    public void defineAchievements(int first, int second, int third) {
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


    public void setProgressLocally(String fileName, ItemCollectAchievement achievementObj) {
        super.setProgressLocally("ItemCollectProgress.txt", this);
    }

    public ItemCollectAchievement getProgress(String fileName) {
        return (ItemCollectAchievement) super.getProgress("ItemCollectProgress.txt");
    }

    public String getItemCollectAchievementInfo() {
        int itemsPacmanCollected = game.getScore() / 10; // every 10 points equal to 1 item
        int remainingItems = game.getDotsLeft();

        double collectionRate =  ((double)itemsPacmanCollected / (double) (itemsPacmanCollected + remainingItems)) * (double) 100; // percentage of collected items
       
        String status = "";
        if(!level1Achieved) {
            if(collectionRate < level1) {
                status = "You have collected " + String.format("%2.02f",collectionRate) + " % of items. If you collect " + level1 + " % of items, you will unlock Level 1";
                return status;
            }
            else {
                if(collectionRate >= level1 && collectionRate< level2) {
                    status = "Congratulations. You have unlocked Level 1 with collecting " + String.format("%2.02f",collectionRate) + " % of items";
                    this.level1Achieved = true;
                }
                else if(collectionRate >= level2 && collectionRate < level3) {
                    status = "Congratulations. You have unlocked both Level 1 and Level 2 with collecting " + String.format("%2.02f",collectionRate) + " % of items";
                    this.level1Achieved = true;
                    this.level2Achieved = true;
                }
                else if(collectionRate > level3) {
                    status = "Congratulations. You have unlocked all Level 1, Level 2 and Level 3 at the same time collecting" + String.format("%2.02f",collectionRate) + " % of items";
                    this.level1Achieved = true;
                    this.level2Achieved = true;
                    this.level3Achieved = true;
                }
                setProgressLocally("ItemCollectProgress.txt", this);
            }
            return status;
        }
        else if(!level2Achieved && level1Achieved) {
            if(collectionRate < level2)
                status = "You collected " + String.format("%2.02f",collectionRate) + " % of items. You are close to unlock Level 2.Try again!";
            else {
                if(collectionRate < level3) {
                    status = "Congratulations. You collected " + String.format("%2.02f",collectionRate) + " % of items. Now you are Level 2";
                    this.level2Achieved = true;
                }
                else {
                    status = "Congratulations. You have unlocked Level 2 and Level 3 at the same collecting " + String.format("%2.02f",collectionRate) + " % of items";
                    this.level2Achieved = true;
                    this.level3Achieved = true;
                }
                setProgressLocally("ItemCollectProgress.txt", this);
            }
            return status;
        }
        else if(!level3Achieved && level2Achieved && level1Achieved) {
            if(collectionRate < level3)
                status = "You collected " + String.format("%2.02f",collectionRate) + " % of items. You are close to unlock Level 3. Try again!";
            else {
                status = "Congratulations. You have unlocked Level 3 with collecting " + String.format("%2.02f",collectionRate) + " % of items";
                this.level3Achieved = true;
                setProgressLocally("ItemCollectProgress.txt", this);
            }
            return status;
        }
        return "You have unlocked all achievements related item collection.";
    }

}
