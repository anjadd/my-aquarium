package com.annjad.myaquarium.otherexamples

import java.util.*
import kotlin.random.Random

/**Create a top-level constant for the maximum number of books a person could borrow.*/
const val MAX_BOOKS_TO_BORROW = 20

/**
 * Create a class, Book, with a title and an author.
Add a method, readPage(), that increases the value of a private variable, currentPage, by 1.
Create a subclass of Book; name it eBook.
eBook also takes in a format, which defaults to "text".
In eBooks, counting words makes more sense than pages. Override the readPage() method to
increase the word count by 250, the average number of words per page from typewriter days.*/
open class Book(val title: String, val author: String) {

    var currentPage: Int = 1
    open fun readPage() {
        currentPage++
    }

    var pages: Int = 10

    /**Inside the Book class, create a method canBorrow() that returns true or false
     * depending on whether a user has already borrowed the max number of books. */
    open fun canBorrow(borrowedBooks: Int): Boolean = borrowedBooks < MAX_BOOKS_TO_BORROW

    /**Create a Constants object that provides constants to the book. For this example,provide
     * the BASE_URL for all books in the library catalog. Inside Book, add a method printUrl
     * that creates and prints a URL composed of BASE_URL, the book title, and “.html”.
     * The base URL is really of interest to the Book class. As such, it makes sense to limit
     * its scope to the Book class. Use a companion object to define the constant in Book.*/
    object Constants {
        const val BASE_URL = "http://www.anjaslibrary.com/"
    }

    companion object {
        const val BASE_URL = "http://www.anjaslibrary.com/"
    }

    fun printUrl() {
        val transformedTitle = title.replace(" ", "").toLowerCase(Locale.getDefault())
        val url = "${BASE_URL}${transformedTitle}.html"
        val url2 = "${Constants.BASE_URL}${transformedTitle}.html"
        println("Book url: $url")   //Book url: anjaslibrary/thesubtleartofnotgivingafuck.html
    }
}

class eBook(title: String, author: String, val format: String = "text") : Book(title, author) {
    var wordsRead: Int = 0
    override fun readPage() {
        wordsRead += 250
    }
}

/** Create a book class with a title, author, and year. Of course, you could get each of the properties separately.
Create a method that returns both the title and the author as a Pair.
Create a method that returns the title, author and year as a Triple. Use the documentation to find out how to use Triple.
Create a book instance.
Print out the information about the book in a sentence, such as: “Here is your book X written by Y in Z.”*/
class BookAuthor(val title: String, val author: String, val year: Int) {
    fun getBookAuthorPair(): Pair<String, String> = title to author
    fun getBookAuthorYearTriple(): Triple<String, String, Int> = Triple(title, author, year)
}

/** The class that shouldn't be instantiated multiple times should be declared as a singleton.
 *  A singleton class is a class that can have only one object (an instance of the class)
 *  at a time. You can achieve that by replacing the class keyword with “object”.
 *  For example, there can only be one white color for defining your nail color, one green,
 *  one book called The Subtle art of not giving a f**k by Mark Manson, etc.*/
object BookTheSubtleArtOfNotGivingAF {
    val author = "Mark Manson"
    fun read() {
        println("You are reading the book 'The Subtle Art Of Not Giving A F***' ")
    }
}

interface MyColor {
    val color: String
}

object WhiteColor : MyColor {
    override val color: String = "white"
}

object GreenColor : MyColor {
    override val color: String = "green"
}

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
fun Book.bookWeight() = pages * 1.5

fun Book.tornPages(tornPages: Int) {
    /* Unnecessary check
    if (pages >= tornPages) {
        println("Before torn: Pages = $pages, torn pages: $tornPages")
        pages -= tornPages
        println("After torn: Pages = $pages")
    }*/
    println("Torn pages: $tornPages")
    pages -= tornPages
}

class Puppy {
    fun playWithBook(book: Book) {
        println("Current number of pages: ${book.pages}")
        book.tornPages(Random.nextInt(book.pages + 1))
        println("After tearing some pages, current number of pages: ${book.pages}")
    }
}

fun main() {
    val myeBook = eBook("The Subtle Art of not giving a f**k", "Mark Manson", "audio")
    myeBook.readPage()
    println("Your book: ${myeBook.title} by ${myeBook.author} is in ${myeBook.format} format. Words that you have read: ${myeBook.wordsRead}")

    val bookTheSubtleArt = BookTheSubtleArtOfNotGivingAF
    bookTheSubtleArt.read()

    val whiteColor = WhiteColor
    println(whiteColor)

    /** Pairs are a data type in Kotlin that allows you to define a generic pair of values.
     * The first item in a pair is mapped to the second one by using the keyword “to”.
     * The value of the first item is accessed by using the “.first” keyword and the value
     * of the second item is accessed by using “.second”.
     * You can turn a pair into a String using the .toString() and into a List by .toList() method.*/
    val titlePair: Pair<String, String> = "The Subtle Art" to "Mark Manson"
    println("The title with its author: ${titlePair.first} by ${titlePair.second}")

    println(titlePair.toString())
    println(titlePair.toList())


    val book1 = BookAuthor("The Subtle Artttt", "Mark Manson", 2018)

    val bookAuthorPair: Pair<String, String> = book1.getBookAuthorPair()
    val bookAuthorYearTriple: Triple<String, String, Int> = book1.getBookAuthorYearTriple()

    println("Here is your book ${bookAuthorPair.first}, written by ${bookAuthorPair.second}")
    println(
        "Here is your book ${bookAuthorYearTriple.first}, written by " +
                "${bookAuthorYearTriple.second} in ${bookAuthorYearTriple.third}"
    )
    val bookAuthorYearTriple2: Triple<String, String, Int> =
        Triple("The Subtle Art", "Mark Manson", 2018)

    val myBook1 = Book("The Subtle Art of not giving a fuck", "Mark Manson")
    println("The user has 20 books and he can borrow: ${myBook1.canBorrow(20)}")
    myBook1.printUrl()

    /**Create a puppy and give it a book to play with, until there are no more pages.*/
    val puppy: Puppy = Puppy()
    val bookForPuppy = Book("Star Wars", "Timothy Zahn")
    bookForPuppy.pages = 80
    while (bookForPuppy.pages > 0) {
        puppy.playWithBook(bookForPuppy)
        println("========================================")
    }

}

