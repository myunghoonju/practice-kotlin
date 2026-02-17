package com.group.libraryapp.calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class CalculatorTest {

    @Test
    fun 더하기_테스트() {
        //given
        val cal = Calculator(5)

        //when
        cal.add(3)

        //then
        assertThat(cal.number).isEqualTo(8)
    }

    @Test
    fun 빼기_테스트() {
        //given
        val cal = Calculator(5)
        //when
        cal.subtract(3)
        //then
        assertThat(cal.number).isEqualTo(2)
    }

    @Test
    fun 곱하기_테스트() {
        //given
        val cal = Calculator(5)
        //when
        cal.multiply(3)
        //then
        assertThat(cal.number).isEqualTo(15)
    }

    @Test
    fun 나누기_테스트() {
        //given
        val cal = Calculator(5)
        //when
        cal.divide(3)
        //then
        assertThat(cal.number).isEqualTo(1)
    }

    @Test
    fun 나누기_오류_테스트() {
        val cal = Calculator(5)

        var message = assertThrows<IllegalArgumentException> {
            cal.divide(0)
        }.message

        assertThat(message).isEqualTo("Division by zero")
    }
}