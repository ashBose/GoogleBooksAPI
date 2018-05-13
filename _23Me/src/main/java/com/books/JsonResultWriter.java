package com.books;

import au.com.bytecode.opencsv.CSVWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class JsonResultWriter  {
    final static Logger logger = Logger.getLogger(JsonResultWriter.class);
    private Writer writer = null;

    public JsonResultWriter(String fileName) throws IOException {
        try {
            Writer writer = new FileWriter(fileName);
        } catch (IOException e) {
            throw new IOException("unable to open file");
        }
    }

    public void write(List<String> shelf) throws IOException {
        Gson gson = new GsonBuilder().create();
        gson.toJson(shelf, writer);
    }

}
