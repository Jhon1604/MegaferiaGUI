package core.controllers;

import core.controllers.dto.AuthorRankingDto;
import core.controllers.dto.BookTableDto;
import core.controllers.interfaces.IQueryController;
import core.controllers.utils.BookDtoMapper;
import core.controllers.utils.Response;
import core.controllers.utils.ResponseKeys;
import core.controllers.utils.ValidationUtils;
import core.models.Author;
import core.models.Book;
import core.repositories.AuthorRepository;
import core.repositories.BookRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Controlador para consultas
 */
public class QueryController implements IQueryController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public QueryController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Response getBooksByAuthor(long authorId) {
        if (!ValidationUtils.isValidId(authorId)) {
            return Response.error("El ID del autor no es válido");
        }
        Author author = authorRepository.findById(authorId).orElse(null);
        if (author == null) {
            return Response.error("El autor seleccionado no existe");
        }
        List<BookTableDto> rows = new ArrayList<>();
        for (Book book : bookRepository.findAllOrdered()) {
            boolean belongsToAuthor = book.getAuthors()
                    .stream()
                    .anyMatch(a -> a.getId() == author.getId());
            if (belongsToAuthor) {
                rows.add(BookDtoMapper.toDto(book));
            }
        }
        rows.sort(Comparator.comparing(BookTableDto::getIsbn));
        HashMap<String, Object> data = new HashMap<>();
        data.put(ResponseKeys.ROWS, rows);
        return Response.success("Libros por autor", data);
    }

    @Override
    public Response getBooksByFormat(String format) {
        if (ValidationUtils.isBlank(format)) {
            return Response.error("El formato es obligatorio");
        }
        List<BookTableDto> rows = new ArrayList<>();
        for (Book book : bookRepository.findAllOrdered()) {
            if (format.equals(book.getFormat())) {
                rows.add(BookDtoMapper.toDto(book));
            }
        }
        rows.sort(Comparator.comparing(BookTableDto::getIsbn));
        HashMap<String, Object> data = new HashMap<>();
        data.put(ResponseKeys.ROWS, rows);
        return Response.success("Libros por formato", data);
    }

    @Override
    public Response getAuthorsWithMorePublishers() {
        List<AuthorRankingDto> rows = new ArrayList<>();
        int maxPublishers = -1;
        for (Author author : authorRepository.findAllOrdered()) {
            int publisherCount = author.getPublisherQuantity();
            if (publisherCount > maxPublishers) {
                maxPublishers = publisherCount;
            }
        }
        for (Author author : authorRepository.findAllOrdered()) {
            if (author.getPublisherQuantity() == maxPublishers && maxPublishers >= 0) {
                rows.add(new AuthorRankingDto(author.getId(), author.getFullname(), maxPublishers));
            }
        }
        rows.sort(Comparator.comparingLong(AuthorRankingDto::getId));
        HashMap<String, Object> data = new HashMap<>();
        data.put(ResponseKeys.ROWS, rows);
        return Response.success("Autores destacados", data);
    }
}
