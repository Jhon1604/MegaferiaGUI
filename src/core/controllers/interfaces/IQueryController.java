package core.controllers.interfaces;

import core.controllers.utils.Response;

/**
 * Controlador de consultas, gestiona búsquedas y reportes especiales
 */
public interface IQueryController extends IController {

    Response getBooksByAuthor(long authorId);

    Response getBooksByFormat(String format);

    Response getAuthorsWithMorePublishers();
}
