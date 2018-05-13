package com.books;

import com.google.gson.JsonObject;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TestPersistence {

    @Test
    public void testPersistenceResult() throws Exception {

        try {
            String[] args = new String[]{"-q", "java", "-b",
                    "Java Web Development Illuminated",
                    "-s", "published_date"};
            Map<String, String> argMap = new ProcessArg(args).parse();
            RetriveBooks rb = new RetriveBooks();
            Utility.setTempFile(argMap.get("query"));
            Files.deleteIfExists(Paths.get(Utility.getTempFile()));
            List<String> shelf = rb.doExtractBooks(argMap);
            new JsonResultWriter(Utility.getTempFile()).write(shelf);

            //check file exists
            File f = new File(Utility.getTempFile());
            assertTrue(f.exists());
            assertTrue(f.canRead());
            assertEquals(f.getAbsolutePath(), Utility.getTempFile());

            //read the file
            List<String> persistShelf = new JsonResultReader(Utility.
                    getTempFile()).
                    getVirtualShelf();
            assertTrue(persistShelf.size() == 1);
            JsonObject jsonObj = TestUtility.getJsonObject(persistShelf.get(0));
            assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                            getAsString(),
                    "Java Web Development Illuminated");

            //take backup of persisted file and check if it works

            String newFile = "/tmp/java_bkup.json";
            File fnew = new File(newFile);
            f.renameTo(fnew);

            fnew.renameTo(f);
            persistShelf = new JsonResultReader(Utility.
                    getTempFile()).
                    getVirtualShelf();
            assertTrue(persistShelf.size() == 1);
            jsonObj = TestUtility.getJsonObject(persistShelf.get(0));
            assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                            getAsString(),
                    "Java Web Development Illuminated");

        } catch(Exception e) {
            fail();
        }

    }

    @Test
    public void testPersistenceMultiResult() throws Exception {

        try {
            String[] args = new String[]{"-q", "python","-b",
                    "Programming in Python 3, Fluent Python, Pythons, Learning Python" ,
                    "-s", "published_date"};
            Map<String, String> argMap = new ProcessArg(args).parse();
            RetriveBooks rb = new RetriveBooks();
            Utility.setTempFile(argMap.get("query"));
            Files.deleteIfExists(Paths.get(Utility.getTempFile()));
            List<String> shelf = rb.doExtractBooks(argMap);
            new JsonResultWriter(Utility.getTempFile()).write(shelf);

            //check file exists
            File f = new File(Utility.getTempFile());
            assertTrue(f.exists());
            assertTrue(f.canRead());
            assertEquals(f.getAbsolutePath(), Utility.getTempFile());

            //read the file
            List<String> persistShelf = new JsonResultReader(Utility.
                    getTempFile()).
                    getVirtualShelf();
            assertTrue(persistShelf.size() == 4);
            JsonObject jsonObj = TestUtility.getJsonObject(persistShelf.get(0));
            assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                            getAsString(),
                    "Pythons");

            //take backup of persisted file and check if it works

            String newFile = "/tmp/java_bkup.json";
            File fnew = new File(newFile);
            f.renameTo(fnew);

            fnew.renameTo(f);
            persistShelf = new JsonResultReader(Utility.
                    getTempFile()).
                    getVirtualShelf();
            assertTrue(persistShelf.size() == 4);
            jsonObj = TestUtility.getJsonObject(persistShelf.get(0));
            assertEquals(jsonObj.getAsJsonObject("volumeInfo").get("title").
                            getAsString(),
                    "Pythons");

        } catch(Exception e) {
            fail();
        }

    }
}
