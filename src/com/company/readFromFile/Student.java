package com.company.readFromFile;

public class Student {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final String city;

    public Student (String firstName, String lastName, int age, String city) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.city=city;
    }

    public static Student parse(String value) {
        String [] splits = value.split(";");

        return new Student(splits[0], splits[1], Integer.getInteger(splits[2]), splits[3]);
    }

    @Override
    public String toString () {
        return "Student{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", age=" + age + ", city='" + city + '\'' + '}';
    }
}
