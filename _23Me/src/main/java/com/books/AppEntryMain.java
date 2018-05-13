package com.books;

import java.util.List;
import java.util.Map;

public class AppEntryMain {

    static String tempFile = null;
    public static String getTempFile(){
        return tempFile;
    }
    public static void setTempFile(String query) {
        tempFile = "/tmp/" + query + ".json";
    }

    public static void main(String[] args)  throws Exception{
        Map<String, String> argMap = new ProcessArg(args).parse();
        RetriveBooks rb = new RetriveBooks();
        List<String> shelf = rb.doExtractBooks(argMap);
        setTempFile(argMap.get("query"));
        new JsonResultWriter(getTempFile()).write(shelf);
        new DisplayOutput().display(shelf);
    }
}
