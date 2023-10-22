package ex06

/**
 * @author        : Rene
 * @since         : 2023/10/22
 */
fun main() {
    // new 키워드 없이도 생성
    val dog = Dog("해피")

    dog.eat()
    dog.bark()
}


interface Bark {
    fun bark()
}

abstract class Animal (
    // 코틀린에서는 생성자 부분이 바로 선언이 됨
    private val name: String? = "") : Bark{

    // body - >

    fun eat() {
        println("$name 식사 !")
    }
}

// 이렇게만 선언해도 됨
//class Dog()


// Animal을 상속하는데 null을 받아도 되기 때문에 이렇게 호출이 가능함
//class Dog(): Animal()


// 생성자를 받아서 슈퍼라는 키워드로 넘기는 것  표현
class Dog(
    private val name:String?=null
) : Animal(name),
    // Temp 인터페이스 다중 상속 -> 파라미터 없으므로 그냥 상속
    Temp{
    override fun bark() {
        println("$name : 멍멍")
    }

    override fun hi() {
        println("hi")
    }
}

interface Temp{
    fun hi()
}

