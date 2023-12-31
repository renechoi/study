package org.designpattern.iterator;

public class BookShelf implements Aggregate {
    private Book[] books;
    private int last = 0;
    Factory factory = IteratorFactory.getInstance();
   
    public BookShelf(int maxsize) {
        this.books = new Book[maxsize];
    }
    public Book getBookAt(int index) {
        return books[index];
    }
    public void appendBook(Book book) {
        this.books[last] = book;
        last++;
    }
    public int getLength() {
        return last;
    }
    public Iterator iterator(int type) {
        return factory.create(this, type);
    }
   	
}
