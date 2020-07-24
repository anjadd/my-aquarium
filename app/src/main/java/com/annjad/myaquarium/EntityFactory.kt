package com.annjad.myaquarium

import java.util.*


class Entity private constructor(val id: String) {

    /**
     * If you want to create an instance of a class that has a private constructor,
     * use a companion object, which is an object scoped to an instance of another class
     * In that object create a function that will return an instance of that class */
    companion object Factory {
        //Function with a hardcoded id
//        fun create() = Entity("id")

        //You can also store properties within the companion object
        const val id = "id"

        fun create() = Entity(id)
    }
}


interface IdProvider {
    fun getId(): String
}

class EntityWithCompObjInterface private constructor(val id: String) {

    companion object : IdProvider {

        override fun getId(): String {
            return "123"
        }

        fun create() = EntityWithCompObjInterface(getId())
    }
}

/** Object Declaration is a convenient way of creating thread save singletons within Kotlin.
 * To do it, use the object keyword, followed by the class name. Within this object,
 * you can add any types of properties or methods that you need. */
object EntityFactory {
    fun create() = EntityObjDeclaration("123", "User123")
}

class EntityObjDeclaration constructor(val id: String, val name: String) {

    /*
    //Delete the companion object and transfer the create() to the EntityFactory object

    companion object Factory {
        fun create() = EntityObjectDeclaration("id")
    }*/

    //Override toString() so if you print out an instance of this class, you'll get nice readable text
    override fun toString(): String {
        return "id: $id, name: $name"
    }
}

enum class Entity2Type {
    EASY, MEDIUM, HARD;

    // to format the enum names, so they are not printed in all capital letters, instead
    // only first capital letter and the following are small
    fun getFormatedEnumName(): String = name.toLowerCase().capitalize()
}

object EntityFactory2 {
    fun create(type: Entity2Type): Entity2 {
        //Use the UUID lib to generate a random id
        val id = UUID.randomUUID().toString()
        val name = when (type) {
            Entity2Type.EASY -> "Easy"
            Entity2Type.MEDIUM -> "Medium"
            Entity2Type.HARD -> "Hard"
        }
        //Or if you want the name to be just as it is in the enum class use enum.name
        val name2 = when (type) {
            Entity2Type.EASY -> type.name
            Entity2Type.MEDIUM -> type.name
            Entity2Type.HARD -> type.name
        }
        //Or if you want the name to be even more readable
        val name3 = when (type) {
            Entity2Type.EASY -> type.getFormatedEnumName()
            Entity2Type.MEDIUM -> type.getFormatedEnumName()
            Entity2Type.HARD -> type.getFormatedEnumName()
        }
        return Entity2(id, name3)
    }
}

class Entity2 constructor(val id: String, val name: String) {
    override fun toString(): String {
        return "id: $id, name: $name"
    }
}


sealed class Color {
    class Red : Color()
    class Yellow : Color()
    class Blue : Color()
    class Pink : Color()
}

fun paintWalls(color: Color) = when (color) {
    is Color.Red -> println("Paint the walls in red")
    is Color.Yellow -> println("Paint the walls in yellow")
    is Color.Blue -> println("Paint the walls in blue")
    is Color.Pink -> println("Paint the walls in pink")
}


/** All classes extend from the sealed class but have different types of properties,
 * which is the main difference between sealed and enum classes.*/
sealed class GameLevels {
    data class Easy(val id: String, val name: String) : GameLevels()
    data class Medium(val id: String, val name: String) : GameLevels()
    data class Hard(val id: String, val name: String, val difficultyMultiplier: Float) :
        GameLevels()
}


/**Data classes are classes whose main purpose is to hold data. They generate methods such as:
 * the pair equals() and hashCode(), toString(), copy() automatically for you. With this you can
 * perform an equality comparisons on instances of these data classes.*/
data class User(val name: String = "", val age: Int = 0) {
    fun printUserInfo() = "Name: $name, age: $age"
}

/**
 * You have to define this function outside of the data class, because data classes are final
 * and closed, so you won't be able to call this function from main()*/
fun checkIfUsersAreEqual(user1: User, user2: User) {
    println("First user: ${user1.printUserInfo()}; Second user: ${user2.printUserInfo()}")
    println("Users are ${if (user1 == user2) "equal" else "not equal"}")
}


/**
 * You can add another function outside of the User data class, same as the above printUserInfo()
 * function, which will be an extension function to the User class. With this you're adding a
 * new method to your User class, without actually defining that method inside the body of User class.*/
fun User.extensionFunPrintInfo() {
    println("User details: Name: $name, age: $age")
}

/**
 * You can add a property outside of the User data class, which will be an extension property to
 * the User class. With this you're adding a new property to your User class, without actually
 * defining it inside the body of User class. But you have to initialize it through a getter*/
val User.extentionPropertyInfo: String
    get() = "Some user info"

/**
 * Referential equality operator (‘===’) is used to compare the reference (address location) of
 * two variables or objects. It will only be true if both the objects or variables are
 * pointing to the same object. */
fun checkIfUsersAreReferentiallyEqual(user1: User, user2: User) {
    println("First user: ${user1.printUserInfo()}; Second user: ${user2.printUserInfo()}")
    println("Users are referentially ${if (user1 === user2) "equal" else "not equal"}")
}


class Person3(val firstName: String = "Peter", val lastName: String = "Parker", val age: Int?) {
    fun printPersonInfo() {
        val ageToPrint = age?.toString() ?: "unknown"
        println("Person details: $firstName $lastName, age: $ageToPrint")
    }
}

fun main() {
    /**
     * If you want to create an instance of a class that has a private constructor,
     * it will throw an error because of the private constructor in that class:
     * (Cannot access <init>: it is private in Entity)
     *
     * val entity = Entity("someId")   //ERROR!
     */

    //The reference to Companion/Factory is redundant
    val entity = Entity.Factory.create()

    //Reference the property within the companion object as if it was a static property
    Entity.id

    val entityy = Entity.create()

    val entityCompInt = EntityWithCompObjInterface.create()
    println("Entity with Companion object implementing an Interface, id: ${entityCompInt.id}")

    val entObjDec = EntityFactory.create()
    println(entObjDec)

    val easyEntity2 = EntityFactory2.create(Entity2Type.EASY)
    println(easyEntity2)

    val mediumEntity2 = EntityFactory2.create(Entity2Type.MEDIUM)
    println(mediumEntity2)

    paintWalls(Color.Blue())

    val user1 = User("John", 32)
    val user2 = User("Randal", 26)
    val user3 = User("John", 32)
    val user4 = User("Randal", 27)
    //create exact copies
    val user5 = user4.copy()
    //create copy of user4 but change the name to Becca
    val user6 = user4.copy(name = "Becca")

    val user7 = User("Shay", 33)
    //user8 is referencing to the user7 instance, and user7 === user8 is true
    val user8 = user7

    checkIfUsersAreEqual(user1, user3)
    checkIfUsersAreEqual(user2, user4)
    checkIfUsersAreEqual(user4, user5)
    checkIfUsersAreEqual(user4, user6)

    checkIfUsersAreReferentiallyEqual(user7, user8)

    println("==========")
    user7.extensionFunPrintInfo()
    println(user1.extentionPropertyInfo)

    Person3(age = null).printPersonInfo()
}