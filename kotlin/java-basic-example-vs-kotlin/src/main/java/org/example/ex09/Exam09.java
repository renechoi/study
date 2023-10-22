package org.example.ex09;

import lombok.Data;

/**
 * @author : Rene
 * @since : 2023/10/22
 */
public class Exam09 {
	public static void main(String[] args) {

	}

	public Exam09(StoreUser storeUser){
		// service logic

		if (storeUser.getRole().equals("MASTER")){

		} else if (storeUser.getRole().equals("ADMIN")) {

		} else if (storeUser.getRole().equals("USER")){

		}



		switch (storeUser.getRole()){
			case "MASTER":
				break;
			case "ADMIN":
				break;
			case "USER":
				break;
			default:
				// 로직
		}

		try {

		} catch (Exception e){
			if (e instanceof NullPointerException){

			}
		}

	}
}


@Data
class StoreUser{
	private String name;
	private String role;
}


// java에서 이와 같이 분기문을 처리하는 방식 -> 코틀린에서는 ?
