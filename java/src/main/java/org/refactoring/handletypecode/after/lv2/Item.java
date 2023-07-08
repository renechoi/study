package org.refactoring.handletypecode.after.lv2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Item {
	public static final int TYPECODE_BOOK = ItemType.BOOK.getTypeCode();
	public static final int TYPECODE_DVD = ItemType.DVD.getTypeCode();
	public static final int TYPECODE_SOFTWARE = ItemType.SOFTWARE.getTypeCode();
	
	private final ItemType itemType;
	private final String title;
	private final int price;

	public String toString() {
		return "[" + itemType.getTypeCode() + "," + getTitle() + "," + getPrice() + "]";
	}
}
