package core.controllers.utils;

import core.controllers.dto.BookTableDto;
import core.models.Audiobook;
import core.models.Book;
import core.models.DigitalBook;
import core.models.PrintedBook;
import java.util.stream.Collectors;

/**
 * Utilidad para convertir libros a DTOs
 */
public final class BookDtoMapper {

    private BookDtoMapper() {
    }

    public static BookTableDto toDto(Book book) {
        String authors = book.getAuthors()
                .stream()
                .map(author -> author.getFullname())
                .collect(Collectors.joining(", "));
        if (authors.isEmpty()) {
            authors = "-";
        }
        if (book instanceof PrintedBook printedBook) {
            return new BookTableDto(
                    printedBook.getTitle(),
                    authors,
                    printedBook.getIsbn(),
                    printedBook.getGenre(),
                    printedBook.getFormat(),
                    printedBook.getValue(),
                    printedBook.getPublisher().getName(),
                    String.valueOf(printedBook.getCopies()),
                    String.valueOf(printedBook.getPages()),
                    "-",
                    "-",
                    "-"
            );
        }
        if (book instanceof DigitalBook digitalBook) {
            String hyperlink = digitalBook.hasHyperlink() ? digitalBook.getHyperlink() : "No";
            return new BookTableDto(
                    digitalBook.getTitle(),
                    authors,
                    digitalBook.getIsbn(),
                    digitalBook.getGenre(),
                    digitalBook.getFormat(),
                    digitalBook.getValue(),
                    digitalBook.getPublisher().getName(),
                    "-",
                    "-",
                    hyperlink,
                    "-",
                    "-"
            );
        }
        if (book instanceof Audiobook audiobook) {
            return new BookTableDto(
                    audiobook.getTitle(),
                    authors,
                    audiobook.getIsbn(),
                    audiobook.getGenre(),
                    audiobook.getFormat(),
                    audiobook.getValue(),
                    audiobook.getPublisher().getName(),
                    "-",
                    "-",
                    "-",
                    audiobook.getNarrador().getFullname(),
                    String.valueOf(audiobook.getDuration())
            );
        }
        throw new IllegalStateException("Tipo de libro desconocido");
    }
}
