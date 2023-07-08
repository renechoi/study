package org.refactoring.handletypecode.after.lv1;

public class Item {
	public static final int TYPECODE_BOOK = ItemType.BOOK.getTypeCode();
	public static final int TYPECODE_DVD = ItemType.DVD.getTypeCode();
	public static final int TYPECODE_SOFTWARE = ItemType.SOFTWARE.getTypeCode();
	
	//private final int typeCode;
	private final ItemType itemType;
	private final String title;
	private final int price;
	
	
	public Item(int typeCode, String title, int price) {
		this.itemType = ItemType.getTypeItem(typeCode);
		this.title = title;
		this.price = price;
	}
	
	public int getTypeCode() {
		return itemType.getTypeCode();
	}
	
	public String getTitle() {
		return title;
	} 
	
	public int getPrice() {
		return price;
	}
	
	public String toString() {
		return "[" + itemType.getTypeCode() + "," + getTitle() + "," + getPrice() + "]";
	}
}
