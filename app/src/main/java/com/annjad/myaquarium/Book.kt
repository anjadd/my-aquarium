package com.annjad.myaquarium

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
}

class eBook(title: String, author: String, val format: String = "text") : Book(title, author) {
    var wordsRead: Int = 0
    override fun readPage() {
        wordsRead += 250
    }
}

fun main() {
    val myeBook = eBook("The Subtle Art of not giving a f**k", "Mark Manson", "audio")
    myeBook.readPage()
    println("Your book: ${myeBook.title} by ${myeBook.author} is in ${myeBook.format} format. Words that you have read: ${myeBook.wordsRead}")
}