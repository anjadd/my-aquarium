package com.annjad.myaquarium.aquarium

import java.util.*

data class Decorations(val rocks: String) {
}

fun makeDecorations() {
    val dec1 = Decorations("granite")
    println(dec1)

    val dec2 = Decorations("slate")
    val dec3 = Decorations("slate")
    println("Is decoration2 ($dec2) equal to decoration3 ($dec3):")
    println(dec2.equals(dec3))

    println("Is decoration1 ($dec1) equal to decoration2 ($dec2):")
    println(dec1.equals(dec2))

    val dec4 = dec3.copy()
    println("Is decoration3 ($dec3) equal to decoration4 ($dec4):")
    println(dec3.equals(dec4))
}

data class Decorations2(val rocks: String, val wood: String, val diver: String) {
}

fun makeDecorations2() {
    val dec5 = Decorations2("crystal", "wood", "diver")
    println(dec5)

    /**You can use object decomposition to print out every property of the object*/
    val (rocks: String, wood: String, diver: String) = dec5
    println(rocks)
    println(wood)
    println(diver)
}

enum class Color(val rgbValue: Int) {
    RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF), YELLOW(0xFFFF00);
}

interface DecorationColor {
    val decorationColor: Color
}

object YellowDecorationColor : DecorationColor {
    override val decorationColor: Color = Color.YELLOW
}

sealed class Decorations3(val rocks: String, val color: DecorationColor) {
}

class YellowDecorations3(
    rocks: String = "rounded rocks",
    color: DecorationColor = YellowDecorationColor
) : Decorations3(rocks, color) {
    init {
        println(
            "The decorations are $rocks and the color is " +
                    color.decorationColor.name.toLowerCase(Locale.getDefault()).capitalize()
        )
    }
}

fun main() {
//    makeDecorations()
//    makeDecorations2()
    val yellowDecorations3 = YellowDecorations3()
}

