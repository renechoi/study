package org.example.ex08;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

/**
 * @author : Rene
 * @since : 2023/10/22
 */
public class Exam08 {
	public static void main(String[] args) {

		var registerDto = new Store();

		new Exam08(registerDto);
	}

	public Exam08(Store store){
		// null point -> 터짐
		var stringRegisteredAt = toLocalDateTimeString(store.getRegisteredAt());
	}

	public String toLocalDateTimeString(LocalDateTime localDateTime){

		// null을 방지하려고 이런식으로 방어 로직을 짬
		LocalDateTime temp;
		if(localDateTime == null){
			temp = localDateTime.now();
		}

		// 혹은 optional로 받아서 orElseGet으로

		return localDateTime.format(DateTimeFormatter.ofPattern("yyyy MM dd"));
	}
}


@Data
class Store {
	private LocalDateTime registeredAt;
}



// 이러한 null 체크 로직, optional을 코틀린에서 더 쉽게 처리할 수 있다면 ?

