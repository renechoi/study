package org.designpattern.prototype;

import org.junit.jupiter.api.Assertions;

import lombok.Data;

@Data
public class PrototypeMain {

	public static void test() throws CloneNotSupportedException {

		BookShelf bookShelf = new BookShelf();
		bookShelf.addBook(new Book("박완서", "나목"));
		bookShelf.addBook(new Book("박경리", "토지"));
		bookShelf.addBook(new Book("조정래", "태백산맥"));

		BookShelf another = (BookShelf)bookShelf.clone();

		System.out.println(bookShelf);
		System.out.println(another);
		Assertions.assertEquals(bookShelf.getShelf().get(0), another.getShelf().get(0));

		bookShelf.getShelf().get(0).setAuthor("한강");
		bookShelf.getShelf().get(0).setTitle("눈");

		System.out.println(bookShelf);
		System.out.println(another);
		Assertions.assertNotEquals(bookShelf.getShelf().get(0), another.getShelf().get(0));


	}
	public static void main(String[] args) throws CloneNotSupportedException {
		test();
	}
}
