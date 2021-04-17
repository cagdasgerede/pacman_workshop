package com.thoughtworks.pacman.core;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.KeyEvent;
public class Settings {
    private int pacmanSpeed;
    private int ghostSpeed;
    private int pacmanColorIndex;
    private int ghostColorIndex;
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
                else
                    buildCorrect =true;
                    
                pacmanSpeed = scanner.nextInt();
                ghostSpeed = scanner.nextInt();
                pacmanColorIndex = scanner.nextInt();
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
                if(controlCRC()==0) System.out.println("ERROR");;
            }
            catch (IOException e) {
                pacmanSpeed = 20;
                ghostSpeed = 20;
                pacmanColorIndex = 0;
                keyIndex[0] = 0;
                keyIndex[1] = 1;
                keyIndex[2] = 2;
                keyIndex[3] = 3;
                keyEventNumb[0] = gKeyEvent(keyIndex[0]);
                keyEventNumb[1] = gKeyEvent(keyIndex[1]);
                keyEventNumb[2] = gKeyEvent(keyIndex[2]);
                keyEventNumb[3] = gKeyEvent(keyIndex[3]);
                crateCRC(pacmanSpeed+ghostSpeed+pacmanColorIndex+keyIndex[0]+keyIndex[1]+keyIndex[2]+keyIndex[3]);
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
    public boolean buildCorrect() {
        return buildCorrect;
    }
    public void crateCRC(int sum){
        CRC = sum-((sum/coefficient)*coefficient);
        System.out.println("sum "+sum+" CRC "+CRC);
    }
    public int controlCRC(){
        int sum = pacmanSpeed+ghostSpeed+pacmanColorIndex+keyIndex[0]+keyIndex[1]+keyIndex[2]+keyIndex[3];
        System.out.println("Sum-CRC "+sum+" "+CRC);
        if ((sum-CRC)%coefficient==0) return 1;
        else return 0;
        }
    public void setGhostColorIndex(int ghostColorIndex) {
        this.ghostColorIndex = ghostColorIndex;
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
    public int getGhostColorIndex() {
        return ghostColorIndex;
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
}
