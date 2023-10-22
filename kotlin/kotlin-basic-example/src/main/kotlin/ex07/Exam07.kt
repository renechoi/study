package ex07

/**
 * @author        : Rene
 * @since         : 2023/10/22
 */
class User(
    var name: String? = null, var age: Int? = null, var eamil: String? = null
) {
    override fun toString(): String {
        return "User(name=$name, age=$age, eamil=$eamil)"
    }
}

fun main() {
    var user = User()
    user.name= "홍길동"
}