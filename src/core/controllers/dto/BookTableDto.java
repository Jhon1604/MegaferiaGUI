package core.controllers.dto;

public class BookTableDto {

    private final String title;
    private final String authors;
    private final String isbn;
    private final String genre;
    private final String format;
    private final double price;
    private final String publisher;
    private final String copies;
    private final String pages;
    private final String hyperlink;
    private final String narrator;
    private final String duration;

    public BookTableDto(String title, String authors, String isbn, String genre, String format, double price,
            String publisher, String copies, String pages, String hyperlink, String narrator, String duration) {
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.genre = genre;
        this.format = format;
        this.price = price;
        this.publisher = publisher;
        this.copies = copies;
        this.pages = pages;
        this.hyperlink = hyperlink;
        this.narrator = narrator;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
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

    public String getPublisher() {
        return publisher;
    }

    public String getCopies() {
        return copies;
    }

    public String getPages() {
        return pages;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public String getNarrator() {
        return narrator;
    }

    public String getDuration() {
        return duration;
    }
}
