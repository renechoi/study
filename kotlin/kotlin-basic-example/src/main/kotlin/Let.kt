import java.time.LocalDateTime

/**
 * @author        : Rene
 * @since         : 2023/10/22
 */
fun main() {
    // let

    val now : LocalDateTime?= null

    // 타입에 대한 제네릭을 받아서 리턴해줌 -> map이랑 비슷
    /**
     * @kotlin.internal.InlineOnly
     * public inline fun <T, R> T.let(block: (T) -> R): R {
     *     contract {
     *         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
     *     }
     *     return block(this)
     * }
     */
    val kst = now?.let{
        // 엘비스 연산자를 통해 있을 때만 처리하도록 함
        println(it)
    }


    // 안에서 사용되는 지역 변수를 설정함
    // 타입도 지정이 가능
    val kst2 = now?.let{localDateTime ->
        println(localDateTime)
    }


    val kst3 = now?.let{localDateTime:LocalDateTime ->
        println(localDateTime)
    }


    // let의 특징 - > 마지막에 있는 것이 리턴됨
    // 스코프 안에서
    // return을 적지 않음


    val kst4 = now?.let {localDateTime ->
        println("")
        "let scope"
    } ?: LocalDateTime.now()



    // let -> 특정한 객체를 받아서 마지막 라인을 리턴, default는 it으로 쓰지만 변수명을 쓸 수 있음
}


fun logic(userDto: UserDto?): UserResponse? {
    return userDto?.let {
        println(it)
        UserEntity(
            name = it.name
        )
    }?.let { userEntity ->
        println(userEntity)

        UserResponse(
            name = userEntity.name
        )
    }
}

data class UserDto(
    var name:String?=null,
)

data class UserEntity(
    var name:String?=null
)

data class UserResponse(
    var name:String?=null
)


// map 이랑 let 차이 ?
// map -> collection, let -> 모든 객체

