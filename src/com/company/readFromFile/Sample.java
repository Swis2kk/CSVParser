package com.company.readFromFile;

import java.io.File;
import java.io.IOException;

public class Sample {

    public static void main (String[] args) {

        String pathToFile="C:/Example/text.txt";
        File file=new File(pathToFile);

        new Students(file).forEach(System.out::println);
    }
}
