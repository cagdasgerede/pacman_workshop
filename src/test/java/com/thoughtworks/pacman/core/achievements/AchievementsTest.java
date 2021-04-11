package com.thoughtworks.pacman.core.achievements;

import org.junit.Test;
import org.junit.Before;
import java.io.*;
import static org.junit.Assert.*;
import com.thoughtworks.pacman.core.achievements.*;

public class AchievementsTest {
    Achievements testObj;

    public void init(String fileName) {
        FileInputStream fi;
        ObjectInputStream oi;
        File file = new File("src/main/java/com/thoughtworks/pacman/core/achievements/progress/" + fileName);
        Achievements achievementObject = null;

        try{
             fi = new FileInputStream(file);
             oi = new ObjectInputStream(fi);

            achievementObject = (Achievements) oi.readObject();

             oi.close();
             fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        testObj = achievementObject;

    }


    @Test
    public void testGetAchievemenInfoMethodReturnsAString() {
        Achievements achievements = new Achievements();
        assertEquals(achievements.getAchievementInfo().getClass().getSimpleName(), "String");
    }

    @Test
    public void testIsSuccesfullGettingObjectDataFromTimeProgress() {
        init("TimeProgress.txt");
        System.out.println(testObj);
        assertTrue(testObj != null);
    }

    @Test
    public void testIsSuccesfullGettingObjectDataFromTurnProgress() {
        init("TurnProgress.txt");
        System.out.println(testObj);
        assertTrue(testObj != null);
    }

    @Test
    public void testIsSuccesfullGettingObjectDataFromOverallTimeProgress() {
        init("OverallTimeProgress.txt");
        System.out.println(testObj);
        assertTrue(testObj != null);
    }

    @Test
    public void testIsSuccesfullGettingObjectDataFromItemCollectProgress() {
        init("ItemCollectProgress.txt");
        assertTrue(testObj != null);
    }

}
