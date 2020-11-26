package com.annjad.myaquarium.otherexamples

import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*

/**
 * To write and execute code in a Kotlin file, you need to create a new
 * Kotlin File/Class. To run an actual Kotlin program you need to define a main
 * function, which is always the entry point for execution.
 *
 * It takes an array of Strings as its arguments, followed by the function body and
 * doesn't have a return statement (it returns a type Unit, which is Kotlin's way of
 * saying return no value and you don't have to specify it explicitly).
 *
 * To run this function, click the green triangle next to the main function on the left.
 * */
fun main(args: Array<String>) {

    /*println(dayOfWeek())
    println(dayOfWeekWithJavaTimeLibrary())*/


    /**Create a main() function that takes an argument representing the time in 24-hour format
     * (values between and including 0 -> 23). In the main() function, check if the
     * time is before midday (<12), then print "Good morning, Kotlin"; otherwise, print "Good night, Kotlin".*/
    if(!args.isNullOrEmpty()){
        println("Good ${if (args[0].toInt() < 12) "morning" else "night"}, Kotlin!")
        //Or you can do the check in a function outside of main
        greetingBasedOnTime(args[0])
    }
    else{
        greetingBasedOnTime("14")
    }



    /**
     * Create a program with a function that returns a fortune cookie message that you can print.
     * It should ask the user to enter the day of their birthday
     * Divide the birthday by the number of fortunes, and use the remainder as the index for the fortune to return.
     * After this, instead of calculating the fortune based on the birthday, use a when statement to assign some fortunes (use your own conditions)
     * Use a for loop to run the program 10 times, or until the "Take it easy" fortune has been selected.
     * */
/*    var fortune: String = ""
    while (!fortune.contains("take it easy", true)) {
        fortune = getFortuneCookie(getBirthday())
        println("\nYour fortune is: $fortune")
    }*/

/*    var fortune2: String
    for (i in 1..10) {
        fortune2 = getFortuneCookie(getBirthday())
        println("\nYour fortune is: $fortune2")
        if (fortune2.contains("take it easy", true)) break
    }*/


/*
    println(whatShouldIDoToday("happy", "sunny"))
    println(whatShouldIDoToday("sad"))
    println(whatShouldIDoToday("happy", "Sunny", 32))
    println(whatShouldIDoToday("moody"))
    println(whatShouldIDoToday("sleepy"))

//    repeat(5) { println(whatShouldIDoToday()) }

    filterSpices()

    filterSpicesShorter()*/

    labeledBreakExample()
}

/** Create a function, dayOfWeek() and print the current day of the week.*/
fun dayOfWeek(): String {
    println("What day is today?")

    return when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
        1 -> "Sunday"
        2 -> "Monday"
        3 -> "Tuesday"
        4 -> "Wednesday"
        5 -> "Thursday"
        6 -> "Friday"
        7 -> "Saturday"
        else -> "Error"
    }
    /*
    // Longer code:
    val todayInt = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    when(todayInt){
        1 -> return "Sunday"
        2 -> return "Monday"
        3 -> return "Tuesday"
        4 -> return "Wednesday"
        5 -> return "Thursday"
        6 -> return "Friday"
        7 -> return "Saturday"
        else -> return "Error"
    }
*/
}

/** Create a function, dayOfWeek() and print the current day of the week.*/
fun dayOfWeekWithJavaTimeLibrary(): String {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        when
                (LocalDate.now().dayOfWeek) {
            DayOfWeek.MONDAY -> "Monday"
            DayOfWeek.TUESDAY -> "Tuesday"
            DayOfWeek.WEDNESDAY -> "Wednesday"
            DayOfWeek.THURSDAY -> "Thursday"
            DayOfWeek.FRIDAY -> "Friday"
            DayOfWeek.SATURDAY -> "Saturday"
            DayOfWeek.SUNDAY -> "Sunday"
            else -> "Error"
        }
    } else {
        TODO("VERSION.SDK_INT < O")
    }
}

fun greetingBasedOnTime(timeStr: String) {
    println("Good ${if (timeStr.toInt() < 12) "morning" else "night"}, Kotlin!")
}

fun getBirthday(): Int {
    print("\nEnter the day of your birthday (1-31):")
    return readLine()?.toIntOrNull() ?: 1
}

fun getFortuneCookie(birthday: Int): String {
    val listOfFortunes = listOf(
        "You will have a great day!", "Things will go well for you today.",
        "Enjoy a wonderful day of success.", "Be humble and all will turn out well.",
        "Today is a good day for exercising restraint.", "Take it easy and enjoy life!",
        "Treasure your friends because they are your greatest fortune."
    )

    // Instead of module operator %, you can use the rem() function
    // val fortuneIndex = birthday % listOfFortunes.size
    val fortuneIndex: Int = when (birthday) {
        28, 31 -> 0
        in 1..7 -> 1
        in 8..10 -> 2
        in 11..15 -> 3
        in 16..20 -> 4
        in 21..24, 29, 30 -> 5
        else -> birthday.rem(listOfFortunes.size)
    }

    return listOfFortunes[fortuneIndex]
}

/**
 * Create a program that suggests an activity based on various parameters
 * mood: a required string parameter(It should ask the user to enter their mood);
 * weather: a string parameter that defaults to "sunny";
 * temperature: an Integer parameter that defaults to 24 (Celsius).
 * Use a when construct to return some activities based on combinations of conditions
 * */
