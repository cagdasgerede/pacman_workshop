package com.thoughtworks.pacman.core;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class FileIO {
    private static Logger logger = LogManager.getLogManager().getLogger(FileIO.class.toString());
    private static String fileName;
    private static final int ScoreBoardSize = 5;

    public static ArrayList<ScoreEntry> load() {
        String path = getPath();
        ArrayList<ScoreEntry> scoreList = new ArrayList<>();
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scn = null;
        try {
            scn = new Scanner(new FileInputStream(file));
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                String name = line.substring(0, line.lastIndexOf(' '));
                Integer score = Integer.parseInt(line.substring(line.lastIndexOf(' ') + 1));
                ScoreEntry current = new ScoreEntry(name, score);
                scoreList.add(current);
            }
            scn.close();
        } catch (FileNotFoundException e) {
            logger.log(Level.INFO, e.getMessage(), e);
        }
        return scoreList;
    }

    public static void save(ArrayList<ScoreEntry> scoreList) {
        String path = getPath();
        File file = new File(path);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(file));
            for (int i = 0; i < scoreList.size(); i++) {
                if (i == ScoreBoardSize) break;
                if (i == scoreList.size() - 1)
                    pw.write(scoreList.get(i).toString());
                else
                    pw.write(scoreList.get(i).toString() + "\n");
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getScoreBoard() {
        String path = getPath();
        String Text = "";
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scn = null;
        try {
            scn = new Scanner(new FileInputStream(file));
            String line = "";
            for (int i = 0; i < ScoreBoardSize; i++) {
                if (scn.hasNextLine())
                    line = scn.nextLine();
                Text += "player " + (i + 1) + ": " + line + "\n";
                line = "";
            }
            scn.close();
        } catch (FileNotFoundException e) {
            logger.log(Level.INFO, e.getMessage(), e);
        }
        return Text;
    }

    public static String getPath(){
        String path = System.getProperty("user.dir");
        path = path.substring(0, path.lastIndexOf("\\"));
        path = path.substring(0, path.lastIndexOf("\\"));
        path = path + "\\" + fileName + ".txt";
        return path;
    }

    public static void setFileName(String fileName) {
        FileIO.fileName = fileName;
    }

    public static String getFileName() {
        return fileName;
    }
}
