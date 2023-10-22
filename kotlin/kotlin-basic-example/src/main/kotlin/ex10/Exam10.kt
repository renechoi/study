package ex10

/**
 * @author        : Rene
 * @since         : 2023/10/22
 */
fun main(){
    val user = ExamUser(name = "abcd");
    exam10(user)
}



fun exam10(examUser: ExamUser?){
    examUser?.let {
        it.name?.let{name ->
            println(name)
        }
    }


    examUser?.let{
        if(it.name.isNullOrEmpty()){
            // 로직
        }

        // 뒤에 만든 함수를 마치 스트링 클래스에 생긴 메서드처럼 동작을 함 -> 내가 원하는 특정 클래스를 이미 존재하는 클래스에 추가가 가능함
        if(it.name.isNotNullOrBlank()){

        }



        if (examUser.isNotNull() && examUser.name.isNotNullOrBlank()){
            // 로직
        }

    }
}
data class ExamUser(
    var name: String ?=null
)

// 코틀린의 확장 함수 -> extension function
fun String.isNotNullOrBlank(): Boolean{
    return !this.isNotNullOrBlank();
}



// 코틀린에서 모든 Object의 조상은 Any

fun Any?.isNotNull():Boolean{
    return this!=null;
}


// 중요한 점은 확장 함수 -> 자주 사용하는 함수들을 만들 수도 있고 이미 만들어진 것을 정의할 수 있음
