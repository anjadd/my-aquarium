package com.annjad.myaquarium

import java.lang.Math.random
import kotlin.random.Random

fun printFruitsNew(listFruits: List<String>, myPatternFunction: (String) -> Boolean) {
    listFruits.forEach {
        if (myPatternFunction(it)) {
            println(it)
        }
    }
}

val waterFilter: (Int) -> Int = { dirty -> dirty / 2 }

/**Create a lambda and assign it to rollDice, which returns a dice roll (number between 1 and 12).
 * Extend the lambda to take an argument indicating the number of sides of the dice used for
 * the roll. If you haven't done so, fix the lambda to return 0 if the number of sides
 * passed in is 0.*/
fun rollTheDice() {
    //Create a lambda and assign it to rollDice, which returns a dice roll (number between 1 and 12)
    //Random.nextInt() generates an `Int` random value uniformly distributed between the
    // specified [from] (inclusive) and [until] (exclusive) bounds.
    val rollDice1: () -> Int = { Random.nextInt(1, 12 + 1) }
    val rollDice1_2 = { Random.nextInt(12) + 1 }

    //Extend the lambda to take an argument indicating the number of sides of the dice used for the roll
    val rollDice: (Int) -> Int = { numberOfDiceSides: Int ->
        if (numberOfDiceSides == 0) {
            0
        } else if (numberOfDiceSides < 0 || numberOfDiceSides > 12) {
            0
        } else {
            Random.nextInt(numberOfDiceSides) + 1
        }
    }

    val sides = 12
    println("Roll the dice: Your dice has $sides sides.")
    //Because rollDice is a lambda, you have to call it like you would call a function: rollDice()
    println("Your random number between 1 and $sides is: ${rollDice(sides)}")
}

/** Create a function gamePlay() that takes a roll of the dice as an argument and prints
 * it out. Pass your rollDice function as an argument to gamePlay() to generate a dice
 * roll every time gamePlay() is called.*/
val rollDice: (Int) -> Int = { numberOfDiceSides: Int ->
    if (numberOfDiceSides == 0) {
        0
    } else if (numberOfDiceSides < 0 || numberOfDiceSides > 12) {
        0
    } else {
        Random.nextInt(numberOfDiceSides) + 1
    }
}

fun gamePlay(rollingDice: Int) {
    println("Rolling the dice. Your random number is: $rollingDice")
}


fun main() {

    /**A lambda is always surrounded by curly brackets.
     *
     * You can declare some variable (sum) and assign it to a lambda, then call it like a
     * normal function: sum(3,7) or you can call it inside println() to see the result
     *
     * In the curly brackets, first you put the input parameters,
     * followed by ->,
     * then the function body follows (if any, because a function does not have to have a body),
     * and the last expression in a lambda is the return value.
     * */
    val sum = { x: Int, y: Int -> x + y }

    sum(3, 7)

    println("The sum of 2 and 6 is: ${sum(2, 6)}")

    /**The full lambda syntax is: the variable to which the lambda will be assigned,
     * followed by ‘:’, and you can optionally add the data type of the input parameters in curly
     * brackets, then an arrow ‘’ and the data type of the return value, and ‘=’ to assign the
     * following lambda function.
     * The lambda is defined the same as explained before, inside curly braces you define the
     * parameters with optional data type annotations, the lambda body goes after the arrow ‘’
     * and if the inferred return type of the lambda is not Unit, the last (or maybe only)
     * expression is treated as the return value. */
    val sumLambdaFullSyntax: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    println("The sum of 1 and 4 is: ${sumLambdaFullSyntax(1, 4)}")

    /**You assign the value of lambda to a variable so you can call it later (variable is sumWithPrint)
     * In curly braces, you define your lambda:
     * when a lambda has parameters, they go first, followed by '->': */
    val sumWithPrint = { x: Int, y: Int ->
        /**then the function body follows: */
        println("The sum of $x and $y is: ")
        /**then the last expression in a lambda is considered the return value: */
        x + y
    }
    /** To call the lambda, use the variable sumWithPrint with 2 numbers*/
    println(sumWithPrint(10, 35))

    /**This lambda prints the result in 1 line, and you call it only by its name,
     * not by placing it into a println()*/
    val sumWithPrintInline: (Int, Int) -> Int = { x: Int, y: Int ->
        println("Sum of $x and $y is: ${x + y}")
        x + y
    }
    sumWithPrintInline(5, 19)

    val fruits = listOf("apple", "banana", "kiwi", "cherry", "apricot", "orange")

    printFruitsNew(fruits) { it.startsWith("a") }

    /**Random1 has a value assigned at compile time, and the value never changes when
     * the variable is accessed. This code will generate only one random number every time
     * random1 is accessed */
    val random1 = random()

    /**This code will generate a random number every time random2 is accessed.
     * Random2 has a lambda assigned at compile time, and the lambda is executed every
     * time the variable is referenced, returning a different value.*/
    val random2 = { random() }

    println("Random 1: $random1, Random 2: $random2")

    rollTheDice()

    gamePlay(rollDice(10))

}