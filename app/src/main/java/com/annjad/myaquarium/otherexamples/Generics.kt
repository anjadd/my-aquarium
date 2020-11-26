package com.annjad.myaquarium.otherexamples

/** Generic types allow you to write a general, generic class (or method) that works with
 * different types, allowing for code re-use.
 *
 * For example, if you wanted to define a List class in Kotlin, you’ll need to make one
 * class for each data type (one class that will hold integers, one for strings, another
 * for longs, etc.). Without generics, you’ll need to make a class for each type.
 */
class MyIntList {
    var list = mutableListOf<Int>()
    fun addItem(item: Int) {
        list.add(item)
    }
}

class MyLongList {
    var list = mutableListOf<Long>()
    fun addItem(item: Long) {
        list.add(item)
    }
}

class MyStringList {
    var list = mutableListOf<String>()
    fun addItem(item: String) {
        list.add(item)
    }
}

/** On the other hand, with generics, you can make the list generic, so it can hold any type of object.
 * The generic type is “T”, but you can use any letter or a longer name.
 * T is just a convention for type.
 *
 * To define a generic type, you put “T” inside angle brackets after the class name.
 * To use a generic class, you specify the type you want to use in angle brackets when you
 * instantiate the class.
 *
 * Example: Create a list class that will have a method for adding items to a list of any type.
 */
class MyList<T> {
    var list = mutableListOf<T>()
    fun addItem(item: T) {
        list.add(item)
    }
}

/**To use a generic class, you specify the type you want to use in angle brackets
 * when you instantiate the class, val intList: MyList<Int> */
fun workingWithMyList() {
    val intList: MyList<Int> = MyList()
    intList.addItem(5)
    println(intList.list)
}


fun main() {
//    workingWithMyList()
    genericExample()
}

/**The class WaterSupply represents the water which will be used for filling the Aquarium.
 * The water can be a tap water, lake water, and store bought fish water.
 * The tap water should be processed by adding chemicals and afterwards it's clean.
 * The lake water should be filtered with a filter, but after that it doesn't need to be processed anymore.
 * The store bought fish water doesn't need to be processed.
 * */
open class WaterSupply(var waterNeedsProcessing: Boolean)

class TapWater : WaterSupply(true) {
    fun addChemicalCleaners() {
        waterNeedsProcessing = false
    }
}

class LakeWater : WaterSupply(true) {
    fun filter() {
        waterNeedsProcessing = false;
    }
}

class FishStoreWater : WaterSupply(false)

/**Create a generic Aquarium with a property waterSupply of a generic type */

//Defining a class as Aquarium<T> could be a problem, because you are able to pass
// any type in or or even pass a null
//class Aquarium<T>(val waterSupply: T)     PROBLEM!

class Aquarium<out T : WaterSupply>(val waterSupply: T) {

    fun addWater() {
        if (!waterSupply.waterNeedsProcessing) {
            println("Adding water from $waterSupply")
        } else println("Water needs processing first!")
    }

    fun addWaterUsingCheck() {
        /**Check acts as an assertion that its argument is true, because if it isn’t it will
         * throw an exception.
         * The error: Exception in thread "main" java.lang.IllegalStateException: Adding water
         * from TapWater@511d50c0
         *
         * The code below checks if the condition 'water is filtered' is true
         * (or waterNeedsProcessing is false) and if it's true, goes on to the next line of code
         * printing that water is added.
         * Otherwise, if the condition 'water is filtered' is false (or waterNeedsProcessing is true),
         * it will throw an exception with the provided message ("Water needs processing first!")
         * as an error.
         */
        check(!waterSupply.waterNeedsProcessing) { "Water needs processing first!" }
        println("Adding water from $waterSupply")
    }

    fun addWaterFromCleaner(cleaner: Cleaner<T>) {
        if (waterSupply.waterNeedsProcessing) {
            cleaner.clean(waterSupply)
        }
    }

    fun getWaterType(): T {
        return waterSupply
    }

    /**Use reflection to get information about the class at runtime.
     * The syntax is: ‘:: class’ operation.
     * This lets Kotlin look into the class's properties or in other words reflect.*/
    fun aquariumInfo() =
        "The aquarium is of the following Water supply type: ${waterSupply::class.simpleName}"
}

/**Create an interface Cleaner that let's you clean different water supplies.
 * Since you're only using the cleaner's generic type T as an argument to method clean(),
 * you can make it an in type.*/
