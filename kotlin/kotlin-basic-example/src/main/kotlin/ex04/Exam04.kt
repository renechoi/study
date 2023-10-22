package ex04

/**
 * @author        : Rene
 * @since         : 2023/10/22
 */
fun Main() {
    // 코틀린에서도 마찬가지로 key value 형식으로 map을 만들 수 있다. -> 기본형태는 immutable

    // java의 Object -> 코틀린에서는 Any
    // pair라는 문법
    val map = mapOf<String, Any>(
        Pair("", ""),
        "key" to "value"
    )

    // 만약 mutable로 하려면
    val mutableMap = mutableMapOf(
        "key" to "value"
    )

    mutableMap.put("key", "value");
    mutableMap.put("key2", "value2");

    // 이렇게도 쓸 수 있다.
    mutableMap["key"] = "value";


    val value = mutableMap["key"];

    val hashMap = hashMapOf<String, Any>()


    // kotlin에는 triple이라는 자료형태도 있음
    var triple = Triple<String, String, String>(
        first = "",
        second = "",
        third = ""
    )

}