fun whatShouldIDoToday(
    mood: String = getUserMood(),
    weather: String = "Sunny",
    temperature: Int = 24
): String {
    return when {
        isHappySunny(mood, weather) -> "Go for a walk"
        isSadRainyCold(mood, weather, temperature) -> "Stay in bed"
        isVeryHot(temperature) -> "Go swimming"
        else -> "Stay home and read."
    }
}

fun getUserMood(): String {
    print("\nEnter mood: ")
    // The !! Operator is the not-null assertion operator (!!), which converts any value
    // to a non-null type and throws an exception if the value is null.
    return readLine()!!
}

fun isHappySunny(mood: String, weather: String) = mood == "happy" && weather == "Sunny"
fun isSadRainyCold(mood: String, weather: String, temperature: Int) =
    mood == "sad" && weather == "rainy" && temperature == 0

fun isVeryHot(temperature: Int) = temperature > 35


/**
 * Create a filter that gets all the curries and sorts them by string length.
 * Filter the list of spices to return all the spices that start with 'c' and end in 'e'. Do it in two different ways.
 * Take the first three elements of the list and return the ones that start with 'c'.*/
fun filterSpices() {
    val spices =
        listOf("curry", "pepper", "cayenne", "ginger", "red curry", "green curry", "red pepper")

    var curriesSortedByLength = spices.filter { it.contains("curry") }
    curriesSortedByLength = curriesSortedByLength.sortedBy { it.length }
    println("Curries sorted by length:")
    curriesSortedByLength.forEach { println(it) }

    println("Curries sorted by length shorter version:")
    spices.filter { it.contains("curry") }.sortedBy { it.length }.forEach { println(it) }


    val spicesStartingWithCAndEndingWithE =
        spices.filter { it[0] == 'c' && it[it.length - 1] == 'e' }
    println("Spices starting with C and ending with E:")
    spicesStartingWithCAndEndingWithE.forEach { println(it) }

    println("Spices starting with C and ending with E shorter version:")
    spices.filter { it[0] == 'c' && it[it.length - 1] == 'e' }.forEach { println(it) }


    val spicesStartingWithCAndEndingWithE2 = spices.filter { it.first() == 'c' && it.last() == 'e' }
    println("Spices starting with C and ending with E:")
    spicesStartingWithCAndEndingWithE2.forEach { println(it) }

    //Sublist returns a view of the portion of this list between the specified [fromIndex] (inclusive)
    // and [toIndex] (exclusive). That's why you need to set the upper toIndex to 3 instead of 2
    val firstThreeThatStartWithC = spices.subList(0, 3).filter { it.first() == 'c' }
    println("Spices starting with C and are part of the first 3:")
    firstThreeThatStartWithC.forEach { println(it) }

    println("Spices starting with C and are part of the first 3 shorter version:")
    spices.subList(0, 3).filter { it.first() == 'c' }.forEach { println(it) }
}


/**
 * Create a filter that gets all the curries and sorts them by string length.
 * Filter the list of spices to return all the spices that start with 'c' and end in 'e'. Do it in two different ways.
 * Take the first three elements of the list and return the ones that start with 'c'.*/
fun filterSpicesShorter() {
    val spices =
        listOf("curry", "pepper", "cayenne", "ginger", "red curry", "green curry", "red pepper")

    println("Curries sorted by length:")
    spices.filter { it.contains("curry") }.sortedBy { it.length }.forEach { println(it) }

    println("Spices starting with C and ending with E:")
    spices.filter { it[0] == 'c' && it[it.length - 1] == 'e' }.forEach { println(it) }
    //Second option
    spices.filter { it.first() == 'c' && it.last() == 'e' }.forEach { println(it) }
    //Third option
    spices.filter { it.startsWith('c') }.filter { it.endsWith('e') }.forEach { println(it) }

    //Sublist returns a view of the portion of this list between the specified [fromIndex] (inclusive)
    // and [toIndex] (exclusive). That's why you need to set the upper toIndex to 3 instead of 2
    println("Spices starting with C and are part of the first 3:")
    spices.subList(0, 3).filter { it.first() == 'c' }.forEach { println(it) }
    //Second option
    spices.take(3).filter { it.startsWith('c') }.forEach { println(it) }
}


fun swimExamples() {
    var dirty = 20
    val swim = { dirty: Int -> dirty > 30 }
    swim(dirty)
}

fun printNumberN() {

    val n = -1

    when (n) {
        1, 2, 3 -> println("n is a positive integer less than 4.")
        0 -> println("n is zero")
        -1, -2 -> println("n is a negative integer greater than 3.")
    }
}

/**Kotlin gives you additional control over loops with the labeled break.
 * A break qualified with a label annotation jumps to the execution point right after the
 * loop marked with that label. This gives you better control over nested loops.
 *
 * The code below will print out:
 *  i = 1, j = 1
    i = 1, j = 2
    i = 2, j = 1
 *
 * Here by using break with a label (break@first in this case), you can break the specific loop.
 * So, when i == 2 expression is evaluated to true, break@first is executed which terminates
 * the loop marked with label first@. */
fun labeledBreakExample() {
    first@ for (i in 1..4) {
        for (j in 1..2) {
            println("i = $i, j = $j")

            if (i == 2)
                break@first
        }
    }
}