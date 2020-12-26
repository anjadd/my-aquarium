package com.annjad.myaquarium.aquarium

data class MyFish(var name: String)

fun main() {
    val isTesting = true
    if (!isTesting) {
        higherOrderFunctionExamples()
        doSomethingExamples()
        printTheWelcome()
        printTheWelcome2()
        assignHOFtoVariable()
        getNumbersInListDivisibleByThree()
        getNumbersInListDivisibleByThreeAgain()
        someSAMsExamples()
        higherOrderFunctsPractice()
        someHigherOrderFunctionExamples()
    } else {
//        higherOrderFunctionExamples()
//        doSomethingExamples()
//        printTheWelcome()
//        printTheWelcome2()

//        assignHOFtoVariable()
//        getNumbersInListDivisibleByThree()
        getNumbersInListDivisibleByThreeAgain()
//        someHigherOrderFunctionExamples()
//        higherOrderFunctsPractice()
    }
}


fun higherOrderFunctionExamples() {

    val fish = MyFish("splashy")

    /**
     * With is used to access an object's members and methods without having to refer to
     * the object once per access.
     * */
    with(fish) {
        name = ("fishy")
        name = this.name.capitalize()
    }
    println(fish.name)      //Fishy


    fish.name = "splashy"
    with(fish.name) {
        println(this.capitalize())      //Splashy
    }
    /**
     * Capitalize() returns a copy of the passed in String, and it doesn't change the original String.
     * That's why fish.name is still unchanged (splashy)
     * */
    println(fish.name)      //splashy

    /**
     * Run is a higher order function that works with all data types.
     * It takes one lambda as its argument and returns the result of executing the lambda.
     * In the example below, run will just return the name of the fish
     * */
    println(fish.run { name })

    /**
     * Apply is similar to run.
     * It is a higher order function that can also be used on all data types.
     * But unlike run which returns the result of executing the lambda (the result of the block
     * function – in Kotlin the lambda could be called however you want to call it, but by a
     * convention it’s called a block function), apply returns the object it’s applied to.
     * So if you apply it to fish, it will return the fish object.
     * */
    println(fish.apply { })        //MyFish(name=splashy)

    /**
     * Apply can be really useful for calling functions on a newly created object.
     * Create a new fish and before you assign it to a variable, call the apply function with a
     * different name.
     * So apply returns the object after the lambda has been applied
     * */
    val fish2 = MyFish("splashy").apply { name = "sharky" }
    println(fish2)      //MyFish(name=sharky)

    /**
     * Let returns a copy of the changed object.
     * Let is very useful for chaining manipulations together.
     * Example: first capitalize fish, then add another string to it, then get its length,
     * then add 31 and print the result.
     * */
    println(fish.let { it.name.capitalize() }.let { it + "fish" }.let { it.length }
        .let { it + 31 })     //42

}


fun doSomethingExamples() {
    /**
     * The function doSomething accepts a function () -> Unit, that takes no parameter and returns
     * Unit. There is no concept of void in Kotlin, if a function returns nothing, it returns Unit
     * (compiler adds return statement for you).
     * This function is required in parameter and the parameter name is block, but you can name it
     * whatever you want, even xyz.*/
    fun doSomething(block: () -> Unit) {
    }

    fun doSomething2(xyz: () -> Unit) { // valid, xyz is the param name
    }

    /**The function doSomething() can call the parameter function passed in it, by calling it
     * like a normal function.*/
    fun doSomething3(xyz: () -> Unit) { // valid, xyz is the param name
        xyz()
    }

    /**This time, the parameter function returns a String*/
    fun doSomething4(block: () -> String) {
        val someString: String = block()
    }

    /**This time, the parameter function accepts an Int and returns a String*/
    fun doSomething5(block: (i: Int) -> String) {
        val someString = block(5)
    }

}

fun printTheWelcome() {
    /**
     * There are 2 ways to pass a function as a parameter:
     * Option 1) Pass an existing function that matches the structure: to pass a function as
     * a parameter to other function use the ‘::’ operator before the function which is passed
     * as a parameter.
     *
     * In the following example you are passing a function printTheSeminarCity() to another
     * function printWelcomeToSeminar(), using the ‘::’ operator.
     */
    fun printWelcomeToSeminar(city: String, printCity: (String) -> Unit) {
        print("Welcome to the Kotlin seminar at ")
        printCity(city)
    }

    fun printTheSeminarCity(semCity: String) {
        println(semCity)
    }

    printWelcomeToSeminar(
        "Vienna",
        ::printTheSeminarCity
    )      //Welcome to the Kotlin seminar at Vienna
}

