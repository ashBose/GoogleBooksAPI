package com.books;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JsonResultReader {
    private List<String> bookShelf;
    private JsonReader reader = null;

    JsonResultReader(String fileName) throws IOException {
        try{
            reader = new JsonReader(new FileReader(fileName));
        } catch (IOException e) {
            throw new IOException("unable to open file");
        }
    }

    public List<String> getVirtualShelf() throws IOException{
        Gson gson = new GsonBuilder().create();
        bookShelf = gson.fromJson(reader, List.class);
        return bookShelf;
    }

}
