package com.annjad.myaquarium

fun main(args: Array<String>) {
    for (i in 1..10)
        feedTheFish()

    testFitMoreFish()

}

fun feedTheFish() {
    val day: String = getRandomDay()
    val food: String = fishFood(day)
    println("Today is $day and the fish eat $food")
    if (shouldChangeWater(day))
        println("Change the water today")
    if (shouldChangeWater(day, 27, 55))
        println("Change the water today")
    if (shouldChangeWater(day = day, temperature = 27, dirty = 55))
        println("Change the water today")
}

fun getRandomDay(): String {
    val days = listOf(
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
        "Saturday", "Sunday"
    )
    return days.random()
}

//Return different kind of food for each day
fun fishFood(day: String): String {
    return when (day) {
        "Monday" -> "flakes"
        "Tuesday" -> "pellets"
        "Wednesday" -> "redworms"
        "Thursday" -> "granules"
        "Friday" -> "mosquitoes"
        "Saturday" -> "lettuce"
        "Sunday" -> "plankton"
        else -> "breadcrumbs"
    }
}

fun shouldChangeWater(day: String, temperature: Int = 22, dirty: Int = 20): Boolean {
    //When statement without a parameter for the when, acts as a sequence of if/else checks:
    // This code reads: when temperature > 30, return true, else if dirty > 30 return true, etc.
    return when {
        temperature > 30 -> true
        dirty > 30 -> true
        day == "Sunday" -> true
        else -> false
    }
}

fun shouldChangeWater2(day: String, temperature: Int = 22, dirty: Int = 20): Boolean {
    val isTooHot = temperature > 30
    val isDirty = dirty > 30
    val isSunday = day == "Sunday"

    return when {
        isTooHot -> true
        isDirty -> true
        isSunday -> true
        else -> false
    }
}

fun shouldChangeWater3(day: String, temperature: Int = 22, dirty: Int = 20): Boolean {
    return when {
        isTooHot(temperature) -> true
        isDirty(dirty) -> true
        isSunday(day) -> true
        else -> false
    }
}

fun isTooHot(temperature: Int) = temperature > 30
fun isDirty(dirty: Int) = dirty > 30
fun isSunday(day: String) = day == "Sunday"

fun getDirtySensorReading(): Int = 20
fun shouldChangeWater4(
    day: String,
    temperature: Int = 22,
    dirty: Int = getDirtySensorReading()
): Boolean {
    return when {
        isTooHot(temperature) -> true
        isDirty(dirty) -> true
        isSunday(day) -> true
        else -> false
    }
}


fun testFitMoreFish() {
    println("Can you fit more fish in these tanks?")

    println(fitMoreFish(10.0, listOf(3, 3, 3)))
    println(fitMoreFish(8.0, listOf(2, 2, 2), hasDecorations = false))
    println(fitMoreFish(9.0, listOf(1, 1, 3), 3))
    println(fitMoreFish(10.0, listOf(), 7, true))
}

/**Create a function that checks if we can add another fish into a tank that already has fish in it.
 * Typically, a tank with decorations can contain a total length of fish (in inches) less than or equal to 80% of the tank size (in gallons).
 * A tank without decorations can contain a total length of fish up to 100% of the tank size.
 *
 * A 10 gallon tank with decorations can hold up to 8 inches of fish, for example 4 x 2-inch-long fish.
 * A 20 gallon tank without decorations can hold up to 20 inches of fish, for example 6 x 3-inch-long fish and 1 x 2-inch-long fish.*/
fun fitMoreFish(
    tankSizeGallons: Double,
    currentFishLengths: List<Int>,
    newFishSize: Int = 2,
    hasDecorations: Boolean = true
): Boolean {
    val tankSizeInches: Int =
        if (hasDecorations) (tankSizeGallons * 0.8).toInt() else tankSizeGallons.toInt()

    var totalFishLength: Int = 0
    currentFishLengths.forEach { fishLength -> totalFishLength += fishLength }
    //The above forEach loop which sums all the elements in a list can be replaced with:
    var totalFishLength2 = currentFishLengths.sum()

    val freeTankSize = tankSizeInches.minus(totalFishLength)

    println("tankSizeInches: $tankSizeInches, totalFishLength: $totalFishLength, freeTankSize: $freeTankSize, newFishSize: $newFishSize")
    println("result: ${freeTankSize.minus(newFishSize)}")

    return freeTankSize.minus(newFishSize) >= 0

    /**
     * All the code above in this function can be replaced with this one line
    val result = tankSizeGallons * (if(hasDecorations) 0.8 else 1.0) >= currentFishLengths.sum().plus(newFishSize)
     */
}