package com.annjad.myaquarium.otherexamples

/** Create class, SimpleSpice. Let the class be a property with a String for the name of
 * the spice, and a String for the level of spiciness. Set the name to curry and the
 * spiciness to mild. Add a spicinessLevel property to your class with a getter that returns
 * a numeric value for spiciness, useful for calculations. Use a value of 5 for mild.
 * Create an instance of SimpleSpice and print out its name and heat.*/

class SimpleSpice {

    var spiceName: String = "curry"
    var spiciness: String = "mild"

    val spicinessLevel: Int
        get() =
            when (spiciness) {
                "mild" -> 5
                else -> 0
            }

    fun printSimpleSpiceInfo() {
        println("Spice: $spiceName, spiciness:$spiciness, spicinessLevel: $spicinessLevel")
    }
}

fun main() {
    val spice1 = SimpleSpice()
    spice1.printSimpleSpiceInfo()
}