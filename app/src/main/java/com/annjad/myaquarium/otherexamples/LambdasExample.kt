package com.annjad.myaquarium.otherexamples


/**
 * #PART 1#:
 * Create an enum class, Directions, that has the directions NORTH, SOUTH, EAST and WEST, as well
as START, and END.
 * Create a class Game.
Inside Game, declare a var, path, that is a mutable list of Direction. Initialize it with one element, START.
Create 4 lambdas, north, south, east, and west, that add the respective direction to the path.
Add another lambda, end, that:
Adds END to path
Prints “Game Over”
Prints the path
Clears the path
Returns false
Create a main function.
Inside main(), create an instance of Game.
To test your code so far, in main() print the path, then invoke north, east, south, west, and end.
Finally, print the path again.

================================

 * #PART 2#:
In this practice, you will finish your simple game using higher-order functions, that is, a function that takes functions as an argument.

In the game class, create a function move() that takes an argument called where, which is a lambda with no arguments that returns Unit.

Hint: Declaring a function that takes a lambda as its argument:

fun move(where: () -> Boolean )
Inside move(), invoke the passed-in lambda.
In the Game class, create a function makeMove() that takes a nullable String argument and returns nothing.
Inside makeMove, test whether the String is any of the 4 directions and invoke move() with the corresponding lambda. Otherwise, invoke move() with end.

Hint: You can call the function like this:

move(north)
In main() add a while loop that is always true.

Inside the loop, print instructions to the player:
print("Enter a direction: n/s/e/w:")
Call makeMove() with the contents of the input from the user via readLine()
Remove the code for testing the first version of your game.
Run your program.
Challenge:
Create a simple “map” for your game, and when the user moves, show a description of their location. Consider the following:

Use a Location class that takes a default width and height to track location. 4x4 is pretty manageable.
You can create a matrix like this:
val map = Array(width) { arrayOfNulls<String>(height) }
Use an init block to initialize your map with descriptions for each location.
When you move() also updateLocation(). There is some math involved to prevent null-pointer exceptions and keep the user from walking off the map. rem() and absoluteValue come handy.
When you are done, zip up the code and send it to a friend to try your first Kotlin game.*/

enum class Direction {
    NORTH, SOUTH, EAST, WEST, START, END
}

class Game {

    //#PART 1# beginning
    var path: MutableList<Direction> = mutableListOf(Direction.START)

    var isGameOver = false

    val north = {
        path.add(Direction.NORTH)
    }
    val south = {
        path.add(Direction.SOUTH)
    }
    val east = {
        path.add(Direction.EAST)
    }
    val west = {
        path.add(Direction.WEST)
    }
    val end = {
        path.add(Direction.END)
        println("Complete path before game end: $path")
        path.clear()
        isGameOver = true
        false
    }
    //#PART 1# end

    // The end direction lambda adds a move to the path, and returns a boolean false which indicates
    // if the game is still going or it's over, that's why where has to also return a boolean
    fun move(where: () -> Boolean) {
        where()
    }

    fun makeMove(moveDirection: String?) {
//        if(!moveDirection.isNullOrEmpty()){
//            if(moveDirection.equals(Direction.NORTH.toString(), true))
//        }

        when (moveDirection) {
            "n" -> move(north)
            "s" -> move(south)
            "e" -> move(east)
            "w" -> move(west)
            else -> move(end)
        }
    }
}

fun main() {

    val testingForPart1 = false
    //#PART 1# beginning
    val game = Game()
    if (testingForPart1) {
        println(game.path)
        game.north()
        game.south()
        game.east()
        game.west()
        game.end()
        println(game.path)
    }

    //#PART 1# end

    else {
        while (!game.isGameOver) {
            print("Enter a direction: n/s/e/w:")
            game.makeMove(readLine())
            println(game.path)
        }
    }

}