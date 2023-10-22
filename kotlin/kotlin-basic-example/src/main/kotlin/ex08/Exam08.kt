package ex08

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @author        : Rene
 * @since         : 2023/10/22
 */
fun main() {

    Exam08(Store())
}

data class Store(
    // data 클래스를 만들 때부터 null일 수 있음을 명시해줌
    var registeredAt: LocalDateTime ?= null

)

class Exam08{

    constructor(store: Store){
    }

    // 메서드에서 반환 -> 콜론으로 -> 뭘 리턴하는지는 마지막에 표현함
    fun toLocalDateTimeString(localDateTime: LocalDateTime?):String{

        // 이것만으로 변수 세팅이 됨
//        var temp = localDateTime ?: LocalDateTime.now()

        // ?을 작성하면 ->
//        return localDateTime?.format(DateTimeFormatter.ofPattern("yyyy MM dd"))

        // 합치면 ->
        return (localDateTime ?: LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy MM dd"))
    }
}