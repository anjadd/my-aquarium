package com.annjad.myaquarium

/**
 * Higher order functions are functions that either return another function or
 * that take functions as their parameter values.
 *
 * This is a higher order function that takes a list of programming languages as Strings,
 * filters them by some pattern and prints the Strings that match the filter pattern criteria.
 * It takes function as one of its parameters, with these parameters:
 * - list of Strings (which should be filtered)
 * - function that will take in a String and return a Boolean, based on whether or not
 *   that String matches the filter pattern criteria (e.g. the String should start with 'J').*/
fun printFilteredStrings(list: List<String>, filterPatternFunction: (String) -> Boolean) {

    /** Iterate the list, check if each String matches the criteria by passing it to the
     * filterPatternFunction(), which will return true if it does and then print that String*/
    list.forEach {
        /**You can call the function parameter directly as if it was a regular function*/
        if (filterPatternFunction(it)) {
            println(it)
        }
    }
}

fun printFruits(list: List<String>, myPattern: (String) -> Boolean) {
    println("The fruits that match the filter pattern criteria are:")
    list.forEach {
        if (myPattern(it)) {
            println(it)
        }
    }
}

/**To make the function parameter of a nullable type, wrap it in parentheses and add a '?'
 * Then you will have to use explicit ?.invoke() to make a function call instead, because
 * the reference has a nullable type, meaning you cannot invoke that function parameter
 * by using the parentheses only if it’s a nullable type.
 * Then you will have to use the safe call operator (== true or !!):
 * if (myPattern(it)) <=> if (myPattern?.invoke(it) == true) or
 * if (myPattern(it)) <=> if (myPattern?.invoke(it) !!) */
fun printFruitsWithNullableType(list: List<String>, myPattern: ((String) -> Boolean)?) {
    println("The fruits that match the filter pattern criteria are:")
    list.forEach {
        if (myPattern?.invoke(it) == true) {
            println(it)
        }
    }
}


/**You can also define a function parameter as variable of a functional type.
 * The syntax is: val/var followed by the name of the function, then the functional type
 * (“(input_type) -> return_type = {function_body}”). Then you can pass that variable when
 * invoking your high order function in main(), instead of passing a lambda into it.*/
val myPattern: (String) -> Boolean = {
    it.startsWith("a")
}

fun printFruits2(list: List<String>, myPattern: (String) -> Boolean) {
    println("The fruits that match the filter pattern criteria are:")
    list.forEach {
        if (myPattern(it)) {
            println(it)
        }
    }
}

/**Defining a Function that returns other function.
 * It is defined like a normal function, the fun keyword followed by the name of the function
 * and its input params in parentheses, and then “:” followed by the return type of that function.
 * That return type is another function, which is represented by
 * its input params in parentheses, followed by “->” and its return type.
 *
 * In the below example, this function takes no parameters, but you can define its return type
 * to be a function which takes a String and returns a Boolean.
 * This function will return a lambda in which you’ll define the filter pattern.
 * Then you will invoke the high order function with a list of Strings and another function
 * that will return a function which then can be used as a filtering pattern */
fun getFilterPattern(): (String) -> Boolean {
    return {
        it.startsWith("a")
    }
}

fun getMyFilterPattern(): (String) -> Boolean {
    return {
        it.endsWith("e")
    }
}

