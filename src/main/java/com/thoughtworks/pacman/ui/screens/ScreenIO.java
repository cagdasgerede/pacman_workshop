package com.thoughtworks.pacman.ui.screens;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.awt.event.KeyEvent;

public class ScreenIO {


    public static void setSettings( GameScreen game ) {
        try {
        File file=new File("config.txt");    
        FileReader fr=new FileReader(file);   
        BufferedReader br=new BufferedReader(fr);  

        String line=br.readLine();
        switch(line) {
            case "low":
                game.setTimeDeltaGhost(100); 
                break;
            case "medium":
                game.setTimeDeltaGhost(200);
                break;
            case "high":
                game.setTimeDeltaGhost(300);
                break;
        }


        line=br.readLine();

        switch(line) {
            case "low":
                game.setTimeDeltaPacman(100); 
                break;
            case "medium":
                game.setTimeDeltaPacman(200);
                break;
            case "high":
                game.setTimeDeltaPacman(300);
                break;
        }

        line=br.readLine();
        line=br.readLine();

        switch (line) {
        case "UP KEY":
            game.setDirUp(KeyEvent.VK_UP);
            //game.dirup = KeyEvent.VK_UP;
            break;
        case "RIGHT KEY":
            game.setDirUp(KeyEvent.VK_RIGHT);
            //game.dirup = KeyEvent.VK_RIGHT;
            break;
        case "LEFT KEY":
            game.setDirUp(KeyEvent.VK_LEFT);
            //game.dirup = KeyEvent.VK_LEFT;
            break;
        case "DOWN KEY":
            game.setDirUp(KeyEvent.VK_DOWN);
            //game.dirup = KeyEvent.VK_DOWN;
            break;
        case "Num8":
            game.setDirUp(KeyEvent.VK_NUMPAD8);
            //game.dirup = KeyEvent.VK_NUMPAD8;
            break;
        case "Num6":
            game.setDirUp(KeyEvent.VK_NUMPAD6);
            //game.dirup = KeyEvent.VK_NUMPAD6;
            break;
        case "Num4":
            game.setDirUp(KeyEvent.VK_NUMPAD4);
            //game.dirup = KeyEvent.VK_NUMPAD4;
            break;
        case "Num2":
            game.setDirUp(KeyEvent.VK_NUMPAD2);
            //game.dirup = KeyEvent.VK_NUMPAD2;
            break;        
        }       

        line=br.readLine();

        switch (line) {
        case "UP KEY":
            game.setDirDown(KeyEvent.VK_UP);
            //game.dirdown = KeyEvent.VK_UP;
            break;
        case "RIGHT KEY":
            game.setDirDown(KeyEvent.VK_RIGHT);
            //game.dirdown = KeyEvent.VK_RIGHT;
            break;
        case "LEFT KEY":
            game.setDirDown(KeyEvent.VK_LEFT);
            //game.dirdown = KeyEvent.VK_LEFT;
            break;
        case "DOWN KEY":
            game.setDirDown(KeyEvent.VK_DOWN);
            //game.dirdown = KeyEvent.VK_DOWN;
            break;
        case "Num8":
            game.setDirDown(KeyEvent.VK_NUMPAD8);
            //game.dirdown = KeyEvent.VK_NUMPAD8;
            break;
        case "Num6":
            game.setDirDown(KeyEvent.VK_NUMPAD6);
            //game.dirdown = KeyEvent.VK_NUMPAD6;
            break;
        case "Num4":
            game.setDirDown(KeyEvent.VK_NUMPAD4);
            //game.dirdown = KeyEvent.VK_NUMPAD4;
            break;
        case "Num2":
            game.setDirDown(KeyEvent.VK_NUMPAD2);
            //game.dirdown = KeyEvent.VK_NUMPAD2;
            break;        
        } 

        line=br.readLine();

        switch (line) {
        case "UP KEY":
            game.setDirLeft(KeyEvent.VK_UP);
            //game.dirleft = KeyEvent.VK_UP;
            break;
        case "RIGHT KEY":
            game.setDirLeft(KeyEvent.VK_RIGHT);
            //game.dirleft = KeyEvent.VK_RIGHT;
            break;
        case "LEFT KEY":
            game.setDirLeft(KeyEvent.VK_LEFT);
            //game.dirleft = KeyEvent.VK_LEFT;
            break;
        case "DOWN KEY":
            game.setDirLeft(KeyEvent.VK_DOWN);
            //game.dirleft = KeyEvent.VK_DOWN;
            break;
        case "Num8":
            game.setDirLeft(KeyEvent.VK_NUMPAD8);
            //game.dirleft = KeyEvent.VK_NUMPAD8;
            break;
        case "Num6":
            game.setDirLeft(KeyEvent.VK_NUMPAD6);
            //game.dirleft = KeyEvent.VK_NUMPAD6;
            break;
        case "Num4":
            game.setDirLeft(KeyEvent.VK_NUMPAD4);
            //game.dirleft = KeyEvent.VK_NUMPAD4;
            break;
        case "Num2":
            game.setDirLeft(KeyEvent.VK_NUMPAD2);
            //game.dirleft = KeyEvent.VK_NUMPAD2;
            break;        
        } 
        line=br.readLine();

        switch (line) {
        case "UP KEY":
            game.setDirRight(KeyEvent.VK_UP);
            //game.dirright = KeyEvent.VK_UP;
            break;
        case "RIGHT KEY":
            game.setDirRight(KeyEvent.VK_RIGHT);
            //game.dirright = KeyEvent.VK_RIGHT;
            break;
        case "LEFT KEY":
            game.setDirRight(KeyEvent.VK_LEFT);
            //game.dirright = KeyEvent.VK_LEFT;
            break;
        case "DOWN KEY":
            game.setDirRight(KeyEvent.VK_DOWN);
            //game.dirright = KeyEvent.VK_DOWN;
            break;
        case "Num8":
            game.setDirRight(KeyEvent.VK_NUMPAD8);
            //game.dirright = KeyEvent.VK_NUMPAD8;
            break;
        case "Num6":
            game.setDirRight(KeyEvent.VK_NUMPAD6);
            //game.dirright = KeyEvent.VK_NUMPAD6;
            break;
        case "Num4":
            game.setDirRight(KeyEvent.VK_NUMPAD4);
            //game.dirright = KeyEvent.VK_NUMPAD4;
            break;
        case "Num2":
            game.setDirRight(KeyEvent.VK_NUMPAD2);
            //game.dirright = KeyEvent.VK_NUMPAD2;
            break;        
        }      

        }catch (IOException ioe){
            ioe.printStackTrace();  
        }

    }

}