fun printTheWelcome2() {
    /**
     * There are 2 ways to pass a function as a parameter:
     * Option 2) Pass an anonymous function: To pass an anonymous function as a parameter to other
     * function, create the anonymous function with its input parameters and its body directly when
     * calling the higher order function.
     *
     * In the following example you are passing and creating an inline anonymous function which takes
     * a city and prints it out, to the higher order function printWelcomeToSeminar().
     */
    fun printWelcomeToSeminar(city: String, printCity: (String) -> Unit) {
        print("Welcome to the Kotlin seminar at ")
        printCity(city)
    }

    printWelcomeToSeminar(
        "Vienna",
        fun(cityName: String) { println(cityName) })      //Welcome to the Kotlin seminar at Vienna
}


fun assignHOFtoVariable() {
    /**
     * Higher-order functions can be assigned to a variable as well. */
    fun sumTwoNumbers(x: Int, y: Int): Int = x + y

    val mySum: (Int, Int) -> Int = ::sumTwoNumbers

    println(mySum(3, 7))
}

fun getNumbersInListDivisibleByThree() {
    /**
     * Create an extension on List using a higher order function that returns all the numbers
     * in the list that are divisible by 3.
     * Start by creating an extension function on List that takes a lambda on Int and
     * applies it to each item in the list. When the lambda returns zero, include the item in the output. For example, this list:

    val numbers = listOf<Int>(1,2,3,4,5,6,7,8,9,0)
    Should return

    > [3, 6, 9, 0]*/


    val numbers = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
    fun List<Int>.getNumbersDivisibleByThree(myFilter: (Int) -> Boolean): MutableList<Int> {
        var resultList = mutableListOf<Int>()
        this.forEach { if (myFilter(it)) resultList.add(it) }
        return resultList
    }

    fun checkIfNumberIsDivisibleByThree(num: Int): Boolean {
        return num % 3 == 0
    }

    val res = numbers.getNumbersDivisibleByThree { checkIfNumberIsDivisibleByThree(it) }
    println(res)
}


fun higherOrderFunctsPractice() {
    /**
     * Create an extension on List using a higher order function that returns all the numbers
     * in the list that are divisible by 3.
     * Start by creating an extension function on List that takes an lambda on Int and
     * applies it to each item in the list. When the lambda returns zero, include the item in the output. For example, this list:

    val numbers = listOf<Int>(1,2,3,4,5,6,7,8,9,0)
    Should return > [3, 6, 9, 0]

    Note: This solution doesn't use a higher order function*/
    val numbers = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
    fun List<Int>.getAllNumsDivisibleByThree(): MutableList<Int> {
        var numbersDivisibleByThree: MutableList<Int> = mutableListOf()
        this.filter { it.rem(3) == 0 }.forEach { numbersDivisibleByThree.add(it) }
        return numbersDivisibleByThree
    }
    println(numbers.getAllNumsDivisibleByThree())

}


fun someHigherOrderFunctionExamples() {
    fun printWelcome(city: String, printCity: (String) -> Unit) {
        print("Welcome to our newest Kotlin seminar in ")
        printCity(city)
    }

    fun printCityyy(city: String) {
        println(city)
    }

    printWelcome("Wien", ::printCityyy)

    printWelcome("Wien", fun(cityName: String) { println(cityName) })


}

fun getNumbersInListDivisibleByThreeAgain() {
    /**
     * Create an extension on List using a higher order function that returns all the numbers
     * in the list that are divisible by 3.
     * Start by creating an extension function on List that takes a lambda on Int and
     * applies it to each item in the list. When the lambda returns zero, include the item in the output. For example, this list:

    val numbers = listOf<Int>(1,2,3,4,5,6,7,8,9,0)
    Should return

    > [3, 6, 9, 0]*/

    val numbers = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)

//    fun List<Int>.getDivisibleBy3(myFilter: (Int) -> Int){
//        this.forEach { it.apply{myFilter} }
//    }

    fun List<Int>.getDivisibleBy3other() {
        val result = mutableListOf<Int>()
        this.filter { it % 3 == 0 }.forEach { result.add(it) }
    }

    fun List<Int>.getDivisibleBy3(myFilter: (Int) -> Boolean): MutableList<Int> {
        val result = mutableListOf<Int>()
        this.filter { myFilter(it) }.forEach { result.add(it) }
        return result
    }

    fun isDivisibleBy3(num: Int): Boolean = num % 3 == 0

    println(numbers.getDivisibleBy3 {
        isDivisibleBy3(it)
    })
}

/** SAM - Single Abstract Method is just an interface with only one method on it.
 * Examples of SAMs in Java, used when working with APIs are: runnable and callable.
 * In Kotlin, you will need to call functions that take SAM as parameters all the time.
 * And the best thing is that you can pass a lambda in place of a SAM and Kotlin will make
 * the right kind of object for you.
 */
//Runnable has an abstract method run
interface Runnable {
    fun run()
}

//Callable has a single method call()
interface Callable<T> {
    fun call(): T
}

fun someSAMsExamples() {
    TODO("Write some SAM examples later")
}

