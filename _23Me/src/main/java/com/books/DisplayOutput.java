package com.books;

import au.com.bytecode.opencsv.CSVReader;
import com.google.api.services.books.model.Volume;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisplayOutput {
    public void display(List<String> virtualShelf) throws IOException {
        for(String value: virtualShelf) {
            System.out.println(value);
        }
    }
}
