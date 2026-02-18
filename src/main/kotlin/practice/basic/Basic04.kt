package practice.basic

import java.util.UUID

/**
 *
 * Dealing with operator
 *
 * */

fun main() {
    compareAddress()
    val money1 = Money(1_000L)
    val money2 = Money(1_000L)

    val money = money1 + money2
    println(money.amount)
}

fun compareAddress() {
    val money1 = Money(1_000L)
    val money2 = money1
    val money3 = Money(1_000L)

    //true
    println(money1 === money2)
    //false
    println(money1 === money3)
    //true
    println(money1 == money3)
}

class Money(
    val amount: Long
) {
    // plus -> +
    // minus -> -
    // times -> *
    // equals -> ==
    // get set -> a[o] -> a.get(i) or a.set(i, value)
    // contains -> in
    operator fun plus(money: Money): Money {
        return Money(amount + money.amount)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if  (other !is Money) {
            return false
        }

        return amount == other.amount
    }

    override fun hashCode(): Int {
        return UUID.randomUUID().hashCode()
    }
}
