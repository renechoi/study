package org.designpattern.prototype;

import java.util.ArrayList;

import lombok.Data;

@Data
class BookShelf implements Cloneable {

	private ArrayList<Book> shelf;

	public BookShelf() {
		shelf = new ArrayList<Book>();
	}

	public void addBook(Book book) {
		shelf.add(book);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// super.clone(); // <- 얕은 복사
		BookShelf another = new BookShelf();
		for (Book book : this.shelf) {
			another.addBook(new Book(book.getAuthor(), book.getTitle()));
		}
		return another;
	}

}
