package com.nextstep.jngcii.step2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SimpleCalculatorTest {
    private val calculator = SimpleCalculator()

    @ParameterizedTest
    @CsvSource(
        "1, false, 2, false, +,  3, false",
        "-1, false, -2, false, +, -3, false",
        "1, false, 2, true, +, 3, true",

        "3, false, 2, false, -, 1, false",
        "-1, false, -2, false, -, 1, false",
        "1, false, 2, true, -, -1, true",

        "3, false, 2, false, *, 6, false",
        "-1, false, -2, false, *, 2, false",
        "-1, false, 2, true, *, -2, true",

        "3, false, 1, false, /, 3, false",
        "-2, false, -2, false, /, 1, false",
        "-4, false, 2, true, /, -2, true",
    )
    fun `계산 테스트`(
        leftNumber: Int,
        leftIsEnd: Boolean,
        rightNumber: Int,
        rightIsEnd: Boolean,
        operatorString: String,
        resultNumber: Int,
        resultIsEnd: Boolean,
    ) {
        val left = Operand(leftNumber, leftIsEnd)
        val right = Operand(rightNumber, rightIsEnd)
        val operator = Operator.of(operatorString)

        val result = calculator.run(left, right, operator)

        assertThat(result.value).isEqualTo(resultNumber)
        assertThat(result.isEnd).isEqualTo(resultIsEnd)
    }
}
