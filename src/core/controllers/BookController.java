package core.controllers;

import core.controllers.dto.BookCreationRequest;
import core.controllers.dto.BookTableDto;
import core.controllers.dto.BookType;
import core.controllers.interfaces.IBookController;
import core.controllers.utils.BookDtoMapper;
import core.controllers.utils.Response;
import core.controllers.utils.ResponseKeys;
import core.controllers.utils.ValidationUtils;
import core.models.Author;
import core.models.Audiobook;
import core.models.Book;
import core.models.DigitalBook;
import core.models.Narrator;
import core.models.PrintedBook;
import core.models.Publisher;
import core.models.factory.BookFactory;
import core.observer.DataChangeNotifier;
import core.observer.DataChangeType;
import core.repositories.AuthorRepository;
import core.repositories.BookRepository;
import core.repositories.NarratorRepository;
import core.repositories.PublisherRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controlador para los libros
 */
public class BookController implements IBookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final NarratorRepository narratorRepository;
    private final DataChangeNotifier notifier;
    private final BookFactory bookFactory;

    public BookController(BookRepository bookRepository,
            AuthorRepository authorRepository,
            PublisherRepository publisherRepository,
            NarratorRepository narratorRepository,
            BookFactory bookFactory,
            DataChangeNotifier notifier) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.narratorRepository = narratorRepository;
        this.bookFactory = bookFactory;
        this.notifier = notifier;
    }

    @Override
    public Response createBook(BookCreationRequest request) {
        Response validation = validateBookRequest(request);
        if (!validation.isSuccess()) {
            return validation;
        }

        Publisher publisher = publisherRepository.findByNit(request.getPublisherNit()).orElse(null);
        List<Author> authors = new ArrayList<>();
        for (Long authorId : request.getAuthorIds()) {
            authorRepository.findById(authorId).ifPresent(authors::add);
        }

        Narrator narratorEntity = null;
        if (request.getType() == BookType.AUDIOBOOK) {
            narratorEntity = narratorRepository.findById(request.getNarratorId()).orElse(null);
            if (narratorEntity == null) {
                return Response.error("El narrador seleccionado ya no existe");
            }
        }

        Book book = bookFactory.createBook(request, authors, publisher, narratorEntity);
        bookRepository.save(book);
        notifier.emit(DataChangeType.BOOKS);
        return Response.success("Libro registrado correctamente");
    }

    @Override
    public Response getBooksByType(BookType type) {
        List<BookTableDto> rows = new ArrayList<>();
        for (Book book : bookRepository.findAllOrdered()) {
            if (matchesType(book, type)) {
                rows.add(BookDtoMapper.toDto(book));
            }
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put(ResponseKeys.ROWS, rows);
        return Response.success("Listado de libros", data);
    }

    @Override
    public Response getAllBooks() {
        List<BookTableDto> rows = new ArrayList<>();
        for (Book book : bookRepository.findAllOrdered()) {
            rows.add(BookDtoMapper.toDto(book));
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put(ResponseKeys.ROWS, rows);
        return Response.success("Listado de libros", data);
    }

    private Response validateBookRequest(BookCreationRequest request) {
        if (ValidationUtils.isBlank(request.getTitle())) {
            return Response.error("El título es obligatorio");
        }
        if (request.getAuthorIds() == null || request.getAuthorIds().isEmpty()) {
            return Response.error("Debes seleccionar al menos un autor");
        }
        Set<Long> uniqueAuthors = new HashSet<>(request.getAuthorIds());
        if (uniqueAuthors.size() != request.getAuthorIds().size()) {
            return Response.error("No puedes repetir autores en el mismo libro");
        }
        for (Long authorId : request.getAuthorIds()) {
            if (!authorRepository.exists(authorId)) {
                return Response.error("Alguno de los autores no existe");
            }
        }
        if (ValidationUtils.isBlank(request.getIsbn()) || !ValidationUtils.isValidIsbn(request.getIsbn())) {
            return Response.error("El ISBN no cumple con el formato XXX-X-XX-XXXXXX-X");
        }
        if (bookRepository.exists(request.getIsbn())) {
            return Response.error("Ya existe un libro con ese ISBN");
        }
        if (ValidationUtils.isBlank(request.getGenre()) || ValidationUtils.isPlaceholder(request.getGenre())) {
            return Response.error("El género seleccionado no es válido");
        }
        if (ValidationUtils.isBlank(request.getFormat()) || ValidationUtils.isPlaceholder(request.getFormat())) {
            return Response.error("Género y formato son obligatorios");
        }
        if (!ValidationUtils.isPositive(request.getPrice())) {
            return Response.error("El precio debe ser mayor que cero");
        }
        Publisher publisher = publisherRepository.findByNit(request.getPublisherNit()).orElse(null);
        if (publisher == null) {
            return Response.error("La editorial seleccionada no existe");
        }
        if (request.getType() == null) {
            return Response.error("Debes seleccionar un tipo de libro");
        }
        switch (request.getType()) {
            case PRINTED -> {
                if (request.getPages() == null || request.getPages() <= 0) {
                    return Response.error("Las páginas deben ser mayores que cero");
                }
                if (request.getCopies() == null || request.getCopies() <= 0) {
                    return Response.error("Las copias deben ser mayores que cero");
                }
            }
            case DIGITAL -> {
            }
            case AUDIOBOOK -> {
                if (request.getDuration() == null || request.getDuration() <= 0) {
                    return Response.error("La duración debe ser mayor que cero");
                }
                if (request.getNarratorId() == null || !narratorRepository.exists(request.getNarratorId())) {
                    return Response.error("El narrador seleccionado no existe");
                }
            }
            default -> {
                return Response.error("Tipo de libro no soportado");
            }
        }
        // hipervínculo puede ser vacío
        return Response.success("Validación exitosa");
    }

    private boolean matchesType(Book book, BookType type) {
        return switch (type) {
            case PRINTED ->
                book instanceof PrintedBook;
            case DIGITAL ->
                book instanceof DigitalBook;
            case AUDIOBOOK ->
                book instanceof Audiobook;
            default ->
                false;
        };
    }

}
