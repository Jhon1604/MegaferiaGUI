package core.repositories;

import core.models.Narrator;
import core.repositories.interfaces.IRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repositorio para narradores
 */
public class NarratorRepository implements IRepository<Narrator, Long> {

    private final Map<Long, Narrator> narrators = new HashMap<>();

    @Override
    public boolean exists(Long id) {
        return narrators.containsKey(id);
    }

    @Override
    public void save(Narrator narrator) {
        narrators.put(narrator.getId(), narrator);
    }

    @Override
    public Optional<Narrator> findById(Long id) {
        return Optional.ofNullable(narrators.get(id));
    }

    @Override
    public List<Narrator> findAllOrdered() {
        ArrayList<Narrator> ordered = new ArrayList<>(narrators.values());
        ordered.sort(Comparator.comparingLong(Narrator::getId));
        return ordered;
    }
}
