package com.thoughtworks.pacman.ui.screens;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class ScreenIOTest {

    @Test
    public void test_configFileReadsCorrect() throws IOException {


        File file=new File("config.txt");    
        FileReader fr=new FileReader(file);   
        BufferedReader br=new BufferedReader(fr);  

        String line=br.readLine();

        boolean flag = false;

        if (line.equals("high") || line.equals("medium") || line.equals("low") ){
            flag = true;
        }

        assertEquals(true, flag);

        line=br.readLine();

        flag = false;

        if (line.equals("high") || line.equals("medium") || line.equals("low") ){
            flag = true;
        }

        assertEquals(true, flag);


        line=br.readLine();
        String[] values = { "yellow", "red", "blue", "green", "gold", "grey", "brown", "purple"};
        boolean contains = false;

        for(int i = 0;i<values.length;i++){
            if(line.equals(values[i])){
                contains = true;
                break;
            }

        }

        assertTrue(contains);


        line=br.readLine();
        String[] valuesKeys = { "UP KEY","DOWN KEY","LEFT KEY","RIGHT KEY","Num8","Num6", "Num2","Num4"};
        contains = false;

        for(int i = 0;i<valuesKeys.length;i++){
            if(line.equals(valuesKeys[i])){
                contains = true;
                break;
            }

        }

        assertTrue(contains);

        line=br.readLine();
        contains = false;

        for(int i = 0;i<valuesKeys.length;i++){
            if(line.equals(valuesKeys[i])){
                contains = true;
                break;
            }

        }

        assertTrue(contains);

        line=br.readLine();
        contains = false;

        for(int i = 0;i<valuesKeys.length;i++){
            if(line.equals(valuesKeys[i])){
                contains = true;
                break;
            }

        }

        assertTrue(contains);

        line=br.readLine();
        contains = false;

        for(int i = 0;i<valuesKeys.length;i++){
            if(line.equals(valuesKeys[i])){
                contains = true;
                break;
            }

        }

        assertTrue(contains);
    }
}
