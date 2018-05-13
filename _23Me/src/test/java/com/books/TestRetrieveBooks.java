package com.books;

import com.google.gson.JsonObject;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class TestRetrieveBooks {


    @Test
    public void testWithValidSearch() throws Exception {
        String[] args = new String[]{"-q", "java","-b",
                "Java Web Development Illuminated" ,
                "-s", "published_date"};
        ProcessArg pg = new ProcessArg(args);
        RetriveBooks rb = new RetriveBooks();
        List<String> shelf = rb.doExtractBooks(pg.parse());
        assertEquals(shelf.size(), 1);
        JsonObject jsonObj = TestUtility.getJsonObject(shelf.get(0));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                        getAsString(),
                "Java Web Development Illuminated");
    }

    @Test
    public void testWithInValidSearch() throws Exception {
        String[] args = new String[]{"-q", "java88","-b",
                "Java Web Development Illuminated" ,
                "-s", "published_date"};
        ProcessArg pg = new ProcessArg(args);
        RetriveBooks rb = new RetriveBooks();
        List<String> shelf = rb.doExtractBooks(pg.parse());
        assertEquals(shelf.size(), 0);
    }

    @Test
    public void testWithCaseSensitiveSearch() throws Exception {
        String[] args = new String[]{"-q", "java","-b",
                "Java web development illuminated" ,
                "-s", "published_date"};
        ProcessArg pg = new ProcessArg(args);
        RetriveBooks rb = new RetriveBooks();
        List<String> shelf = rb.doExtractBooks(pg.parse());
        assertEquals(shelf.size(), 1);
        JsonObject jsonObj = TestUtility.getJsonObject(shelf.get(0));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                        getAsString(),
                "Java Web Development Illuminated");
    }

    @Test
    public void testWithMultiSearch() throws Exception {
        String[] args = new String[]{"-q", "python","-b",
                "Python Programming, Fluent Python" ,
                "-s", "published_date"};
        ProcessArg pg = new ProcessArg(args);
        RetriveBooks rb = new RetriveBooks();
        List<String> shelf = rb.doExtractBooks(pg.parse());
        assertEquals(shelf.size(), 2);
        JsonObject jsonObj = TestUtility.getJsonObject(shelf.get(0));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                        getAsString(),
                "Python Programming");
    }

    @Test
    public void testWithUnicodeSearch() throws Exception {
        String cc2 = "2202";
        String text2 = String.valueOf(Character.toChars(Integer.parseInt(cc2,
                16)));
        String[] args = new String[]{"-q", "java","-b",
                text2 ,
                "-s", "published_date"};
        ProcessArg pg = new ProcessArg(args);
        RetriveBooks rb = new RetriveBooks();
        List<String> shelf = rb.doExtractBooks(pg.parse());
        assertEquals(shelf.size(), 0);
    }


}
