package com.books;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AppEntryMain {


    public static void main(String[] args)  throws Exception{
        Map<String, String> argMap = new ProcessArg(args).parse();
        RetriveBooks rb = new RetriveBooks();
        List<String> shelf = rb.doExtractBooks(argMap);
        Utility.setTempFile(argMap.get("query"));
        new JsonResultWriter(Utility.getTempFile()).write(shelf);
        List<String> persistShelf = new JsonResultReader(Utility.getTempFile()).
                getVirtualShelf();
        new DisplayOutput().display(persistShelf);
    }
}
