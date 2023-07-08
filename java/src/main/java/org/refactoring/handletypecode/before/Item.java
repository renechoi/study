package org.refactoring.handletypecode.before;

import lombok.Getter;

@Getter
public class Item {
	public static final int TYPECODE_BOOK = 0;
	public static final int TYPECODE_DVD = 1;
	public static final int TYPECODE_SOFTWARE = 2;

	private final int typeCode;
	private final String title;
	private final int price;

	public Item(int typeCode, String title, int price) {
		this.typeCode = typeCode;
		this.title = title;
		this.price = price;
	}

	public String toString() {
		return "[" + getTypeCode() + "," + getTitle() + "," + getPrice() + "]";
	}
}