fun main() {

    val languagesList =
        listOf("Kotlin", "Java", "Python", "Java Android", "Php", "Javascript", "jQuery")

    /**To pass a function as a parameter, specify a lambda expression (put it in curly braces {})
     * and add the filter pattern*/

    println("Programming languages that start with J")
    printFilteredStrings(languagesList) { it -> it.startsWith("J", true) }

    println("Programming languages that start with P")
    printFilteredStrings(languagesList) { it -> it.startsWith("P", true) }

    println("========================================================")

    val fruits = listOf("apple", "banana", "kiwi", "cherry", "apricot", "orange")
    printFruits(fruits) { it.startsWith("A", true) }

    printFruitsWithNullableType(fruits, null)

    /** Pass a function as a parameter declared as a variable*/
    printFruits2(fruits, myPattern)

    printFruits(fruits, getFilterPattern())

    printFruits(fruits, getMyFilterPattern())

    println("========================================================")

    /**You can chain multiple functions to create a high order one*/
    /**Use filterNotNull() to only filter through the not null elements */
    val fruits2 =
        listOf("apple", "banana", "kiwi", "cherry", "apricot", "orange", "blueberry", null, null)
    println("Fruits starting with 'A':")
    fruits2
        .filterNotNull()
        .filter { it.startsWith("a") }
        .forEach { println(it) }

    /**The map() function takes in whatever the previous type is, and it allows you to convert
     * it to/return any other type you want
     * Use map() to change the type of each element, for example mapping the Strings which
     * represent each fruit, to it.length (Int) which represent the filtered fruits' lengths.
     * With map() the Strings are converted to Ints, and the forEach() is looping through Ints as well
     */
    println("Fruits length ending with 'Y':")
    fruits2
        .filterNotNull()
        .filter { it.endsWith("y") }
        .map { it.length }
        .forEach { it -> println(it) }

    /** take() is used when you want to take only a certain number of elements from a list.*/
    println("First 3 elements from fruits are:")
    fruits2
        .filterNotNull()
        .take(3)
        .forEach { println(it) }

    /** takeLast() is used when you want to take only a certain number of elements from the end of a list.*/
    println("Last 3 elements from fruits are:")
    fruits2
        .filterNotNull()
        .takeLast(3)
        .forEach { println(it) }


    /**The function associate() is used when you want to do a transformation, that will
     * associate the input values with some other value to return a map. The syntax is:
     * associate(element to element.some_property), like: fruits.associate { it to it.length },
     * where the result is a map in which the key is the name of the fruit, and the value is
     * the fruit’s length.*/
    fruits2
        .filterNotNull()
        .associate { it to it.length }
        .forEach { println("Fruit: ${it.key}, word length: ${it.value}") }

    /**You should use the function associateWith() instead of the associate() function. It is used
     * for the same purpose, when you want to do a transformation, that will associate the
     * input values with some other value to return a map. The syntax is:
     * associateWith(element.some_property), like: fruits.associateWith { it.length },
     * where the result is a map in which the key is the name of the fruit, and the value is
     * the fruit’s length.*/
    fruits2
        .filterNotNull()
        .associateWith { it.length }
        .forEach { println("Fruit: ${it.key}, word length: ${it.value}") }


    /**In order to save the map in a variable, just map the list of strings to a Map with String
     * as the key (fruit name) and Integer as the value (fruit’s name length) using the
     * associateWith() function and assign it to a variable instead of using the
     * forEach() for iterating*/
    val mapOfFruits = fruits2
        .filterNotNull()
        .associateWith { it.length }

    println("Map of fruits: $mapOfFruits")

    /**Use the function first() to get the first element from a list and
     * last() to get the last element from a list*/
    val firstFruit = fruits2.filterNotNull().first()
    val lastFruit = fruits2.last()
    val lastNotNullFruit = fruits2.filterNotNull().last()

    println("First fruit: $firstFruit, last fruit: $lastFruit, last not null fruit: $lastNotNullFruit")


    /**The find() function is used to find only the first list element that matches some pattern,
     * by using a lambda expression. The findLast() function is used to find only the last list
     * element that matches some pattern, by using a lambda expression.
     * To find all elements that match the search criteria, use the filter function()*/
    val firstFruitStartingWithA = fruits2.filterNotNull().find { it.startsWith("a") }
    println("First fruit starting with A: $firstFruitStartingWithA")

    val lastFruitStartingWithA = fruits2.filterNotNull().findLast { it.startsWith("a") }
    println("Last fruit starting with A: $lastFruitStartingWithA")


    /**If none of the elements match the criteria using the find() function, the result will
     * be null (output: First fruit starting with Rasp: null). So if you want to avoid printing
     * null in the console, and replacing it with an empty String, use the orEmpty() function
     * at the end of your find() search.*/
    val fruitStartingWithRasp = fruits2.filterNotNull().find { it.startsWith("rasp") }
    println("First fruit starting with Rasp: $fruitStartingWithRasp")

    val fruitStartingWithRaspOrEmpty = fruits2.filterNotNull()
        .find { it.startsWith("rasp") }.orEmpty()
    println("First fruit starting with Rasp without showing a null value: $fruitStartingWithRaspOrEmpty")

}
