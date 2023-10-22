package ex01

/**
 * @author        : Rene
 * @since         : 2023/10/22
 */
fun main() {


    // var -> mutable, val -> final(불변)

    // : [타입]
    val name: String = "홍길동"
    var _name: String = "홍길동"
    val n = "홍길동" // <- 타입 추론

    // 코틀린은 primitive 타입이 없고 전부 레퍼런스 타입 -> 모든 것을 객체로 관리한다.
    var i = 10
    var _i: Int = 10

    var d: Double = 20.0


    println("사용자의 이름은: $name")



}