package practice.basic

/**
 *   - statement: 프로그램의 문장, 하나의 값으로 도출되지 않는다
 *   - expression: 하나의 값으로 도출되는 문장
 *   - 삼항연산자 없음
 *   - switch-case -> when 대체
 * */

//statement
fun validScore(score: Int) {
    if (score !in 0..10) {
        throw IllegalArgumentException("$score is not in the range")
    }
}

//expression
fun passOrFail(score: Int): String {
    return if (score >= 50) {
         "P"
    } else {
         "F"
    }
}

fun switchCondition1(score: Int): String {
    return when (score / 10) {
             9 -> "A"
             8 -> "B"
             7 -> "C"
             else -> "F"
    }
}

fun switchCondition2(score: Int): String {
    return when (score / 10) {
             in 90..100 -> "A"
             in 80..89 -> "B"
             in 70..79 -> "C"
             else -> "F"
    }
}

fun switchCondition3(obj: Any): Boolean {
    return when (obj) {
        is String -> obj.startsWith("a")
        else -> false
    }
}

fun switchCondition4(score: Int): Boolean {
    return when (score) {
        1, 2, 4 -> true
        else -> false
    }
}

fun switchCondition5(score: Int): Boolean {
    return when {
        score == 0 -> false
        score % 2 == 0 -> true
        else -> false
    }
}