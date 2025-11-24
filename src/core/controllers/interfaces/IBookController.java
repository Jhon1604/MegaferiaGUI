package core.controllers.interfaces;

import core.controllers.dto.BookCreationRequest;
import core.controllers.dto.BookType;
import core.controllers.utils.Response;

/**
 * Controlador de libros, gestiona el registro y consulta de libros
 */
public interface IBookController extends IController {

    Response createBook(BookCreationRequest request);

    Response getBooksByType(BookType type);

    Response getAllBooks();
}
