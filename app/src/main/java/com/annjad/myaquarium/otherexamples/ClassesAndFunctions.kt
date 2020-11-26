package com.annjad.myaquarium.otherexamples

open class ClassesAndFunctions {

    /**Reverse the elements of a list or array*/
    fun reverseElements(list: List<Int>): List<Int> {
        val reverseList = mutableListOf<Int>()

        // for (i: Int in list.indices) can be replaced with for (i: Int in 0..list.size-1)
        // or with for(i in 0..4)
        for (i: Int in list.indices) {
            println("Index i = $i, indices: ${list.indices}")
            // You need to use newList.add(x) instead of newList[0]=x because the newList is empty
            // and accessing the 1st element by newList[0] will throw an error
            reverseList.add(list[list.size - 1 - i])
        }

        return reverseList
    }

    /**Reverse the elements of a list or array*/
    fun reverseElementsSecondWay(list: List<Int>): MutableList<Int> {
        val result = mutableListOf<Int>()
        for (i: Int in list.size - 1 downTo 0) {
            result.add(list[i])
        }
        return result
    }

    /**Reverse the elements of a list or array by using the method reversed()*/
    fun reverseElementsThirdWay(list: List<Int>) {
        println(list.reversed())
    }

    fun someOperationsOnLists() {
        val fruits = mutableListOf("apple", "banana", "kiwi", "cherry", "orange")

        fruits.add("lemon")
        fruits.remove("kiwi")
        println(fruits)   //[apple, banana, cherry, orange, lemon]

        //contains() tests whether or not an item is contained in the list
        println(fruits.contains("pomegranate")) //false
        println(fruits.contains("apple"))       //true

        //sublist() creates a sublist from the index which is the 1st argument to the next index excluded, which is the 2nd argument
        val fruitsSublist = fruits.subList(2, fruits.size)
        println(fruitsSublist)  //[apple, banana, cherry, orange, lemon] -> [cherry, orange, lemon]

        //You can do math on lists:
        //sum() sums all elements
        //sumBy() takes a lambda that specifies what property of the elements should be summarized.
        //The “it” is the default name for lambda arguments, and in your case example, that “it” refers to each element of the list as it’s traversed.
        println(listOf(1, 3, 5).sum())
        println(listOf("a", "b", "cc").sumBy { it.length }) // 4 (1+1+2=4)

    }

    fun someOperationsOnMapsAndSets() {
        /** A Map contains key/value pairs. Doesn't allow duplicates.
         * In Kotlin, you can map almost anything to anything else, using the mapOf() method.
         * Define a map by using the mapOf() function combined with the “to” function which
         * is used to connect the key to its value.
         * You can retrieve the value, based on the key by using get() or indexing.*/

        val fruitsWithColors: Map<String, String> =
            mapOf("cherry" to "red", "banana" to "yellow", "kiwi" to "green")
        println("The key banana has a value: ${fruitsWithColors["banana"]}") //yellow

        //Also you can specify what to do if a key is not found in the list and provide a default value.
        println("The default key is: ${fruitsWithColors.getOrDefault("strawberry", "white")}")

        // If you need to do more than just return a value, you can use the getOrElse() method.
        // Instead of returning a simple default value, whatever code is between the curly braces is executed.
        println(fruitsWithColors.getOrElse("apple") { "No color for apple" })
        val fruits = listOf("cherry", "banana", "kiwi")
        println(fruits.getOrElse(3) { "No item at index 3" })

        //Create a Set of book titles called allBooks, for example, by William Shakespeare.
        val allBooksByShakespeare: Set<String> =
            setOf("Macbeth", "Romeo and Juliet", "Hamlet", "Julius Caesar", "Othello")

        //Create a Map that associates every book of the set of books, allBooks, to the author.
        val shakespeareLibrary: MutableMap<String, String> = mutableMapOf()
        allBooksByShakespeare.forEach { it -> shakespeareLibrary.put(it, "William Shakespeare") }
        println(shakespeareLibrary) //{Macbeth=William Shakespeare, Romeo and Juliet=William Shakespeare, Hamlet=William Shakespeare, Julius Caesar=William Shakespeare, Othello=William Shakespeare}

        //Create a Map called library that associates the set of books, allBooks, to the author.
        val library = mutableMapOf<String, Set<String>>()
        library.put("Shakespeare", allBooksByShakespeare)

        //Use the collections function any() on library to see if any of the books are "Hamlet"
        println("Are any of the books Hamlet? ${library.any { it.value.contains("Hamlet") }}")

        //Create a MutableMap called moreBooks, and add one title/author to it.
        //Use getOrPut() to see whether a title is in the map, and if the title is not in the map, add it.
        //getOrPut() is a handy function that will check whether a key is in a map, and if it is,
        // will return the value. Otherwise, it will add the key with the supplied value to the map.
        val moreBooks: MutableMap<String, String> = mutableMapOf()
        moreBooks.put("The subtle art", "Mark Manson")
        moreBooks.getOrPut("Book about hope", { "Mark Manson" })
        moreBooks.getOrPut("Everything Is Fucked", { "Mark Manson" })

        println("Some more Books: $moreBooks") //Some more Books: {The subtle art=Mark Manson, Book about hope=Mark Manson, Everything Is Fucked=Mark Manson}
    }

