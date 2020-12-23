package com.thoughtworks.pacman.core;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class FileIOTest {

    @Test
    public void EmptyFileLoadTest() throws Exception {
        ArrayList<ScoreEntry> testList;
        testList = FileIO.load();
        assertEquals(0, testList.size());
    }

    @Test
    public void getFilePathTest() throws Exception {
        FileIO.setFileName("score.txt");
        String path = FileIO.getPath();
        assertTrue(path.length() > 0);
    }

    @Test
    public void emptyScoreBoardTextTest() throws Exception {
        String path = FileIO.getPath();
        File testFile = new File(path);
        String Text = FileIO.getScoreBoard();
        assertEquals(55, Text.length());
    }
}