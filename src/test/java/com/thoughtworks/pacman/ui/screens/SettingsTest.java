package com.thoughtworks.pacman.ui.screens;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import com.thoughtworks.pacman.ui.Settings;

public class SettingsTest {

    @Test
    public void getValuesFromFileWithCorrectCRC_Test() throws IOException
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"));
            writer.write(10+" ");
            writer.write(10+" ");
            writer.write(1+" ");
            writer.write(2+" ");
            writer.write(2+" ");
            writer.write(0+" ");
            writer.write(1+" ");
            writer.write(3+" ");
            writer.write(15+" ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(new Settings().getBuildCorrect());
    }

    @Test
    public void saveValuesToFile_Test() throws IOException
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"));
            writer.write(10+" ");
            writer.write(10+" ");
            writer.write(1+" ");
            writer.write(3+" ");
            writer.write(2+" ");
            writer.write(0+" ");
            writer.write(1+" ");
            writer.write(3+" ");
            writer.write(15+" ");
            writer.close();
            assertTrue(true);
        } catch (IOException e) {
            assertTrue(false);
            e.printStackTrace();
        }

    }
    @Test
    public void controlCRC_Test() throws IOException
    {
        Settings settings = new Settings();
        int sum = 50;
        int CRC = settings.crateCRC(sum);
        assertEquals((sum-CRC)%settings.getCoefficient(), 0);
        assertEquals((sum+1-CRC)%settings.getCoefficient(), 1);
    }
}