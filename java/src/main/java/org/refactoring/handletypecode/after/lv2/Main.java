package org.refactoring.handletypecode.after.lv2;

public class Main {

	public static void main(String[] args) {

		Item book = new Item(ItemType.BOOK, "Demian", 20000);
		Item dvd = new Item(ItemType.DVD, "King", 50000);
		Item soft = new Item(ItemType.SOFTWARE, "Window", 30000);
		
		System.out.println(book);
		System.out.println(dvd);
		System.out.println(soft);
	}
}