interface Cleaner<in T : WaterSupply> {
    fun clean(waterSupply: T)
}

/**In order to use the cleaner interface, create a class that implements Cleaner for cleaning
 * tap water by adding chemicals. */
class TapWaterCleaner : Cleaner<TapWater> {
    override fun clean(waterSupply: TapWater) {
        waterSupply.addChemicalCleaners()
    }

}

/** Create a method isWaterClean that takes an aquarium.
 * You can create a function that takes an Aquarium parameter, but this means that the class
 * Aquarium must have an out type parameter, for this function to be called.*/
fun isWaterClean1(aquarium: Aquarium<WaterSupply>) {
    println(
        if (aquarium.waterSupply.waterNeedsProcessing) "The water in the aquarium is NOT clean!"
        else "The water in the aquarium IS clean."
    )
}

/**Create a method isWaterClean with a generic argument T and T is constrained by WaterSupply.
 * That means that T is a type parameter, to this 'isWaterClean()' function, which is used for
 * specifying the generic type of the aquarium.
 * */
fun <T : WaterSupply> isWaterClean(aquarium: Aquarium<T>) {
    println(
        if (aquarium.waterSupply.waterNeedsProcessing) "The water in the aquarium is NOT clean!"
        else "The water in the aquarium IS clean."
    )
}

fun genericExample() {
    //this is an aquarium of tap water
    val aquarium = Aquarium<TapWater>(TapWater())

//    aquarium.addWater()

//    aquarium.waterSupply.addChemicalCleaners()

//    aquarium.addWater()

    aquarium.waterSupply.addChemicalCleaners()
    aquarium.addWaterUsingCheck()

    val aquarium2: Aquarium<LakeWater> = Aquarium(LakeWater())
    //filter the lake water first, so the check won't throw an exception
    aquarium2.waterSupply.filter()
    aquarium2.addWaterUsingCheck()

    val cleaner = TapWaterCleaner()
    val aquarium3 = Aquarium<TapWater>(TapWater())
    aquarium3.addWaterFromCleaner(cleaner)

    aquarium2.getWaterType()

    val aquarium4 = Aquarium(LakeWater())
    isWaterClean(aquarium4)
    println(aquarium4.aquariumInfo())   //The aquarium is of the following Water supply type: LakeWater


    checkInternetConnection()

    /**You can even pass a String to Aquarium, or create an aquarium of type String.
     * So with this, you are able to pass a String in, as a water supply.
     * This is allowed, because the T type doesn't have any bounds, so it can be set
     * to any type or even to a null(even though it is a type 'T', not a 'T?'),
     * and that could be a problem.*/
    //val aquarium2 = Aquarium<String>("some water")    PROBLEM!
    //println(aquarium2.waterSupply)

    //val aquarium3 = Aquarium(null)  PROBLEM!

    petShopGenericsExample()

    makingBurgersGenericInOutExample()

    genericFunctionsExample()
}

/**Type T by default stands for the ‘nullable Any’ type, which is the type
 * at the top of the type hierarchy*/
class MyGenericClass<T : Any?>

/**To ensure that your generic class parameter must be non-null, but can still be any type,
 * you can remove the ‘?’*/
class MyGenericClass2<T : Any>

/**To furthermore ensure that your generic class parameter must be of some specific type,
 * you can add a generic constraint and replace ‘Any’ with a top of some type hierarchy
 * you want to use.*/
class MyGenericClass3<T : WaterSupply>


/**The function check(Boolean) throws IllegalStateException when its argument is false.
 * So, checks acts as an assertion that its argument is true, because if it isn’t it
 * will throw an exception.
 * The code below checks if the condition 'isConnected' is true and if it's true,
 * goes on to the next line of code printing 'The device is successfully connected to the Internet'.
 * Otherwise, if the condition is false,it will throw an exception with the provided message
 * ('The device is not connected to the Internet!') as an error.*/
fun checkInternetConnection() {
    val isConnected = true
    check(isConnected) { "The device is not connected to the Internet!" }
    println("The device is successfully connected to the Internet.")
}


/**Imagine you are managing a zoo or an exotic pet shop so that you have to organize and
 * keep a reference of all animal species available. Following an object-oriented programming
 * approach, you will most likely put each animal in a cage and declare several attributes
 * and actions associated.*/

