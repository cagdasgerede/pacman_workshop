package com.thoughtworks.pacman.core.achievements;

import com.thoughtworks.pacman.core.Game;
import java.io.*;

public class Achievements implements Serializable{
    private static final long serialVersionUID= 1L;

    public Achievements(Game game) {   
    }

    public Achievements() {
    }

    public void setProgressLocally(String fileName, Achievements achievementObj) {
        try {
            File file = new File("../../src/main/java/com/thoughtworks/pacman/core/achievements/progress/" + fileName);
            FileOutputStream stream = new FileOutputStream(file);
            ObjectOutputStream obj = new ObjectOutputStream(stream);
            
            obj.writeObject(achievementObj);

            obj.close();
            stream.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Achievements getProgress(String fileName) {
        FileInputStream fi;
        ObjectInputStream oi;
        File file = new File("../../src/main/java/com/thoughtworks/pacman/core/achievements/progress/" + fileName);
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

        return achievementObject;
    }

    public String getAchievementInfo() {
        String first = "There are 4 type of achievements in this game:\n";

        String turnNumber = "\nIf you won the game\n     * within 75-100 moves, you will unlock Level 1\n";
        turnNumber += "     * within 25-50 moves, you will unlock Level 2\n";
        turnNumber += "     * within 0-25 moves, you will unlock Level 3\n\n";

        String timeEachLevel = "If you survive in the game\n      * for 30 - 60 second, you will unlock Level 1\n";
        timeEachLevel += "      * for 60 - 120 seconds, you will unlock Level 2\n";
        timeEachLevel += "      * for 120+ seconds, you will unlock Level 3\n\n";

        String overallPlay = "If you play with the game for\n       * 300 second overall, you will unlock Level 1\n";
        overallPlay += "       * 600 seconds overall, you will unlock Level 2\n";
        overallPlay += "       * 900 seconds overall, you will unlock Level 3\n\n";

        String collectItem = "If you collect\n       * % 25 of items, you will unlock Level 1\n";
        collectItem += "       * % 50 of items, you will unlock Level 2\n";
        collectItem += "       * % 75 items, you will unlock Level 3\n";

        return first + turnNumber + timeEachLevel + overallPlay + collectItem;
    }
}
