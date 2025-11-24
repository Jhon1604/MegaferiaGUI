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
public abstract class Book implements IBook {

    protected String title;
    protected ArrayList<Author> authors;
    protected final String isbn;
    protected String genre;
    protected String format;
    protected double value;
    protected Publisher publisher;

    public Book(String title, ArrayList<Author> authors, String isbn, String genre, String format, double value, Publisher publisher) {
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.genre = genre;
        this.format = format;
        this.value = value;
        this.publisher = publisher;

        for (Author autor : this.authors) {
            autor.addBook(this);
        }
        this.publisher.addBook(this);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<IAuthor> getAuthors() {
        ArrayList<IAuthor> authorCopies = new ArrayList<>();
        authorCopies.addAll(this.authors);
        return authorCopies;
    }

    @Override
    public String getIsbn() {
        return isbn;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public String getFormat() {
        return format;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public IPublisher getPublisher() {
        return publisher;
    }

}
