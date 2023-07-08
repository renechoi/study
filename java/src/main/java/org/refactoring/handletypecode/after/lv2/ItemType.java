package org.refactoring.handletypecode.after.lv2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemType {

	private int typeCode;

	public static final ItemType BOOK = new ItemType(0);
	public static final ItemType DVD = new ItemType(1);
	public static final ItemType SOFTWARE = new ItemType(2);
}
