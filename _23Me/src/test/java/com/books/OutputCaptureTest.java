package com.books;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class OutputCaptureTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    public void captureOut() {
        System.setOut( new PrintStream( outContent ) );
    }

    private void captureErr() {
        System.setErr( new PrintStream( errContent ) );
    }

    public String getOut() {
        System.setOut( new PrintStream( new FileOutputStream( FileDescriptor.out ) ) );
        return outContent.toString().replaceAll( "\r", "" );

    }

    private String getErr() {
        System.setErr( new PrintStream( new FileOutputStream( FileDescriptor.out ) ) );
        return errContent.toString().replaceAll( "\r", "" );
    }



}
