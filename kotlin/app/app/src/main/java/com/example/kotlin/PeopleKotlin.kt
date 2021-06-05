package com.example.kotlin

class PeopleKotlin {
    var people = arrayListOf<Person>()

    // 2. When accesssing values that are null, kotlin enforce us to use safechecking.
    //    Thereby we make sure to think about null pointer cases, and how to handle them.
    init {
        people.add( Person("Michael", 22, "Shopping"))
        people.add( Person("Dennis", 39, "Surfing"))
        people.add( Person("Sofus", 42, null))

        for (person in people){
            println(person.hobby?.uppercase() ?: "NO HOBBY") // We must use a safecheck ?, and we can use Elvis operator
        }
    }

    // 1. Let's say we have a simple person class, a person has a name, an age and might have a hobby.
    //    We can declare this by using ? which allows the value to be nullable.
    inner class Person(_name : String, _age : Int, _hobby : String? ){
        val name = _name
        val age = _age
        val hobby = _hobby
    }
}