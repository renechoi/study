/**
 * @author        : Rene
 * @since         : 2023/10/22
 */
fun main() {
    // also -> 또한


    // 넘어온 객체를 그대로 리턴 -> map이랑 동일한 부분은 키워드를 it으로 받되 변경 가능
    // 타입도 지정 가능
    // let이랑 차이 ? -? let은 마지막 라인을 리턴
    // also는 받은 파라미터를 그대로 리턴한다.

    /**
     * @kotlin.internal.InlineOnly
     * @SinceKotlin("1.1")
     * public inline fun <T> T.also(block: (T) -> Unit): T {
     *     contract {
     *         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
     *     }
     *     block(this)
     *     return this
     * }
     */


    UserDto(
        name = "홍길동"
    ).also {
        println(it)

        // let 이랑 차이는 받은 것을 그대로 리턴하므로 아래 코드를 리턴하지 않음
        UserDto(name = "유관순")
    }

    // 그대로 넘기므로 로깅 등에 사용

}


