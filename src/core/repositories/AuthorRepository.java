package core.repositories;

import core.models.Author;
import core.repositories.interfaces.IRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repositorio para autores
 */
public class AuthorRepository implements IRepository<Author, Long> {

    /*
    We pray we get more time
    Heal the pain, we need more time
    Things change in a short time
     */
    private final Map<Long, Author> authors = new HashMap<>();

    @Override
    public boolean exists(Long id) {
        return authors.containsKey(id);
    }

    @Override
    public void save(Author author) {
        authors.put(author.getId(), author);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(authors.get(id));
    }

    @Override
    public List<Author> findAllOrdered() {
        ArrayList<Author> ordered = new ArrayList<>(authors.values());
        ordered.sort(Comparator.comparingLong(Author::getId));
        return ordered;
    }
}
