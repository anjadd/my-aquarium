package com.annjad.myaquarium

interface PersonInfoProvider {
    val providerInfo: String

    fun printInfo(person: Person) {
        println(providerInfo)
        person.printPersonInfo()
    }
}

/**
 * When you extend a class, there’s an error that the type of the parent class is final,
 * so it can’t be inherited from. This is characteristic for classes in Kotlin, which
 * by default are closed, meaning they cannot be inherited from (or extended).
 * So to extend a parent class, you need to add an “open” keyword before the class declaration.  */
open class BasicInfoProvider : PersonInfoProvider, SessionInfoProvider {
    override val providerInfo: String
        get() = "Basic Info Provider"

    /** To override a property from the parent class into the child class, the property
     * needs to be marked as “open”. But one problem with this is that by doing so,
     * you’re marking the property as public and it’s available to the public API.
     * Instead, it should be only available to the parent and child classes, and you
     * can accomplish this by marking it also as protected. */
    protected open val sessionIdPrefix = "Session"

    override fun getSessionId(): String {
        return sessionIdPrefix
    }
}

interface SessionInfoProvider {
    fun getSessionId(): String
}


fun main() {
    //Instantiating the BasicInfoProvider interface
    val provider = BasicInfoProvider()

    provider.printInfo(Person())
    provider.getSessionId()

    checkTypes(provider)

    val fancyInfoProvider = FancyInfoProvider()
    //Can't access the sessionIdPrefix after it's been marked as protected
    //fancyInfoProvider.sessionIdPrefix

    //you can create an instance of an anonymous inner class, by using an object expression
    val objectInfoProvider = object : PersonInfoProvider {
        override val providerInfo: String
            get() = "Object Provider Info"

        //you can even create your own functions in an anonymous inner class
        fun getObjectSessionId() = "id"
    }

    objectInfoProvider.printInfo(Person())
    objectInfoProvider.getObjectSessionId()
}

fun checkTypes(infoProvider: PersonInfoProvider) {
    if (infoProvider is SessionInfoProvider) {
        //Type casting
        (infoProvider as SessionInfoProvider).getSessionId()
        //Smart casting
        infoProvider.getSessionId()
        println("Info provider is of type Session Info Provider")
    } else {
        println("Info provider is not of type Session Info Provider")
    }
}

