package com.company.readFromFile;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Students {

    private final File file;

    Students (File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist.");
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("This is not file.");
        }

        this.file = file;
    }

    public void forEach (Consumer<Student> consumer) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            //reader.lines().
            for (FileIterator iterator=new FileIterator(reader); iterator.hasNext();) {
                consumer.accept(iterator.next());
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private class FileIterator implements Iterator<Student> {

        private final BufferedReader reader;
        private String nextLine=null;

        public FileIterator (BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public boolean hasNext () {
            if (nextLine != null) {
                return true;
            } else {
                try {
                    nextLine=reader.readLine();
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
