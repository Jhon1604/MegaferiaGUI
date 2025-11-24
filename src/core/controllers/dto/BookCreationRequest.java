package core.controllers.dto;

import java.util.List;

public class BookCreationRequest {

    private final String title;
    private final List<Long> authorIds;
    private final String isbn;
    private final String genre;
    private final String format;
    private final double price;
    private final String publisherNit;
    private final BookType type;
    private final Integer pages;
    private final Integer copies;
    private final String hyperlink;
    private final Integer duration;
    private final Long narratorId;

    public BookCreationRequest(
            String title,
            List<Long> authorIds,
            String isbn,
            String genre,
            String format,
            double price,
            String publisherNit,
            BookType type,
            Integer pages,
            Integer copies,
            String hyperlink,
            Integer duration,
            Long narratorId) {
        this.title = title;
        this.authorIds = authorIds;
        this.isbn = isbn;
        this.genre = genre;
        this.format = format;
        this.price = price;
        this.publisherNit = publisherNit;
        this.type = type;
        this.pages = pages;
        this.copies = copies;
        this.hyperlink = hyperlink;
        this.duration = duration;
        this.narratorId = narratorId;
    }

    public String getTitle() {
        return title;
    }

    public List<Long> getAuthorIds() {
        return authorIds;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    public String getFormat() {
        return format;
    }

    public double getPrice() {
        return price;
    }

    public String getPublisherNit() {
        return publisherNit;
    }

    public BookType getType() {
        return type;
    }

    public Integer getPages() {
        return pages;
    }

    public Integer getCopies() {
        return copies;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public Integer getDuration() {
        return duration;
    }

    public Long getNarratorId() {
        return narratorId;
    }
}
