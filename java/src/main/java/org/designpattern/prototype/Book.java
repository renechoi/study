package org.designpattern.prototype;

import lombok.Data;

@Data
public class Book {
	private String author;
	private String title;

	public Book(String author, String title) {
		this.author = author;
		this.title = title;
	}

	public String toString() {
		return title + "," + author;
	}

}
