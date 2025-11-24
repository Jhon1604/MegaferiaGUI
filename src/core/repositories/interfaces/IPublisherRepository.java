package core.repositories.interfaces;

import core.models.Publisher;
import java.util.Optional;

/**
 * Repositorio específico para editoriales
 */
public interface IPublisherRepository extends IRepository<Publisher, String> {

    /**
     * Busca una editorial por su NIT
     */
    Optional<Publisher> findByNit(String nit);
}

