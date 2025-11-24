package core.models.factory;

/**
 * Fábrica genérica para crear modelos
 */
public interface IModelFactory<T, R> {

    T create(R data);
}

