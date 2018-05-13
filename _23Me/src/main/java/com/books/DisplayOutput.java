package com.books;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.List;

public class DisplayOutput {
    final static Logger logger = Logger.getLogger(DisplayOutput.class);
    public void display(List<String> virtualShelf) throws IOException {
        //logger.info("Displaying the Output");
        for(String value: virtualShelf) {
            System.out.println(value);
        }
    }
}
