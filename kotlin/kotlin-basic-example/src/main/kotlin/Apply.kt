/**
 * @author        : Rene
 * @since         : 2023/10/22
 */
fun main() {

    // apply, 생성자 패턴


    // t 라는 제네릭에 함수가 있음 -> t 제네릭 확장 함수
    // block이라는 람다식이 넘어가게 되어 있음
    // 반환형이 없는 Unit이 매개변수로 넘어감

    // also 랑 비교 ? -> also의 경우 타입을 넣어줌 -> it.name으로 접근이 가능
    // apply는 특정 클래스에 function을 추가 -> 확장 함수 람다
    // 람다식이 넘어가는데 그것이 확장함수

    // apply는 초기화를 시킬 때 많이 사용하는데 키워드가 this로 넘어옴 -> 수신 받은 객체 본인

    /**
     * @kotlin.internal.InlineOnly
     * public inline fun <T> T.apply(block: T.() -> Unit): T {
     *     contract {
     *         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
     *     }
     *     block()
     *     return this
     * }
     */

    var userDto = UserDto().apply {
        this.name="홍길동"
        name = "홍길동"
        ""
    }

    // 수신 받은 객체를 리턴하는데, this를 변경할 수는 없음

    // 초기화 블록
    // 마지막 라인을 리턴하는 게 아니고 수신 받은 객체를 그대로 리턴하므로 변경 x


    println(userDto)


    // also 와 유사 -> 수신 받은 객체를 그대로 전달
    // 생성자때 많이 사용한다.
}



fun String.myStr(){
    // 본인에게 접근할 때 this로 접근

}


fun <T> T.myStr():Unit{
    // 즉, T에다가 본인 자신 접근 -> this.
    // 모든 제네릭에다가 apply 적용 모든 제네릭 클래스들이 가지고 있는 기본 메서드 -> 넘겨주는 확장 함수는 이 안에서만 쓸 수 있다.
    // 본인을 던져주기 때문에
    // 즉 apply에 넘어가는 것은 확장 함수가 넘어가면 됨
}


fun UserDto.myUserDto(){
    println(this.name)
}