    fun someDefaultAndNamedArguments() {

        /**The library function joinToString is declared with default values for parameters:

        fun joinToString(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = "",
        /* ... */
        ): String
         * */

        //Make a function joinOptions() to return a list in a JSON format (e.g., "[a, b, c]"), using named arguments
        fun joinOptions(options: Collection<String>) = options
            .joinToString(separator = ", ", prefix = "[", postfix = "]")

        val myIceCreamFlavors = listOf("chocolate", "sacher", "vanilla")
        println(joinOptions(myIceCreamFlavors)) // [chocolate, sacher, vanilla]

    }

    fun someLambdaExamples() {
        /** Pass a lambda to any function to check if the collection contains an even number.
         * The function any gets a predicate as an argument and returns true if there is at least
         * one element satisfying the predicate.*/

        fun containsEven(collection: Collection<Int>): Boolean = collection.any { it % 2 == 0 }

        val myRandomNumbers = listOf(1, 5, 27, 9)
        println("Does the list (1, 5, 27, 9) contain an even number? ${containsEven(myRandomNumbers)}")
    }

    fun someStringsExamples() {

        //Escaping is done in the conventional way, with a backslash.
        val s = "Hello, world!\n"

        val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

        /** Create a pattern that matches the date in format 13 JUN 1992
         * (two digits, a whitespace, a month abbreviation, a whitespace, four digits).*/
        // A raw string is delimited by a triple quote ("""), contains no escaping
        // and can contain newlines and any other characters:
        fun getPattern(): String = """\d{2} $month \d{4}"""

    }

    fun someExtensionFunctionExamples() {

        /**You can declare a new function in a class without changing the original code and
         * use the instance of that class to call the function. These are extension functions.
         *
         * They are a great way to add helpful functionalities to classes you don’t own.
         * Extensions don’t actually modify the classes they extend.
         *
         * They are defined outside of the class they extend. So, they don’t get access to private variables
         *
         * You don't need to use braces around the body of the function because this can be
         * represented as one expression.
         *
         * Example: Create an extension function that will check if a String has spaces.*/
        fun String.hasSpaces() = find { it == ' ' } != null

        /**You can use extensions as helper methods which are not part of the core API of the class*/
        open class AquariumPlant(val color: String, private val size: Int)

        fun AquariumPlant.isRed() = color == "Red"
        //fun AquariumPlant.isBig() = size > 50  //error: cannot access size because it is private

        /**Extension functions are resolved statically, meaning at compile time.*/
        class GreenLeafyPlant(size: Int) : AquariumPlant(color = "Green", size = size)

        fun AquariumPlant.print() = println("Aquarium Plant")
        fun GreenLeafyPlant.print() = println("Green Leafy Plant")

        val greenPlant = GreenLeafyPlant(50)
        greenPlant.print()  //Green Leafy Plant

        val aquariumPlant: AquariumPlant = greenPlant
        /**You might expect that this will print "Green Leafy Plant", but it doesn't.
         * This happens because the extension function is resolved statically (at compile time).
         * And the compiler just looks at the type of the variable*/
        aquariumPlant.print()   //Aquarium Plant

        fun AquariumPlant?.pullPlant() {
            this?.apply { println("Removing the ${this.color} plant from the aquarium") }
        }

        /**You can also make the class you’re extending (called a receiver) nullable.
         * If you do that, then the “this” variable used in the body can be null */
        fun nullableExample() {
            val plant: AquariumPlant? = null
            plant.pullPlant()   //it's ok, won't crash

            val plant2 = AquariumPlant("Yellow", 35)
            plant2.pullPlant()
        }
        nullableExample()

    }

}

/**You can define extension properties too. Just like extension functions, you specify
 * the class you’re extending, followed by a dot, and a property name.
 *
 * Note that the extension property has to be declared outside of the class and outside of any
 * function, otherwise it’ll give an error that local extension properties are not allowed.
 *
 * Also, you have to initialize the extension property through a getter, otherwise
 * it’ll show an error. Then you can use isGreen just like a regular property*/
open class AquariumPlant(val color: String, private val size: Int)

val AquariumPlant.isGreen: Boolean
    get() {
        return color == "Green"
    }

fun extensionPropertyExample() {
    val somePlant = AquariumPlant("Green", 50)
    println("Is some plant green? Answer: ${somePlant.isGreen}")    //Is some plant green? Answer: true
}

fun main() {
    val classObject = ClassesAndFunctions()


    val numbers = listOf(1, 2, 3, 4, 5)
    println("Reverse Elements: ${classObject.reverseElements(numbers)}")
    println("Reverse Elements Second Way: ${classObject.reverseElementsSecondWay(numbers)}")
    classObject.reverseElementsThirdWay(numbers)

    classObject.someOperationsOnLists()

    classObject.someOperationsOnMapsAndSets()

    classObject.someDefaultAndNamedArguments()

    classObject.someOperationsOnMapsAndSets()
    classObject.someLambdaExamples()

    classObject.someStringsExamples()

    classObject.someExtensionFunctionExamples()
    extensionPropertyExample()


}
