package core.models.factory;

import core.controllers.dto.BookCreationRequest;
import core.controllers.dto.BookType;
import core.models.Author;
import core.models.Audiobook;
import core.models.Book;
import core.models.DigitalBook;
import core.models.Narrator;
import core.models.PrintedBook;
import core.models.Publisher;
import java.util.ArrayList;
import java.util.List;

/**
 * Fábrica dedicada a instanciar libros según su tipo
 */
public class BookFactory {

    public Book createBook(BookCreationRequest request, List<Author> authors, Publisher publisher, Narrator narrator) {
        ArrayList<Author> authorCopies = new ArrayList<>(authors);
        BookType type = request.getType();
        if (type == null) {
            throw new IllegalStateException("No se definió el tipo de libro a crear");
        }
        return switch (type) {
            case PRINTED ->
                new PrintedBook(
                        request.getTitle(),
                        authorCopies,
                        request.getIsbn(),
                        request.getGenre(),
                        request.getFormat(),
                        request.getPrice(),
                        publisher,
                        request.getPages(),
                        request.getCopies()
                );
            case DIGITAL -> {
                if (request.getHyperlink() == null || request.getHyperlink().isBlank()) {
                    yield new DigitalBook(
                            request.getTitle(),
                            authorCopies,
                            request.getIsbn(),
                            request.getGenre(),
                            request.getFormat(),
                            request.getPrice(),
                            publisher
                    );
                }
                yield new DigitalBook(
                        request.getTitle(),
                        authorCopies,
                        request.getIsbn(),
                        request.getGenre(),
                        request.getFormat(),
                        request.getPrice(),
                        publisher,
                        request.getHyperlink()
                );
            }
            case AUDIOBOOK ->
                new Audiobook(
                        request.getTitle(),
                        authorCopies,
                        request.getIsbn(),
                        request.getGenre(),
                        request.getFormat(),
                        request.getPrice(),
                        publisher,
                        request.getDuration(),
                        narrator
                );
        };
    }
}

