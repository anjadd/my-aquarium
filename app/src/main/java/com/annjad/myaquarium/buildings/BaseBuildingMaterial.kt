package com.annjad.myaquarium.buildings

/**Create a class BaseBuildingMaterial with a property numberNeeded that is set to 1.
 * You always need 1 of the base material.
 * Create two subclasses, Wood and Brick. For BaseBuildingMaterial you need 4 units of wood
 * or 8 units of brick. Now you have a type hierarchy.
 *
 * Create a generic class Building that can take any building material as its argument,
 * and only building materials.
 * A building always requires 100 base materials. Add a property baseMaterialsNeeded = 100.
 * Add another property, actualMaterialsNeeded and use a one-line function to calculate this
 * from numberNeeded of the passed-in material.
 *
 * Add a method build() that prints the type and number of materials needed.
 * Hint: Use reflection to get the class and simple name: instance::class.simpleName
 * Create a main function and make a building using Wood. */
open class BaseBuildingMaterial(val numberNeeded: Int = 1) {
}

class Wood : BaseBuildingMaterial(4)

class Brick : BaseBuildingMaterial(8)

class Building<T : BaseBuildingMaterial>(val buildingMaterial: T) {

    val baseMaterialsNeeded: Int = 100
    val actualMaterialsNeeded: Int = buildingMaterial.numberNeeded * baseMaterialsNeeded

    fun build() {
        val buildingClassName = buildingMaterial::class.simpleName
        println(
            "The material needed for this building is: $buildingClassName " +
                    "and number of materials needed is: $actualMaterialsNeeded"
        )
    }
}


/**Create a generic function for type BaseBuildingMaterial and call it isSmallBuilding,
 * which takes a Building with a building material T as an argument. If the materials needed are
 * less than 500, print "small building", otherwise, print "large building".
 * Note: For this function, IntelliJ recommends not to inline the function.*/

fun <T : BaseBuildingMaterial> isSmallBuilding(building: Building<T>) {
    println(
        "The building made with ${building.buildingMaterial::class.simpleName} is a " +
                "${if (building.actualMaterialsNeeded < 500) "small" else "large"} building"
    )
}


//Generic classes are classes which have type parameters:
class Box<T>(boxType: T) {
    val boxValue = boxType
}

fun main() {
    val building1 = Building<Wood>(Wood())
    building1.build()

    isSmallBuilding(Building(Brick()))

    //To create an instance of a generic class, you need to provide the type arguments:
    val box: Box<Int> = Box<Int>(1)
}