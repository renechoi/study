package ex03

import java.util.Collections

/**
 * @author        : Rene
 * @since         : 2023/10/22
 */
fun main() {
    // 코틀린에서는 불변과 가변을 중요하게 나눔
    // 변수에서처럼 컬렉션에서도 ->

    val userList = mutableListOf<User>()
    userList.add(User("1", 10))
    userList.add(User("2", 10))
    userList.add(User("3", 10))

    // 불변 -> 반드시 초기화때 넣어주어야 함 -> add라는 메서드가 지원을 안함
    // 자바에서는 불변이어도 add가 보이지만
    val list = listOf<User>(
        User("4", 40)
    )

    for(element in userList){
        println(element)
    }

    userList.forEach{it->println(it)}
    userList.forEach{ println(it)}

    // index를 보고 싶은 경우에
    userList.forEachIndexed{index, user -> println("$index, $user") }
    userList.forEachIndexed(fun (index, user){
        println("$index, $user")
    })


    // for문에다가 넣는 방식
    for ((index, element) in userList.withIndex()){
        println("$index, $element")
    }
}

class User(
    var name: String,
    var age: Int
)