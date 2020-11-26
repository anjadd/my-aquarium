package com.annjad.myaquarium.otherexamples

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.annjad.myaquarium.R
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private val TAG: String = "Anja"
//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityMainBinding.inflate(layoutInflater)

//        setContentView(binding.root)

        setContentView(R.layout.activity_main)

        /** Uncomment the functions you want to test*/

        /*
        someKotlinExamples()

        addFishInAquariums()

        someOtherExamples()

        collectionExamples()

        varargsExamples()

        */

        collectionExamples()
        classExamples()

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

        //Ranges using the in and .. syntax
        // iterating in the char range from a to g
        for (charItem in 'a'..'g') {
            println("Char $charItem")   //Output: a b c d e f g
        }

        // iterating in the range 1 to 5
        for (numItem in 1..5) {
            println(numItem)    //Output: 1 2 3 4 5
        }

        // iterating backwards, in the range 5 to 1, using the “in” and “downTo” syntax
        for (numItem in 5 downTo 1) {
            println(numItem)    //Output: 5 4 3 2 1
        }

        //Range advancing in multiple steps (ex. print all even numbers in a range from 2 to 10
        for (evenNum in 2..10 step 2) {
            println("Even number: $evenNum")    //Output: 2 4 6 8 10
        }

        /**Use a for loop to create (a list of) the numbers between 0 and 100 that are divisible by 7*/
        fun numbersFrom0To100DivisibleBy7() {
            var divisibleNumbers = mutableListOf<Int>()
            for (el in 0..100) {
                if (el % 7 == 0) {
                    divisibleNumbers.add(el)
                }
            }
            println("========== Numbers 0-100, divisible by 7 ==========")
            print(divisibleNumbers)

            var divisibleNumbersOption2 = mutableListOf<Int>()
            for (el in 0..100 step 7) {
                divisibleNumbersOption2.add(el)
            }
            print(divisibleNumbersOption2)
        }
        numbersFrom0To100DivisibleBy7()
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

        var welcomeMessage = "Hello and welcome to Kotlin"
        when (welcomeMessage.length) {
            0 -> println("Nothing to say?")
            in 1..50 -> println("Perfect")
            else -> println("Too long!")
        }

        val numberOfCoffees = 5
        println("You have ordered $numberOfCoffees coffees!")
        //This will print out: You have ordered 5 coffees!

        fun findMaxOf(a: Int, b: Int): Int {
            return if (a > b) {
                a
            } else {
                b
            }
        }

        findMaxOf(4, 25)

        fun maxOf(a: Int, b: Int) = if (a > b) a else b

        maxOf(10, 3)


    }

    private fun collectionExamples() {

        val fruits = listOf("apple", "banana", "kiwi", "cherry", "orange")
        for (fruit in fruits) {
            println(fruit)
        }

        if (fruits.contains("cherry")) {
            println("The fruits contain cherry")
        }
        println("The first element in fruits is ${fruits.first()} and the last is ${fruits.last()}")

        //Finding the index of an element in an array
        println("The index of kiwi is: ${fruits.indexOf("kiwi")}")

        println("Number of elements is list fruits is: ${fruits.count()}")


        val fruits1 = mutableListOf("apple", "banana", "kiwi", "cherry", "orange")
        fruits1.add("lemon")

        /*val fruits1: MutableList<String> = mutableListOf("apple", "banana", "kiwi", "cherry", "orange")
        fruits1.add("lemon")*/

        //Or using the list item indexes in the for loop
        for (fruitIndex in fruits.indices) {
            println("Fruit at index $fruitIndex is: ${fruits[fruitIndex]}")
        }

        //This is the same as iterating using the indices
        val fruits2 = listOf("apple", "banana", "kiwi", "cherry", "orange")
        for ((index, value) in fruits2.withIndex()) {
            println("The fruit at index $index is: $value")
        }

        val fruits3 = listOf("apple", "banana", "kiwi", "cherry", "orange")
        var index = 0
        while (index < fruits3.size) {
            println("Fruit at index $index is: ${fruits3[index]}")
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

        // Foreach loop, where it is the default name for each element in the array
        // that is passed into this lambda function
        interestingThings2.forEach { it -> println(it) }

        // Even more simplified from the code above
        interestingThings2.forEach { println(it) }

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

        /**
         * The next line says create an array with 5 elements, and at the current index
         * put a value that's 3 times the index and do this for all the elements
         * Output: 0 3 6 9 12*/
        val someNumbers = Array(5) { i -> i * 3 }
        println("Array Some Numbers: ======= ")
        //it is the default name for each element in the array that is passed
        someNumbers.forEach { println(it) }

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

        fun fishTypesThatILike() {
            val trout: String = "trout"
            val haddock: String = "haddock"
            val snapper: String = "snappergrergerheefwf"

            println("I will eat the $haddock, but won't eat the $trout and $snapper")

            val fishList = listOf(trout, haddock, snapper)
            for (fishName in fishList) {
                when (fishName.length) {
                    0 -> println("Fish name can't be 0")
                    in 3..12 -> println("The $fishName is good fish name")
                    else -> println("The $fishName is OK fish name")
                }
            }

            val numbers: IntArray = intArrayOf(1, 2, 3)
            val floats: FloatArray = floatArrayOf(2.5f, 3.44f, 5.7832f)
            val chars: CharArray = charArrayOf('a', 'A', 'b')

            val mixedArray = arrayOf("fish", 2, 5.5f)

            //Option 1 for printing the contents of mixed array
            for (temp in mixedArray) {
                println(temp)
            }

            //Option 2 for printing the contents of mixed array, result is: [fish, 2, 5.5]
            println(mixedArray.contentToString())

            for (temp in numbers) {
                println(temp)
            }

            floats.forEach {
                println(it)
            }

            for (temp in chars) {
                println(temp)
            }

            fun addNumbersToListWithStrings() {
                /**
                 * Create an integer array of numbers, from 11 to 15.
                Create an empty mutable list for Strings.
                Write a for loop that loops over the array and adds the string representation
                of each number to the list.*/
                val myNumbers = Array(5) { i -> i + 11 }
                println("========== My Numbers ==========")
                myNumbers.forEach { println(it) }

                val myNumbersOption2 = arrayListOf<Int>()
                for (numb in 11..15) {
                    myNumbersOption2.add(numb)
                }
                println("========== My Numbers Option 2 ==========")
                myNumbers.forEach { println(it) }

                var myStrings = mutableListOf<String>()
                myNumbers.forEach { myStrings.add(it.toString()) }
                println("========== My Strings ==========")
                myStrings.forEach { println(it) }

            }
            addNumbersToListWithStrings()

        }

        fishTypesThatILike()

        fun favoriteFruits() {
            val allFruits = listOf("cherry", "strawberry", "watermelon")
            val likeliness =
                listOf("most favorite", "second most favorite", "third most favorite")

            for ((i, value) in allFruits.withIndex()) {
                println("My ${likeliness[i]} fruit is: $value")
            }

            /** Output:
             * My most favorite fruit is: cherry
             * My second most favorite fruit is: strawberry
             * My third most favorite fruit is: watermelon */
        }

        favoriteFruits()

        /**
         * This function converts larger byte sizes (KB,MB,GB, etc.) into bytes and iterates through both arrays simultaneously
         * [0] 1B = 1B
         * [1] 1 KB = 1024 B = 1024^1
         * [2] 1 MB = 1024 * 1024 B = 1024^2 (1024 na stepen 2, kolku sto e momentalniot indeks na el)
         * [3] 1 GB = 1024 * 1024 * 1024 B = 1024^3
         * [4] 1 TB = 1024 * 1024 * 1024 * 1024 B = 1024^4
         * [5] 1 PB = 1024 * 1024 * 1024 * 1024 * 1024 B = 1024^5
         * [6] 1 EB = 1024 * 1024 * 1024 * 1024 * 1024 * 1024 B = 1024^6
         */
        fun convertDiferentByteSizes() {
            val bytesArray = Array(7) { 1024.0.pow(it) }
            val sizes = arrayOf(
                "byte", "kilobyte", "megabyte", "gigabyte",
                "terabyte", "petabyte", "exabyte"
            )

            for ((myIndex, value) in bytesArray.withIndex()) {
                println("1 ${sizes[myIndex]} = ${value.toLong()} bytes")
            }

            /***
             * Output:
            1 byte = 1 bytes
            1 kilobyte = 1024 bytes
            1 megabyte = 1048576 bytes
            1 gigabyte = 1073741824 bytes
            1 terabyte = 1099511627776 bytes
            1 petabyte = 1125899906842624 bytes
            1 exabyte = 1152921504606846976 bytes
             */
        }
        convertDiferentByteSizes()

        val someList = listOf(null, null)
//        val someOtherList = [null, null]
        val otherList: List<Int>? = null
        val myList: List<Int?> = listOf(null, null)
    }

    private fun varargsExamples() {

        /**
         * Function that takes n number of integers as inputs and returns the average of all the inputs.
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
         * The same avg function but the vararg parameter is replaced with a list
         * Function that takes n number of integers as inputs and returns the average of all the inputs.
         * If the size of n variables is not fixed, you usually convert it into an array
         * or list and pass it to the function.
         * */
        fun getAverage2(numbersList: List<Int>): Float {
            var sum = 0.0f
            numbersList.forEach { number -> sum += number }
            return (sum / numbersList.size)
        }

        val inputsForGetAverage2 = arrayListOf(4, 5, 6)
        val result4 = getAverage2(inputsForGetAverage2)
        println("Average of 4, 5, 6 = $result4")

        fun getAverageAndPrint(message: String, vararg numbers: Int) {
            var sum = 0.0f
            numbers.forEach { number ->
                sum += number
            }
            val avgResult = sum / numbers.size
            println("$message $avgResult")
        }

        getAverageAndPrint("You can even define the numbers here, Average of 3, 4, 5 =", 3, 4, 5)
        getAverageAndPrint("Only one param, you don't need to send the numbers")


        fun getAverageAndPrint2(message: String, numbers: List<Int>) {
            var sum = 0.0f
            numbers.forEach { number ->
                sum += number
            }
            val avgResult = sum / numbers.size
            println("$message $avgResult")
        }

        getAverageAndPrint2("Average of 3, 4, 5 =", arrayListOf(3, 4, 5))


        fun getPopularHobbies(message: String, vararg hobbies: String) {
            println(message)
            hobbies.forEach { hobby -> println(hobby) }
        }

        val popularHobbies = arrayOf("Swimming", "Reading", "Yoga", "Biking", "Programming")

        // This throws an error, because required type is String, but instead an Array<String> is found
        // getPopularHobbies("The most popular hobbies are: ", popularHobbies)   ERROR

        /**
         * You will need to pass the popular hobbies array of values in the vararg
         * parameter using the spread (*) operator
         * */
        getPopularHobbies("The most popular hobbies are: ", *popularHobbies)

        /**
         * Named arguments is a powerful feature in Kotlin, which allows you to specify the
         * names of arguments that you're passing to the function.
         * You can even mix up the order of the function parameters.
         * How to define a named argument: parameter_name = parameter_value. Use this when calling
         * the desired function.*/
        fun completingLotroQuest(user: String, lotroCharacter: String, quest: String) {
            println("Dear $user, your character $lotroCharacter has completed the quest $quest!")
        }

        completingLotroQuest(
            user = "Lexa_mk",
            lotroCharacter = "Belexael",
            quest = "Wandering Elfs"
        )
        completingLotroQuest(
            quest = "Collecting Spring flowers",
            user = "Lexa_mk",
            lotroCharacter = "Belexa"
        )

        /**
        This will throw an error: Mixing named and positioned arguments is not allowed
        if you use named argument syntax for one argument, all the argument that follow also
        have to be named. The 2nd parameter has to be named also. */
        //completingLotroQuest(user = "Lexa_mk", "Belexael", quest = "Wandering Elfs")

        completingLotroQuest("Lexa_mk", "Belexael", quest = "Wandering Elfs")

        /**
         * Kotlin supports default arguments in function declarations. You can specify a
         * default value for a function parameter. So if no value for a parameter is specified,
         * use the default value. If all the arguments in the function have defaults,
         * you could call the function without passing any arguments in.
         * How to define a default parameter value: parameter_name: parameter_type = default_parameter_value
         */
        fun completingLotroQuest2(
            user: String = "Guest", lotroCharacter: String = "main character",
            quest: String = "Introduction"
        ) {
            println("Dear $user, your character $lotroCharacter has completed the quest $quest!")
        }

        completingLotroQuest2(lotroCharacter = "Arwen")
        completingLotroQuest2()
    }

    private fun classExamples() {

        val person1 = Person(firstName = "Dean", lastName = "Winchester")
        println("Name P1: ${person1.firstName} ${person1.lastName}")

        val person2 = Person()
        println("Name P2: ${person2.firstName} ${person2.lastName}")

        val person3 = Person()
        person3.nickname = "Shades"
        println("Name P3: ${person3.firstName} ${person3.lastName}, nickname: ${person3.nickname}")

        val person4 = Person()
        person4.nickname = "Shades"
        person4.printPersonInfo()

        // If/else check
        val nickname: String? = "Luv"
        val nicknameToPrint: String = if (nickname != null) nickname else ""

        //Replacing the if/else check with Elvis operator check ?:
        val nicknameToPrintElvis: String = nickname ?: ""

        val person5 = Person()
        person5.age = 20
        person5.nickname = null
        println("Details for P5:")
        person5.printPersonInfo()

        //Instantiating the BasicInfoProvider interface
        val provider = BasicInfoProvider()
        provider.printInfo(person5)
        provider.getSessionId()

        fun checkDataTypes() {
            val echo: Person = Person()
            if (echo is Person) {
                println("Variable echo is of type Person")
            } else if (echo !is Person) {
                println("Variable echo is not of type Person")
            } else {
                println("Variable echo is of an unknown type")
            }
        }

        checkDataTypes()

        fun castDataTypes() {
            val avgPoints = 24.8
            val points = avgPoints.toInt()
            println("Points: $points")
        }

        castDataTypes()


    }
}
