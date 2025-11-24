package core.repositories;

import core.models.Publisher;
import core.repositories.interfaces.IPublisherRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repositorio para editoriales
 */
public class PublisherRepository implements IPublisherRepository {

    private final Map<String, Publisher> publishers = new HashMap<>();

    @Override
    public boolean exists(String nit) {
        return publishers.containsKey(nit);
    }

    @Override
    public void save(Publisher publisher) {
        publishers.put(publisher.getNit(), publisher);
    }

    @Override
    public Optional<Publisher> findById(String nit) {
        return Optional.ofNullable(publishers.get(nit));
    }

    @Override
    public Optional<Publisher> findByNit(String nit) {
        return findById(nit);
    }

    @Override
    public List<Publisher> findAllOrdered() {
        ArrayList<Publisher> ordered = new ArrayList<>(publishers.values());
        ordered.sort(Comparator.comparing(Publisher::getNit));
        return ordered;
    }
}
