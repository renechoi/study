package ex05

import java.util.function.Predicate


/**
 * @author        : Rene
 * @since         : 2023/10/22
 */
fun main() {

    var numberList = listOf<Int>(1,2,3,4,5)

    numberList.first{it % 2 == 0}

    val pred1 = object : Predicate<Int> {
        override fun test(t: Int): Boolean{
            return t%2 ==0
        }
    }

    numberList.stream().filter(pred1)

    // 2가지 변수를 받아서 x+y를 리턴
    val add = { x: Int, y:Int -> x+y}

    println(add(2,3))

    // 익명 메서드를 만들어서 넘겨줄 수 있음 -> 자바에서는 인터페이스가 있어야 하지만
    val _add = fun(x:Int, y:Int):Int {
        return x+y
    }

    println(_add(3,4))

    lambda(4,5, _add)

    // 코틀린에서는 이렇게 메서드를 넘겨주는 방식이 다양하고 간편
    // 고차함수, 람다식을 좀 더 편하게 사용

    numberList.map{
        it->
        val t = it
    }
}

// 메서드는 Int 두개를 받아서 Int를 넘겨준다
fun lambda(x:Int, y:Int, method:(Int, Int)-> Int){
    println(method(x,y))
}