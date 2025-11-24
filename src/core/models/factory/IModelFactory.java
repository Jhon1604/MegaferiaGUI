package core.models.factory; 

/**
 * Fábrica genérica para crear modelos
 * Fabrica generica 
 */

public interface ImodelFActory<T, R> {

    T create(R data); 

}



