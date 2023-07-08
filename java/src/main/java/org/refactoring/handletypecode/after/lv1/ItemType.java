package org.refactoring.handletypecode.after.lv1;

public class ItemType {

	private int typeCode;
	
	public static final ItemType BOOK = new ItemType(0);
	public static final ItemType DVD = new ItemType(1);
	public static final ItemType SOFTWARE = new ItemType(2);
	
	private ItemType(int typeCode) {
		this.typeCode = typeCode;
	}
	
	public int getTypeCode() {
		return typeCode;
	}
	
	public static ItemType getTypeItem(int type) {
		
		switch (type) {
			case 0 : return BOOK;
			case 1 : return DVD;
			case 2 : return SOFTWARE;
			default : return null;
		}
			
	}
}
