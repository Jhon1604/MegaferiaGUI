package core.repositories.interfaces;

import core.models.Book;
import java.util.Optional;

/**
 * Repositorio específico para libros
 */
public interface IBookRepository extends IRepository<Book, String> {

    /**
     * Busca un libro por su ISBN
     */
    Optional<Book> findByIsbn(String isbn);
}

