package com.books;

import static org.junit.Assert.*;
import org.junit.Test;
import org.apache.commons.cli.ParseException;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Map;

public class TestArg {

    @Test
    public void testNullArg() throws ParseException {
        String[] args = new String[]{};
        ProcessArg pg = new ProcessArg(args);
        try {
            pg.parse();
            fail();
        } catch(ParseException e) {
            assertEquals(e.getMessage(), "No argument present");
        }
    }

    @Test
    public void testQueryIllegalArg() throws ParseException{
        String[] args = new String[]{"java","-b",
                "Head First Java, Java Web Development Illuminated" ,
                "-s", "published_date"};
        ProcessArg pg = new ProcessArg(args);
        try {
            pg.parse();
            fail();
        } catch(ParseException e) {
            assertEquals(e.getMessage(), "query parameter not present");
        }
    }

    @Test
    public void testQueryArg() throws ParseException{
        String[] args = new String[]{"-b",
                "Head First Java, Java Web Development Illuminated" ,
                "-s", "published_date"};
        ProcessArg pg = new ProcessArg(args);
        try {
            pg.parse();
            fail();
        } catch(ParseException e) {
            assertEquals(e.getMessage(), "query parameter not present");
        }
    }

    @Test
    public void testHelpArg() throws ParseException{
        String[] args = new String[]{"-h"};
        ProcessArg pg = new ProcessArg(args);
        try {
            pg.parse();
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut( new PrintStream( new FileOutputStream(
                    FileDescriptor.out ) ) );
        } catch(ParseException e) {
            fail();
        }
    }

    @Test
    public void testInvalidSortArg() throws ParseException {
        String[] args = new String[]{"-q", "java","-b",
                "Head First Java, Java Web Development Illuminated" ,
                "-s", "published_dates"};
        ProcessArg pg = new ProcessArg(args);
        try {
            pg.parse();
            fail();
        } catch(ParseException e) {

            assertEquals(e.getMessage(), "sort parameter not present");
        }
    }

    @Test
    public void testSortArg() throws ParseException {
        String[] args = new String[]{"-q", "java","-b",
                "Head First Java, Java Web Development Illuminated"};
        ProcessArg pg = new ProcessArg(args);
        try {
            pg.parse();
            fail();
        } catch(ParseException e) {
            assertEquals(e.getMessage(), "sort parameter not present");
        }
    }

    @Test
    public void testBookArg() throws ParseException {
        String[] args = new String[]{"-q", "java",
                "-s", "published_date"};
        ProcessArg pg = new ProcessArg(args);
        try {
            pg.parse();
            fail();
        } catch(ParseException e) {

            assertEquals(e.getMessage(), "query parameter not present");
        }
    }

    @Test
    public void testAllArg() throws ParseException {
        String[] args = new String[]{"-q", "java","-b",
                "Head First Java, Java Web Development Illuminated" ,
                "-s", "published_date"};
        ProcessArg pg = new ProcessArg(args);

        try {
            pg.parse();
            Map<String, String> argMap = pg.parse();
            assertEquals(argMap.get("query"), "java");
            assertEquals(argMap.get("sort"), "published_date");
            String[]tmp =  argMap.get("book").split(",");
            assertTrue(tmp.length == 2);
            assertEquals(tmp[0], "Head First Java");
        } catch(ParseException e) {
            fail();
        }
    }
}


