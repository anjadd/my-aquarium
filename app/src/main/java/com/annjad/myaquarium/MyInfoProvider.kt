package com.annjad.myaquarium

/**
 * This is just my exercise test for the interface and classes, it's the same as PersonInfoProvider*/
interface MyInfoProvider {
    fun printInfoProvider(person: Person)
}

interface PrintsClassesThatImplementIt {
    val classThatImplementsThisInterface: String
    fun printClassesThatImplementIt(person: Person) {
        println(classThatImplementsThisInterface)
        person.printPersonInfo()
    }
}

class InfoProvider : MyInfoProvider {
    override fun printInfoProvider(person: Person) {
        person.printPersonInfo()
    }
}

class SecondInfoProvider : PrintsClassesThatImplementIt {
    override val classThatImplementsThisInterface: String
        get() = "Second Info Provider"
}

fun main() {
    val infoProvider = InfoProvider()
    /**The code below will print out:
     * Person details: Peter  Parker, age: unknown */
    infoProvider.printInfoProvider(Person())

    val secondInfoProvider = SecondInfoProvider()
    secondInfoProvider.printClassesThatImplementIt(Person())
}