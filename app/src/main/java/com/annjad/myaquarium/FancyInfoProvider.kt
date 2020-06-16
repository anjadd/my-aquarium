package com.annjad.myaquarium

class FancyInfoProvider : BasicInfoProvider() {
    override val providerInfo: String
        get() = "Fancy Info Provider"

    override fun printInfo(person: Person) {
        super.printInfo(person)
        println("Fancy Info, $sessionIdPrefix")
    }

    override val sessionIdPrefix: String
        get() = super.sessionIdPrefix + " Fancy"
}


fun main() {
    val provider = FancyInfoProvider()
    provider.printInfo(Person())
}