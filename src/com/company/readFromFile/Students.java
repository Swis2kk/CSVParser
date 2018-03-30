package com.company.readFromFile;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Students implements Iterable<Student> {

    private final File file;

    Students (File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist.");
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("This is not file.");
        }
        this.file=file;
    }

    public void add(Student student) {

    }

    @Override
    public Iterator<Student> iterator () {
        return new FileIterator();
    }

    private class FileIterator implements Iterator<Student> {

        private BufferedReader bufferedReader;

        FileIterator () {
            try {
                bufferedReader=new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new UncheckedIOException(e);
            }
        }

        private String nextLine=null;

        @Override
        public boolean hasNext () {
            if (nextLine != null) {
                return true;
            } else {
                try {
                    nextLine=bufferedReader.readLine();
                    return nextLine != null;
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        }

        @Override
        public Student next () throws NoSuchElementException {
            if (!hasNext() && nextLine == null) {
                throw new NoSuchElementException();
            } else {
                String line=nextLine;
                nextLine=null;
                return Student.parse(line);
            }
        }
    }
}
