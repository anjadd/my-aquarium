package com.annjad.myaquarium.otherexamples

import kotlin.random.Random

/**
 * Create a class, BookAndPuppy, with a title and an author.
 */
open class BookAndPuppy(val title: String, val author: String, var pages: Int = 10)


/**Extension functions:
 *
 * It can be useful to know the weight of a book, for example, for shipping. The weight of
 * a book can change because sometimes pages get torn, and the page count changes. While
 * calculating the weight could be defined as a method, it’s more like a helper function.
 *
 * Add a mutable property pages to Book.
 * Create an extension function on Book that returns the weight of a book as the page count
 * multiplied by 1.5 grams.
 * Create another extension, tornPages(), that takes the number of torn pages as an argument
 * and changes the page count of the book.
 * Write a class Puppy with a method playWithBook() that takes a book as an argument,
 * and removes a random number of pages from the book.
 * Create a puppy and give it a book to play with, until there are no more pages.*/

fun BookAndPuppy.bookWeight(): Double = pages * 1.5

fun BookAndPuppy.tornPages(tornPages: Int) {
    /* Unnecessary check
    if (pages >= tornPages) {
        println("Before torn: Pages = $pages, torn pages: $tornPages")
        pages -= tornPages
        println("After torn: Pages = $pages")
    }*/
    println("Torn pages: $tornPages")
    pages -= tornPages
}

class PuppyPlayful {

    fun playWithBook(book: BookAndPuppy) {
        println("Current number of pages: ${book.pages}")
        book.tornPages(Random.nextInt(book.pages + 1))
        println("After tearing some pages, current number of pages: ${book.pages}")
    }
}

fun main() {

    /**Create a puppy and give it a book to play with, until there are no more pages.*/
    val puppy = PuppyPlayful()
    val bookForPuppy = BookAndPuppy("Star Wars", "Timothy Zahn", 80)

    while (bookForPuppy.pages > 0) {
        puppy.playWithBook(bookForPuppy)
        println("==========")
    }

}