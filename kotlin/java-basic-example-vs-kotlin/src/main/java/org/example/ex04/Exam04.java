package org.example.ex04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Rene
 * @since : 2023/10/22
 */

public class Exam04 {

	public Exam04(){
		var hashMap = new HashMap<String, Object>();
		hashMap.put("key", "value");
		hashMap.put("key", 10);

		var map = Map.of(
			"key1","value1",
			"key2","value2",
			"key3","value3"
		);

		hashMap.get("key");
	}
}