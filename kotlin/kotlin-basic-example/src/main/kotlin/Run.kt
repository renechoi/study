import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @author        : Rene
 * @since         : 2023/10/22
 */

fun main(){
    // run , 지역 scope

    /**
     * @kotlin.internal.InlineOnly
     * public inline fun <T, R> T.run(block: T.() -> R): R {
     *     contract {
     *         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
     *     }
     *     return block()
     * }
     */


    // let 함수와 동일하게 마지막에 있는 게 리턴된다.
    // 확장 함수를 전달 받아 안에 있는 것을 바로 접근 가능하다.

    val userDto = UserDto("").run {
        name = "홍길동"
        "empty"
    }

    println("result $userDto")

    val x = 10


    // 주로 지역 스코프를 만들 때 많이 사용된다.
    // 안에 있는 x를 통해서 연산이 됨
    val sum = run {
        val x = 2
        val y = 3
        x + y
    }

    println(sum)

    val now: LocalDateTime? = null

    val n = now?.let { it }?: LocalDateTime.now()

    val d = now?.let {
        val minusTime = it.minusDays(1)
        minusTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        // 특정 스코프를 만들어 주어야 할 때
        // 위에서는 로직이 필요 없음 -> 로직을 만들어야 한다면 스코프를 만들어야 하기 때문에 run을 만들고 사용하고자 하는 로직을 넣는다.
    }?: run {
        val now = LocalDateTime.now()
        val minusTime = now.minusDays(1)
        minusTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
    }

    println("result $d")
}