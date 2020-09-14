package com.annjad.myaquarium

/**Create a new class, Spice.
Pass in a mandatory String argument for the name, and a String argument for the level of spiciness where the default value is mild for not spicy.
Add a variable, spicinessLevel, to your class, with a getter that returns a numeric value for each type of spiciness.
Instead of the list of spices as Strings you used earlier, create a list of Spice objects and give each object a name and a spiciness level.
Add an init block that prints out the values for the object after it has been created. Create a spice.
Create a list of spices that are spicy or less than spicy. Hint: Use a filter and the spicinessLevel property.
Because salt is a very common spice, create a helper function called makeSalt().*/

class Spice(val name: String, val spiciness: String = "mild") {

    val spicinessLevel: Int
        get() =
            when (spiciness) {
                "mild" -> 1
                "medium" -> 3
                "spicy" -> 5
                "very spicy" -> 7
                "extremely spicy" -> 10
                else -> 0
            }

    init {
        println("Spice name: $name, spiciness: $spiciness, spiciness level: $spicinessLevel")
    }
}

fun getLowAndMediumSpicinessSpices(listOfSpices: MutableList<Spice>): List<Spice> {
    return listOfSpices.filter { it.spicinessLevel < 5 }
}

fun makeSalt() = Spice("Salt")


fun main() {
    /*val spices =
        listOf("curry", "pepper", "cayenne", "ginger", "red curry", "green curry", "red pepper")*/
    val curry: Spice = Spice("Curry", "mild")
    val cayenne: Spice = Spice("Cayenne", "mild")
    val ginger: Spice = Spice("Ginger", "spicy")
    val redCurry: Spice = Spice("Red curry", "spicy")

    val spicesList: MutableList<Spice> = mutableListOf<Spice>()

    spicesList.add(curry)
    spicesList.add(cayenne)
    spicesList.add(ginger)
    spicesList.add(redCurry)

    println("The spices with low and medium spiciness are:")
    getLowAndMediumSpicinessSpices(spicesList).forEach { it ->
        println(it.name)
    }

    val saltSpice = makeSalt()

}