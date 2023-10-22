package org.example.ex07;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Rene
 * @since : 2023/10/22
 */
public class Exam07 {
}



// 롬복을 사용하면 애노테이션으로 작성하지만 , 실제로는 보일러플레이트 코드를 전부 작성해야 함
@Data
@NoArgsConstructor
@AllArgsConstructor
class User{
	private String name;
	private String email;
	private int age;
}