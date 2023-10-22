package org.example.ex10;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Rene
 * @since : 2023/10/22
 */
public class Exam10 {

	public Exam10(ExamUser examUser){
		if (examUser!=null && examUser.getName() != null){
			if (!examUser.getName().isBlank()){
				// 로직
			}
		}

		// 혹은

		var optionalUser = Optional.ofNullable(examUser);

		optionalUser.ifPresent(it ->{
			Optional.ofNullable(examUser.getName()).ifPresent(name->{
				if(!name.isBlank()){
					// 로직
				}
			});
		});

		// 이러한 방어 코드를 작성하게 됨

	}
	public static void main(String[] args) {
		new Exam10(new ExamUser());
		new Exam10(new ExamUser("aa"));
	}
}



@Data
@NoArgsConstructor
@AllArgsConstructor
class ExamUser{
	private String name;
}




// notblank를 사용하려고 이렇게 util 함수를 만들기도 한다.
class StringUtils{
	public static boolean notBlank(String value){
		return !value.isBlank();
	}
}


class ObjectUtils{
	public static boolean isNotNull(Object object){
		return object != null;
	}
}


// 이러한 코드를 코틀린에서는 ?