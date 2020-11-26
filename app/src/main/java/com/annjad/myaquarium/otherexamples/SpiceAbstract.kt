package com.annjad.myaquarium.otherexamples

/**Make Spice an abstract class (I'll call it SpiceAbstract), and then create some subclasses that are actual spices.

It's easiest (organizationally) if you make a new package, Spices, with a file, Spice, that has a main() function.
Copy/paste your Spice class code into that new file.
Make SpiceAbstract abstract.
Create a subclass, Curry. Curry can have varying levels of spiciness, so we don't want to use the default value, but rather pass in the spiciness value.
Spices are processed in different ways before they can be used. Add an abstract method prepareSpice to Spice, and implement it in Curry.
Curry is ground into a powder, so let's call a method grind(). However, grinding is something that's not unique to curry,
or even to spices, and it's always done in a grinder. So we can create an Interface, Grinder, that implements the grind() method. Do that now.
Then add the Grinder interface to the Curry class.

Add a yellow color to Curry. Create an interface, SpiceColor, that has a color property. You can use a String for the color.
Create a singleton subclass, YellowSpiceColor, using the object keyword, because all instances of Curry and other spices can use the same YellowSpiceColor instance.
Add a color property to Curry of type SpiceColor, and set the default value to YellowSpiceColor.
Add SpiceColor as an interface, and let it be by color.
Create an instance of Curry, and print its color. However, color is actually a property common to all spices, so you can move it to the parent class.
Change your code so that the SpiceColor interface is added to the SpiceAbstract class and inherited by Curry.*/

abstract class SpiceAbstract(val name: String, val spiciness: String = "mild", color: SpiceColor) :
    SpiceColor by color {

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

    abstract fun prepareSpice()
}

class Curry(name: String, spiciness: String, spiceColor: SpiceColor = YellowSpiceColor) :
    SpiceAbstract(name, spiciness, spiceColor),
    Grinder {
    override fun prepareSpice() {
        grind()
    }

    override fun grind() {
        println("Curry grinding")
    }
}

interface Grinder {
    fun grind()
}

interface SpiceColor {
    val spiceColor: String
}

object YellowSpiceColor : SpiceColor {
    override val spiceColor: String = "yellow"

}

object WhiteSpiceColor : SpiceColor {
    override val spiceColor: String
        get() = "white"

}

class Salt(name: String, spiciness: String = "mild", spiceColor: SpiceColor = WhiteSpiceColor) :
    SpiceAbstract(name, spiciness, spiceColor), Grinder {
    override fun prepareSpice() {
        grind()
    }

    override fun grind() {
        println("Salt grinding")
    }
}

/**Create a simple data class, SpiceContainer, that holds one spice.
Give SpiceContainer a property, label, that is derived from the name of the spice.
Create some containers with spices and print out their labels.*/
data class SpiceContainer(var spice: SpiceAbstract) {
    val label: String = spice.name
}

fun getLowAndMediumSpicinessSpices2(listOfSpices: MutableList<SpiceAbstract>): List<SpiceAbstract> {
    return listOfSpices.filter { it.spicinessLevel < 5 }
}

fun main() {

    val spicesList: MutableList<SpiceAbstract> = mutableListOf()

    val curry = Curry("Curry", "medium")
    val salt = Salt("Salt")

    spicesList.add(curry)
    spicesList.add(salt)

    println("The spices with low and medium spiciness are:")
    getLowAndMediumSpicinessSpices2(spicesList).forEach { it ->
        println("${it.name} (${it.spicinessLevel})")
    }

    val spice1 = SpiceContainer(curry)
    println(spice1.label)
    val spice2 = SpiceContainer(salt)
    println(spice2.label)

    //Alternative way of creating a spices list and printing their names:
    val mySpicesList = listOf(
        SpiceContainer(Curry("Yellow Curry", "mild")),
        SpiceContainer(Curry("Red Curry", "medium")),
        SpiceContainer(Curry("Green Curry", "spicy"))
    )

    for (mySpice in mySpicesList) {
        println(mySpice.label)
    }
}