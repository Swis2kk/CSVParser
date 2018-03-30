package com.company.readFromFile;

import java.io.File;
import java.io.IOException;

public class Sample {

    public static void main (String[] args) throws IOException {

        String pathToFile="C:/Example/text.txt";
        File file=new File(pathToFile);

        //System.out.println(file.canRead());

        Students students = new Students(file);

        for (Student student : students) {
            System.out.println(student);
        }

    }
}
