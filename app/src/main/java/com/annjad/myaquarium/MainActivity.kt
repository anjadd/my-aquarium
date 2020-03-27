package com.annjad.myaquarium

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val TAG: String = "Anja"
//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityMainBinding.inflate(layoutInflater)

//        setContentView(binding.root)

        setContentView(R.layout.activity_main)

/*        someKotlinExamples()

        addFishInAquariums()

        someOtherExamples()

        maxOf(10, 3)

        findMaxOf(4, 25)*/

        collectionExamples()

        varargsExamples()

    }

    private fun someKotlinExamples() {

        val languageName: String = "Kotlin"

        //This will fail to compile ERROR
        //var coffees: Int = null

        //Set a nullable type using ? for successful compile
        var numberOfCoffees: Int? = null
        numberOfCoffees = numberOfCoffees?.inc()
        println("You have ordered $numberOfCoffees coffees")

        //val address : String = null
        val amount: Double? = 10.0
        var list: List<String?>? = listOf(null, null)
        list?.size

        var food = 5
        val name = "Anja"

        fun eatFood(): Int {
            return food?.dec() ?: 0
        }

        food = eatFood()


        var flowers: Int? = 5

        fun buyNewFlower() {
            flowers = flowers?.inc()
        }

        var rainbowColor: String = "rainbow"
        rainbowColor = "blue"
//        rainbowColor = null ERROR

        var blueColor: String? = null
        var greenColor = null

        val blackColor = "black"
//        blackColor = "white" ERROR

        var yellowColor = "yellow"

        val fruits = listOf("apple", "banana", "kiwi", "cherry", "orange")

        Toast.makeText(this, flowers.toString(), Toast.LENGTH_LONG).show()
        Toast.makeText(this, food.toString(), Toast.LENGTH_LONG).show()
        Toast.makeText(this, name, Toast.LENGTH_LONG).show()

    }

    private fun addFishInAquariums() {
        /** If you start with 2 fish, and they breed twice, producing 71 offspring the first time,
         * and 233 offspring the second time, and then 13 fish are swallowed by a hungry moray eel,
         * how many fish do you have left? How many aquariums do you need if you can put 30 fish
         * per aquarium? */

        var fish = 2
        fish = fish.plus(71).plus(233).minus(13)

        var aquariums = fish.div(30)
        Log.v(TAG, "Aquariums before module: $aquariums")

        if (fish.rem(30) > 0) {
            aquariums++
        }

/*        binding.tvFishValue.setText(fish)
        binding.tvAquariumValue.text = aquariums.toString()*/


        Toast.makeText(this, "Aquariums: $aquariums", Toast.LENGTH_LONG).show()

        Log.v(TAG, "Fish: $fish")
        Log.v(TAG, "Aquariums: $aquariums")
        println("Number of fish in the aquarium: $fish")
    }

    private fun someOtherExamples() {
        var a = 5
        var b = 10
        var maxOf: Int = if (a > b) a else b

        var maxx = when {
            a > b -> a
            else -> b
        }

        var language: String? = "Kotlin"

        fun getLanguage() = "Kotlin"

        when (language) {
            "Kotlin" -> println("Language is Kotlin")
            "Java" -> println("Language is Java")
            null -> println("Language is not specified")
            else -> println("Language is $language")
        }

        class Train(val cargo: Number?) {
            override fun toString(): String {
                return when (cargo) {
                    null, 0 -> "empty"
                    1 -> "tiny"
                    in 2..10 -> "small"
                    is Int -> "big inty"
                    else -> "$cargo"
                }
            }
        }

        val numberOfCoffees = 5
        println("You have ordered $numberOfCoffees coffees!")
        //This will print out: You have ordered 5 coffees!

    }

    private fun findMaxOf(a: Int, b: Int): Int {
        return if (a > b) {
            a
        } else {
            b
        }
    }

    private fun maxOf(a: Int, b: Int) = if (a > b) a else b

    private fun collectionExamples() {

        val fruits1 = listOf("apple", "banana", "kiwi", "cherry", "orange")
        for (fruit in fruits1) {
            println(fruit)
        }

        val fruits = mutableListOf("apple", "banana", "kiwi", "cherry", "orange")
        fruits.add("lemon")

        /*val fruits: MutableList<String> = mutableListOf("apple", "banana", "kiwi", "cherry", "orange")
        fruits.add("lemon")*/

        //Or using the list item indexes in the for loop
        for (fruitIndex in fruits.indices) {
            println("Fruit at index $fruitIndex is: ${fruits[fruitIndex]}")
        }

        val fruits2 = listOf("apple", "banana", "kiwi", "cherry", "orange")
        var index = 0
        while (index < fruits2.size) {
            println("Fruit at index $index is: ${fruits2[index]}")
            index++
        }

        val interestingThings = arrayOf("Reading", "Yoga", "Biking", "Programming")
        //Operations that you can perform on an array
        println("The size of interestingThings array is : ${interestingThings.size}")
        println("The first element in interestingThings is: ${interestingThings.get(0)}")
        println("The first element in interestingThings is: ${interestingThings[0]}")

        println("Your interestingThings items are:")
        //For loop
        for (thing in interestingThings) {
            println(thing)
        }

        //Foreach loop with lambda expression, the same loop as the for loop above
        val interestingThings2 = arrayOf("Reading", "Yoga", "Biking", "Programming")
        interestingThings2.forEach { thing ->
            println(thing)
        }

        interestingThings2.forEachIndexed { index, thing ->
            println("Thing at position $index is $thing")
        }

        val interestingThings3 = listOf("Reading", "Yoga", "Biking", "Programming")
        println("Second element in list is ${interestingThings3[1]}")
        println("Last element in list is: ${interestingThings3.last()}")
        println("Elements in sublist from 3rd element to the end of list are: ")
        interestingThings3.subList(2, interestingThings3.size).forEach { thing: String ->
            println(thing)
        }

        val mapOfInterestingThings =
            mapOf(1 to "Reading", 2 to "Yoga", 3 to "Biking", 4 to "Programming")
        mapOfInterestingThings.forEach { (key, value)
            ->
            println("Map $key -> $value")
        }

        val mapOfInterestingThings2 =
            mutableMapOf(1 to "Reading", 2 to "Yoga", 3 to "Biking", 4 to "Programming")
        //mapOfInterestingThings2.put(5, "Swimming")
        mapOfInterestingThings2[5] = "Swimming"

        val someArray = mutableSetOf(1 to "a", 1 to "b")

        val someList = listOf(null, null)
//        val someOtherList = [null, null]
        val otherList: List<Int>? = null
        val myList: List<Int?> = listOf(null, null)
    }

    private fun varargsExamples() {

        /**
         * Function that takes n number as inputs and returns the average of all the inputs.
         * You can accomplish this by defining a parameter as a vararg.
         * */
        fun getAverage(vararg inputNumbers: Int): Float {
            var sum = 0.0f
            inputNumbers.forEach { number -> sum += number }
            return (sum / inputNumbers.size)
        }

        val result1 = getAverage(1, 2, 3)
        println("Average of 1, 2, 3 = $result1")

        val result2 = getAverage(1, 2, 3, 4, 5)
        println("Average of 1, 2, 3, 4, 5 = $result2")

        val inputsForGetAverage1 = intArrayOf(7, 8, 9)
        val result3 = getAverage(*inputsForGetAverage1)
        println("Average of 7, 8, 9 = $result3")

        /**
         * Function that takes n number as inputs and returns the average of all the inputs.
         * If the size of n variables is not fixed, you usually convert it into an array
         * or list and pass it to function.
         * */
        fun getAverage2(numbersList: List<Int>): Float {
            var sum = 0.0f
            numbersList.forEach { number -> sum += number }
            return (sum / numbersList.size)
        }

        val inputsForGetAverage2 = arrayListOf(4, 5, 6)
        val result4 = getAverage2(inputsForGetAverage2)
        println("Average of 4, 5, 6 = $result4")

    }
}
