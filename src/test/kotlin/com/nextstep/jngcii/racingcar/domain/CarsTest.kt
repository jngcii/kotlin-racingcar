package com.nextstep.jngcii.racingcar.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class CarsTest {

    @ParameterizedTest
    @MethodSource("arguments")
    fun `특정 거리만큼 간 차들 확인 테스트`(carA: Car, carB: Car, carC: Car, distance: Int, expected: List<String>) {
        val cars = Cars(carA, carB, carC)

        assertThat(
            cars.filterBy(distance).map { it.name }
        ).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun arguments() = listOf(
            Arguments.of(
                Car(name = "A").goBy(1),
                Car(name = "B").goBy(2),
                Car(name = "C").goBy(3),
                3,
                listOf("C")
            ),
            Arguments.of(
                Car(name = "A").goBy(2),
                Car(name = "B").goBy(1),
                Car(name = "C").goBy(2),
                2,
                listOf("A", "C")
            ),
            Arguments.of(
                Car(name = "A").goBy(1),
                Car(name = "B").goBy(1),
                Car(name = "C").goBy(1),
                1,
                listOf("A", "B", "C")
            ),
        )

        private fun Car.goBy(distance: Int): Car {
            repeat(distance) {
                this.goOrStayByDiceCondition()
            }
            return this
        }
    }
}
