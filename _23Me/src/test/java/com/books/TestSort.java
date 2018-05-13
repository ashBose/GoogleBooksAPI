package com.books;

import com.google.gson.JsonObject;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
price, avg rating, rating count, published date, or page count
 */
public class TestSort {

    @Test
    public void testAvgRating() throws Exception{
        String[] args = new String[]{"-q", "python","-b",
                "Programming in Python 3, Fluent Python, Pythons, Learning Python" ,
                "-s", "avg_rating"};
        ProcessArg pg = new ProcessArg(args);
        RetriveBooks rb = new RetriveBooks();
        List<String> shelf = rb.doExtractBooks(pg.parse());
        assertEquals(shelf.size(), 4);

        JsonObject jsonObj = TestUtility.getJsonObject(shelf.get(0));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                getAsString(), "Learning Python");
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("averageRating").
                getAsDouble(), 3.5, 0.0);

        jsonObj = TestUtility.getJsonObject(shelf.get(1));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                getAsString(), "Fluent Python");
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("averageRating").
                getAsDouble(), 5.0, 0.0);
    }

    @Test
    public void testPageCount() throws Exception{
        String[] args = new String[]{"-q", "python","-b",
                "Programming in Python 3, Fluent Python, Pythons, Learning Python" ,
                "-s", "page_count"};
        ProcessArg pg = new ProcessArg(args);
        RetriveBooks rb = new RetriveBooks();
        List<String> shelf = rb.doExtractBooks(pg.parse());
        assertEquals(shelf.size(), 4);

        JsonObject jsonObj = TestUtility.getJsonObject(shelf.get(0));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                getAsString(), "Pythons");
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("pageCount").
                getAsInt(), 96);

        jsonObj = TestUtility.getJsonObject(shelf.get(1));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                getAsString(), "Programming in Python 3");
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("pageCount").
                getAsInt(), 630);
        jsonObj = TestUtility.getJsonObject(shelf.get(2));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                getAsString(), "Fluent Python");
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("pageCount").
                getAsInt(), 792);
    }

    @Test
    public void testPublishedDate() throws Exception {
        String[] args = new String[]{"-q", "python","-b",
                "Programming in Python 3, Fluent Python, Pythons, Learning Python" ,
                "-s", "published_date"};
        ProcessArg pg = new ProcessArg(args);
        RetriveBooks rb = new RetriveBooks();
        List<String> shelf = rb.doExtractBooks(pg.parse());
        assertEquals(shelf.size(), 4);

        JsonObject jsonObj = TestUtility.getJsonObject(shelf.get(0));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("publishedDate").
                        getAsString(), "2009");
        jsonObj = TestUtility.getJsonObject(shelf.get(1));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("publishedDate").
                getAsString(), "2010");
        jsonObj = TestUtility.getJsonObject(shelf.get(2));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("publishedDate").
                getAsString(), "2013-06-12");
        jsonObj = TestUtility.getJsonObject(shelf.get(3));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("publishedDate").
                getAsString(), "2015-07-30");
    }

    @Test
    public void testRatingCount() throws  Exception{
        String[] args = new String[]{"-q", "python","-b",
                "Programming in Python 3, Fluent Python, Pythons, Learning Python" ,
                "-s", "rating_count"};
        ProcessArg pg = new ProcessArg(args);
        RetriveBooks rb = new RetriveBooks();
        List<String> shelf = rb.doExtractBooks(pg.parse());
        assertEquals(shelf.size(), 4);

        JsonObject jsonObj = TestUtility.getJsonObject(shelf.get(0));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                getAsString(), "Fluent Python");
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("ratingsCount").
                getAsInt(), 2);

        jsonObj = TestUtility.getJsonObject(shelf.get(1));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                getAsString(), "Learning Python");
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("ratingsCount").
                getAsInt(), 11);
    }

    @Test
    public void testPrice() throws Exception{
        String[] args = new String[]{"-q", "python","-b",
                "Programming in Python 3, Fluent Python, Pythons, Learning Python" ,
                "-s", "retail_price"};
        ProcessArg pg = new ProcessArg(args);
        RetriveBooks rb = new RetriveBooks();
        List<String> shelf = rb.doExtractBooks(pg.parse());
        assertEquals(shelf.size(), 4);

        JsonObject jsonObj = TestUtility.getJsonObject(shelf.get(0));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                getAsString(), "Fluent Python");
        assertEquals(jsonObj.getAsJsonObject("saleInfo").
                        getAsJsonObject("retailPrice").
                        get("amount").getAsDouble(),31.81, 0.0);

        jsonObj = TestUtility.getJsonObject(shelf.get(1));
        assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                getAsString(), "Learning Python");
        assertEquals(jsonObj.getAsJsonObject("saleInfo").
                getAsJsonObject("retailPrice").
                get("amount").getAsDouble(),37.67, 0.0);
    }

}
