package com.thoughtworks.pacman.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.awt.event.KeyEvent;
public class Settings {
    private int pacmanSpeed;
    private int ghostSpeed;
    private int pacmanColorIndex;
    private int pacmanShapeIndex;
    private int[] keyIndex = new int[4];
    private int[] keyEventNumb = new int[4];
    private int CRC;
    private int coefficient=17;
    private Scanner scanner;
    private boolean buildCorrect;
    public Settings() {
        getValuesFromFile();
    }
    public void getValuesFromFile(){
            try {
                scanner = new Scanner(new File("config.txt"));
                if(scanner==null){
                    buildCorrect=false;
                    return;
                }
                    
                pacmanSpeed = scanner.nextInt();
                ghostSpeed = scanner.nextInt();
                pacmanColorIndex = scanner.nextInt();
                pacmanShapeIndex = scanner.nextInt();
                keyIndex[0] = scanner.nextInt();
                keyIndex[1] = scanner.nextInt();
                keyIndex[2] = scanner.nextInt();
                keyIndex[3] = scanner.nextInt();
                keyEventNumb[0] = gKeyEvent(keyIndex[0]);
                keyEventNumb[1] = gKeyEvent(keyIndex[1]);
                keyEventNumb[2] = gKeyEvent(keyIndex[2]);
                keyEventNumb[3] = gKeyEvent(keyIndex[3]);
                if(scanner.hasNextInt())
                    CRC = scanner.nextInt();
                scanner.close();
                if(controlCRC()==0) {
                    JOptionPane.showMessageDialog(null, "config.txt file was changed externally\nAll settings was changed as default back");
                    setDefaultSettings();
                }
                buildCorrect =true;
            }
            catch (IOException e) {
                JOptionPane.showMessageDialog(null, "config.txt file was not founded\nAll settings was changed as default back");
                setDefaultSettings();
                buildCorrect =true;
            }
    }
    public void setDefaultSettings(){
        pacmanSpeed = 40;
        ghostSpeed = 20;
        pacmanColorIndex = 0;
        pacmanShapeIndex = 0;
        keyIndex[0] = 0;
        keyIndex[1] = 1;
        keyIndex[2] = 2;
        keyIndex[3] = 3;
        keyEventNumb[0] = gKeyEvent(keyIndex[0]);
        keyEventNumb[1] = gKeyEvent(keyIndex[1]);
        keyEventNumb[2] = gKeyEvent(keyIndex[2]);
        keyEventNumb[3] = gKeyEvent(keyIndex[3]);
        saveSettings(pacmanSpeed,ghostSpeed,pacmanColorIndex,pacmanShapeIndex,keyIndex[0],keyIndex[1],keyIndex[2],keyIndex[3]);
    }
    public void saveSettings(int pacmanSpeed,int ghostSpeed,int pacmanColorIndex,int pacmanShapeIndex,int upKeyCursor,int downKeyCursor,int rightKeyCursor,int leftKeyCursor){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"));
            writer.write(pacmanSpeed+" ");
            writer.write(ghostSpeed+" ");
            writer.write(pacmanColorIndex+" ");
            writer.write(pacmanShapeIndex+" ");
            writer.write((upKeyCursor)+" ");
            writer.write((downKeyCursor)+" ");
            writer.write((rightKeyCursor)+" ");
            writer.write((leftKeyCursor)+" ");
            crateCRC(pacmanSpeed+ghostSpeed+pacmanColorIndex+pacmanShapeIndex+upKeyCursor+downKeyCursor+rightKeyCursor+leftKeyCursor);
            writer.write(getCRC()+" ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int gKeyEvent(int index){
        switch(index){
                case 0: return KeyEvent.VK_UP;
                case 1: return KeyEvent.VK_DOWN;
                case 2: return KeyEvent.VK_RIGHT;
                case 3: return KeyEvent.VK_LEFT;
                case 4: return KeyEvent.VK_W;
                case 5: return KeyEvent.VK_S;
                case 6: return KeyEvent.VK_A;
                case 7: return KeyEvent.VK_D;
                case 8: return KeyEvent.VK_X;
                case 9: return KeyEvent.VK_C;
                case 10: return KeyEvent.VK_V;
                case 11: return KeyEvent.VK_NUMPAD8;
                case 12: return KeyEvent.VK_NUMPAD4;
                case 13: return KeyEvent.VK_NUMPAD2;
                case 14: return KeyEvent.VK_NUMPAD6;
            }
            return 0;
        }
    public boolean getBuildCorrect() {
        return buildCorrect;
    }
    public int crateCRC(int sum){
        return CRC = sum-((sum/coefficient)*coefficient);
    }
    public int controlCRC(){
        int sum = pacmanSpeed+ghostSpeed+pacmanColorIndex+pacmanShapeIndex+keyIndex[0]+keyIndex[1]+keyIndex[2]+keyIndex[3];
        if ((sum-CRC)%coefficient==0) return 1;
        else return 0;
    }
    public void setPacmanShapeIndex(int pacmanShapeIndex) {
        this.pacmanShapeIndex = pacmanShapeIndex;
    }
    public void setGhostSpeed(int ghostSpeed) {
        this.ghostSpeed = ghostSpeed;
    }
    public void setKeyIndex(int[] keyIndex) {
        this.keyIndex = keyIndex;
    }
    public void setPacmanColorIndex(int pacmanColorIndex) {
        this.pacmanColorIndex = pacmanColorIndex;
    }
    public void setPacmanSpeed(int pacmanSpeed) {
        this.pacmanSpeed = pacmanSpeed;
    }
    public int getPacmanShapeIndex() {
        return pacmanShapeIndex;
    }
    public int getGhostSpeed() {
        return ghostSpeed;
    }
    public int[] getKeyIndex() {
        return keyIndex;
    }
    public int[] getKeyEventNumb() {
        return keyEventNumb;
    }
    public int getPacmanColorIndex() {
        return pacmanColorIndex;
    }
    public int getPacmanSpeed() {
        return pacmanSpeed;
    }
    public int getCRC() {
        return CRC;
    }
    public int getCoefficient() {
        return coefficient;
    }
}
