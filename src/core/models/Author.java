/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.models.interfaces.IAuthor;
import core.models.interfaces.IBook;
import core.models.interfaces.IPublisher;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edangulo
 */
public class Author extends Person implements IAuthor {

    private final ArrayList<Book> books;

    public Author(long id, String firstname, String lastname) {
        super(id, firstname, lastname);
        this.books = new ArrayList<>();
    }

    @Override
    public List<IBook> getBooks() {
        ArrayList<IBook> bookCopies = new ArrayList<>();
        bookCopies.addAll(this.books);
        return bookCopies;
    }

    @Override
    public int getBookQuantity() {
        return this.books.size();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    @Override
    public int getPublisherQuantity() {
        ArrayList<IPublisher> publishers = new ArrayList<>();
        for (Book book : this.books) {
            IPublisher publisher = book.getPublisher();
            if (publisher != null && !publishers.contains(publisher)) {
                publishers.add(publisher);
            }
        }
        return publishers.size();
    }

}
