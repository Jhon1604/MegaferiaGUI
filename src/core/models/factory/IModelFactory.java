package core.models.factory;

/**
 * Fábrica genérica para crear modelos
 * Fabrica generica 
 */
public interface IModelFactory<T, R> {

    T create(R data);
}

