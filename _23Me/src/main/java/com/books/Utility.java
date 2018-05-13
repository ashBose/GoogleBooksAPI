package com.books;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

    static String tempFile = null;
    public static String getTempFile(){
        return tempFile;
    }
    public static void setTempFile(String query) {

        String date = new SimpleDateFormat("yyyy_MM_dd").
                format(new Date());
        tempFile = "/tmp/" + query + "_"+ date +".json";
    }

}
