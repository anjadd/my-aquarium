package com.annjad.myaquarium.aquarium

//Kotlin will not create a property for volumeNeeded from the argument in the constructor,
// because it doesn't have a val or var
class Fish(private val friendly: Boolean = true, volumeNeeded: Int) {

    private val size: Int = if (friendly) {
        volumeNeeded
    } else {
        volumeNeeded * 2
    }

    constructor() : this(true, 9) {
        println("Running second constructor")
    }

    fun printFishInfo() = println("Fish details: friendly = $friendly, and it needs volume = $size")

}

fun makeDefaultFish() = Fish(true, 50)

fun main() {
    val fish1 = Fish(true, 20)
    fish1.printFishInfo()

    val fish2 = Fish(false, 30)
    fish2.printFishInfo()

    makeDefaultFish()
}