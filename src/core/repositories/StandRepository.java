package core.repositories;

import core.models.Stand;
import core.repositories.interfaces.IRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repositorio para los stands
 */
public class StandRepository implements IRepository<Stand, Long> {

    private final Map<Long, Stand> stands = new HashMap<>();

    @Override
    public boolean exists(Long id) {
        return stands.containsKey(id);
    }

    @Override
    public void save(Stand stand) {
        stands.put(stand.getId(), stand);
    }

    @Override
    public Optional<Stand> findById(Long id) {
        return Optional.ofNullable(stands.get(id));
    }

    @Override
    public List<Stand> findAllOrdered() {
        ArrayList<Stand> ordered = new ArrayList<>(stands.values());
        ordered.sort(Comparator.comparingLong(Stand::getId));
        return ordered;
    }
}
