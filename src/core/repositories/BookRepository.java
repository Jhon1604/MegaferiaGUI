package core.repositories;

import core.models.Book;
import core.repositories.interfaces.IBookRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repositorio para libros
 */
public class BookRepository implements IBookRepository {

    private final Map<String, Book> books = new HashMap<>();

    @Override
    public boolean exists(String isbn) {
        return books.containsKey(isbn);
    }

    @Override
    public void save(Book book) {
        books.put(book.getIsbn(), book);
    }

    @Override
    public Optional<Book> findById(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return findById(isbn);
    }

    @Override
    public List<Book> findAllOrdered() {
        ArrayList<Book> ordered = new ArrayList<>(books.values());
        ordered.sort(Comparator.comparing(Book::getIsbn));
        return ordered;
    }
}
