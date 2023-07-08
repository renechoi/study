package org.refactoring.handletypecode.before;

public class Main {

	public static void main(String[] args) {

		Item book = new Item(Item.TYPECODE_BOOK, "Demian", 20000);
		Item dvd = new Item(Item.TYPECODE_DVD, "King", 50000);
		Item soft = new Item(Item.TYPECODE_SOFTWARE, "Window", 30000);
		
		System.out.println(book);
		System.out.println(dvd);
		System.out.println(soft);
	}

}
