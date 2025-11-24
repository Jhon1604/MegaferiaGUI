package core.repositories.interfaces;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para repositorios
 */
public interface IRepository<T, ID> {

    /**
     * Verifica si existe una entidad con el ID dado
     */
    boolean exists(ID id);

    /**
     * Guarda una entidad en el repositorio
     */
    void save(T entity);

    /**
     * Busca una entidad por su ID
     */
    Optional<T> findById(ID id);

    /**
     * Retorna todas las entidades ordenadas
     */
    List<T> findAllOrdered();
}

