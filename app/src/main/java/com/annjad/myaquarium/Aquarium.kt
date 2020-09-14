package com.annjad.myaquarium

class Aquarium(var width: Int = 20, var height: Int = 40, var length: Int = 100) {
/*
    var width: Int = 20
    var height: Int = 40
    var length: Int = 100*/

    var volume: Int
        get() = width * height * length / 1000
        //When you are given a new volume, at least one of the other properties (width, height or
        // length) has to change. Example: change the height when a new volume is given.
        // The formula is: height = volume * 1000 / width * height * length
        set(value) {
            height = (value * 1000) / (width * length)
        }

    var water = volume * 0.9


    fun printAquariumInfo() = println(
        "Aquarium details: Width = $width cm, " +
                "height = $height cm, length = $length cm, volume = $volume liters"
    )

    constructor(numberOfFish: Int) : this()

}

fun main() {
    val aquarium = Aquarium()
    aquarium.printAquariumInfo()

    val smallAquarium = Aquarium(20, 15, 30)
    smallAquarium.printAquariumInfo()

    smallAquarium.volume = 18
    println("The aquarium details with a volume of ${smallAquarium.volume} liters are:")
    smallAquarium.printAquariumInfo()

    val fishAquarium = Aquarium(5)
    println("Fish aq")
    fishAquarium.volume = 8
    fishAquarium.printAquariumInfo()
}