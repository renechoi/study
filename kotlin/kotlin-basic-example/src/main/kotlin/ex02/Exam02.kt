package ex02

import java.io.Serializable

/**
 * @author        : Rene
 * @since         : 2023/10/22
 */
fun main(){
    val a: Int = 0
    val b = 10
    val c: Int = 20
    val d: Int? = null // 엘비스 연산자 - > 의미는 -> null 값이 들어올 수 있다

    callFunction(a)
    callFunction(b)
    callFunction(c)
    callFunction(d)

    // 자바랑 100% 호환되므로 optional이 가능

//    Optional.ofNullable(d).ifPresent() ...



}

fun callFunction(i: Int?){  // i라는 값이 null일 수도 있다
    println(i) // 별도의 null 포인트를 터지지 않고 그냥 찍는다.


    // 코틀린에는 let이라는 함수가 있음. 물음표를 붙이면 i에 대해서 검증을 함
    i?.let{

    }


    // 엘비스 연산자 -> null이 올 수도 있다. -> 이 뒤에 null을 방지할 수 있다. 변수 뒤에다 ?를 붙이면 변수가 null이야?
    // ?. << 변수가 null이 아닐 때
    // ?: << 변수가 null일 때

    i?.let{
        println(it)
    } ?: run {
        println("null 입니다")
    }


    // 자바에서 비슷하게 한다면
//    var temp1 = (i == null) ? "null 입니다" : i;
//    System.out.println("temp = " + temp1);

    // ->

    val temp2: Serializable = i ?: "null 입니다"
    println("temp = $temp2")

    // 코틀린에서 강한 부분 -> 굉장히 간단
    val temp3 = i?: "null입니다"
    println(temp3)

}


fun callFunction2(i: Int? =100){
    // default로 매개변수에 지정을 해줄 수 있음 -> null이 넘어오거나 값이 안넘어올 수도 있다

}


