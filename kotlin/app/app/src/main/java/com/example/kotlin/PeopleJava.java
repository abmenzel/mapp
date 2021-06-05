package com.example.kotlin;

import java.util.ArrayList;
import java.util.List;

public class PeopleJava {
    public static void main(String[] args) {
        PeopleJava people = new PeopleJava();
    }

    public List<Person> people = new ArrayList<>();

    // 2. Let's say we add some people, one of them has a hobby of null.
    //    if we try to access that hobby, we get a null pointer exception.
    //    this is fairly easy to tell by this example, but can be a big problem in complex software.
    public PeopleJava(){
        people.add(new Person("Michael", 22, "Shopping"));
        people.add(new Person("Dennis", 39, "Surfing"));
        people.add(new Person("Sofus", 42, null));

        for(Person person : people){
            System.out.println(person.hobby.toUpperCase());
        }
    }

    // 1. Let's say we have a simple class for a person
    //    A person has a name, an age, and might have a hobby - We can't easily enforce this in java.
    private class Person {
        String name;
        int age;
        String hobby;

        public Person(String name, int age, String hobby){
            this.name = name;
            this.age = age;
            this.hobby = hobby;
        }
    }
}
