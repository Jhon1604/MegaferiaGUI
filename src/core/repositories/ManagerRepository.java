package core.repositories;

import core.models.Manager;
import core.repositories.interfaces.IRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repositorio para gerentes
 */
public class ManagerRepository implements IRepository<Manager, Long> {

    private final Map<Long, Manager> managers = new HashMap<>();

    @Override
    public boolean exists(Long id) {
        return managers.containsKey(id);
    }

    @Override
    public void save(Manager manager) {
        managers.put(manager.getId(), manager);
    }

    @Override
    public Optional<Manager> findById(Long id) {
        return Optional.ofNullable(managers.get(id));
    }

    @Override
    public List<Manager> findAllOrdered() {
        ArrayList<Manager> ordered = new ArrayList<>(managers.values());
        ordered.sort(Comparator.comparingLong(Manager::getId));
        return ordered;
    }
}
