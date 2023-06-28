package com.epam.mjc.io;

import java.io.FileNotFoundException;

public class FileNotExistException extends FileNotFoundException {
    public FileNotExistException(){
        super("Could not find file");
    }
}