open class Animal(val id: Int, val name: String, val furColor: FurColor)

class Dog(id: Int, name: String, furColor: FurColor) : Animal(id, name, furColor)

class Cat(id: Int, name: String, furColor: FurColor) : Animal(id, name, furColor)

class Cage<T : Animal>(var animal: T, val size: Double) {
    fun printAnimalInfo() {
        println(
            "Animal: ${animal::class.simpleName}. Info: name = ${animal.name}, " +
                    "fur: ${animal.furColor.toString().toLowerCase()}, cage size: $size "
        )
    }
}

enum class FurColor {
    WHITE, BLACK, BROWN, GREY, MIX, GOLD
}

fun petShopGenericsExample() {
    val dog1 = Dog(1, "Astor", FurColor.GOLD)
    val dogCage1: Cage<Dog> = Cage(dog1, 6.0)
    dogCage1.printAnimalInfo()

    val cat1 = Cat(2, "Mich", FurColor.GREY)
    val catCage1: Cage<Cat> = Cage(cat1, 3.0)
    catCage1.printAnimalInfo()
}


/**Burger making, create classes and stores for making food.
 * Create Food Production holders.*/
open class Food

open class FastFood : Food()

class Burger : FastFood()

interface Production<out T : Food> {
    //the function produce() returns an object of type T
    fun produce(): T;
}

interface Consumer<in T : Food> {
    fun consume(food: T)
}

class FoodStore : Production<Food> {
    override fun produce(): Food {
        println("Producing food")
        return Food()
    }
}

class FastFoodStore : Production<FastFood> {
    override fun produce(): FastFood {
        println("Producing fast food")
        return FastFood()
    }
}

class BurgerStore : Production<Burger> {
    override fun produce(): Burger {
        println("Producing burger fast food")
        return Burger()
    }
}

class Everybody : Consumer<Food> {
    override fun consume(food: Food) {
        println("Eating food")
    }
}

class ModernPeople : Consumer<FastFood> {
    override fun consume(food: FastFood) {
        println("Eating fast food")
    }
}

class Americans : Consumer<Burger> {
    override fun consume(food: Burger) {
        println("Eating burgers")
    }
}

fun makingBurgersGenericInOutExample() {

    val cons1: Consumer<Food> = Everybody()
    val cons2: Consumer<Burger> = Everybody()

    val store1: Production<Food> = FoodStore()
    val store2: Production<FastFood> = FastFoodStore()
    val store3: Production<Burger> = BurgerStore()

    /**For 'out' generic, you could assign a class of subtype to class of super-type
     * FoodStore produces food, FastFoodStore produces fast food and BurgerStore
     * produces burgers.
     * But, because fast food and burgers are also of type Food, they can be
     * assigned to a super type class.*/
    val store4: Production<Food> = FoodStore()
    val store5: Production<Food> = FastFoodStore()
    val store6: Production<Food> = BurgerStore()


    val store7: Production<Food> = FastFoodStore()
    //This will throw an error, because the fast food store is not just a burger store
    // (it produces other types of fast food).
    //val store8: Production<Burger> = FastFoodStore()  ERROR!!!

    val consumer1: Consumer<Food> = Everybody()
    val consumer2: Consumer<FastFood> = ModernPeople()
    val consumer3: Consumer<Burger> = Americans()

    /**For 'in' generic, you could assign a class of super-type to class of subtype.
     * A burger consumer is an American, who is also part of ModernPeople, who is part of Everybody.
     * Everybody eats food, meaning they eat burgers too, the same applies with Modern people.
     */
    val consumer4: Consumer<Burger> = Everybody()
    val consumer5: Consumer<Burger> = ModernPeople()
    val consumer6: Consumer<Burger> = Americans()
}

/**Create a generic function combineNumbersToString() with a generic type T which is a subtype
 * of Number. It accepts 3 numbers and combines them into a String*/
fun <T : Number> combineNumbersToString(num1: T, num2: T, num3: T): String {
    return "$num1$num2$num3"
}

fun genericFunctionsExample() {
    val someNumbers = arrayOf(4, 72, 3.8)
    val combinedNumbers = combineNumbersToString(someNumbers[0], someNumbers[1], someNumbers[2])
    println("The combination of the numbers ${someNumbers[0]}, ${someNumbers[1]} and ${someNumbers[2]} is $combinedNumbers")
}

