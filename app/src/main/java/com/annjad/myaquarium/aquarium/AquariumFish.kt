package com.annjad.myaquarium.aquarium

abstract class AquariumFish {
    abstract val color: String
    abstract fun eat()
}

class Tuna : AquariumFish() {
    override val color: String
        get() = "blue"

    override fun eat() {
        println("Tuna eats swarm fish like herring, sardines, anchovies, shrimps")
    }
}

class Salmon : AquariumFish(), FishAction {
    override val color: String
        get() = "silver"

    override fun eat() {
        println("Salmon eats planktons, insects and batfish")
    }
}

/**These 2 interfaces are created from the abstract class AquariumFish.So instead of
 * creating an abstract class and inheriting from it, you break down the big abstract
 * class into multiple interfaces. And your class then will implement the different interfaces*/

interface FishAction {
    fun eat()
}

interface FishColor {
    val color: String
}

class Sardine : FishAction, FishColor {
    override fun eat() {
        println("Sardines eat algae")
    }

    override val color: String
        get() = "dark blue"
}

/**You can use an interface delegation to provide a color implementation.
 * To do that, you need an object that knows how to provide a fish color.
 * It doesn't make sense to make multiple instances of GoldColor as they would
 * all do the exact same thing, so Kotlin lets you declare a class where you can
 * only have one instance of it, by using the keyword “object” instead of “class”. */

object GoldColor : FishColor {
    override val color: String
        get() = "gold"
}

class GoldFishOld : FishAction, FishColor {
    override fun eat() {
        println("Goldfishes eat algae")
    }

    override val color: String
        get() = "gold"
}

/**use the interface delegation by using the keyword “by” and remove the member variable.
 * This means implement the interface fish color by submitting all calls to the object gold color. */
class GoldFish : FishAction, FishColor by GoldColor {
    override fun eat() {
        println("Goldfishes eat algae")
    }
}

/**But there are different colors of Goldfish in the world (like gold, orange, yellow),
 * so you can make the fish color object a constructor parameter with a default value of
 * gold color and submit calls to the color properties to whatever fish color you get passed in. */
class GoldFish2(customFishColor: FishColor = GoldColor) : FishAction,
    FishColor by customFishColor {
    override fun eat() {
        println("Goldfishes eat algae")
    }
}

class DarkBlueColor : FishColor {
    override val color = "dark blue"

}


/**Use an interface if you have a lot of methods and 1-2 implementations of methods*/
interface AquariumAction {
    fun eat()
    fun jump()
    fun clean()
    fun catchFish()

    /**This is an implementation of a method, known as default implementation*/
    fun swim() {
        println("swim")
    }
}

fun main() {
    fun makeFish() {
        val tuna = Tuna()
        val salmon = Salmon()

        println("Fish created! \n Tuna: ${tuna.color} color \n Salmon: ${salmon.color} color.")

        tuna.eat()
        salmon.eat()

        val sardine = Sardine()
        sardine.eat()

        val goldFish = GoldFish()
        goldFish.eat()
        println("Goldfish has ${goldFish.color} color")
    }

    makeFish()

    fun feedFish(fish: FishAction) {
        fish.eat()
    }

    fun makeTuna() {
        val tuna = Tuna()
        println("Fish has color ${tuna.color}")
        tuna.eat()

    }

    makeTuna()
}