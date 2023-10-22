package ex09

/**
 * @author        : Rene
 * @since         : 2023/10/22
 */
fun main(){

    when(""){
        ""-> {

        }

        "MASTER" ->{

        }
    }



    var result = when(""){
        "MASTER" -> {
            "master"
        }
        else -> {
            "default"
        }
    }



    var any : Any = "";

    // instanceof 와 동일
    when (any) {
        is String ->{}
        is Int ->{}
        is Boolean ->{}
    }


    // switch 문과 매우 동일하게 생겼지만 break을 안해도 된다.
    var exception = RuntimeException()
    when(exception) {
        is NullPointerException ->{}
    }


    // 표현식을 담을 수도 있다.
    var number = 10

    when (val n = number % 2){
        0 -> {println(n)}
        else -> { println(n)}
    }

}


