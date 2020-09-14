package com.annjad.myaquarium

class Person(val firstName: String = "Peter", val lastName: String = "Parker") {

    var nickname: String? = null

    var age: Int? = null
/*
    //Overriding the getter and setter for the nickname property

        set(value) {
            //To actually assign the new value to your nickname property you need to use
            // the special keyword field. Without this, the nickname would never be updated.
            field = value
            println("Setting up the new nickname $value")
        }
        get() {
            //The field variable is where the actual value of nickname is stored
            println("Getting the nickname $field")
            return field
        }*/

    /**You can add a secondary constructor, but in Kotlin you typically don’t need a second one.
     * A good structured class will only have one constructor with default parameters for the
     * optional values. But if you decide to declare a secondary constructor, it must contain a
     * call to the primary one by using “this” and you have to pass any required arguments*/
    constructor() : this("Lara", "Croft") {}

    fun printPersonInfo() {
        val nicknameToPrint: String = nickname ?: ""
        val ageToPrint = age?.toString() ?: "unknown"
        println("Person details: $firstName $nicknameToPrint $lastName, age: $ageToPrint")
    }
}

/**You can replace a second constructor with a helper method like the one below*/
fun makeDefaultPerson() = Person("Lara", "Croft")

class Person2 {
    val firstName = "Clarke"
    val lastName = "Griffin"

    fun printPersonInfo() {
        println("Person details: $firstName $lastName")
    }
}