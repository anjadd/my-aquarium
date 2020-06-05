package com.annjad.myaquarium

interface PersonInfoProvider {
    val providerInfo: String

    fun printInfo(person: Person) {
        println(providerInfo)
        person.printPersonInfo()
    }
}

interface SessionInfoProvider {
    fun getSessionId(): String
}

class BasicInfoProvider : PersonInfoProvider, SessionInfoProvider {
    override val providerInfo: String
        get() = "Basic Info Provider"

    override fun getSessionId(): String {
        return "Session 1"
    }
}