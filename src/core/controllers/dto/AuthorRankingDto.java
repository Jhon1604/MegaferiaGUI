package core.controllers.dto;

public class AuthorRankingDto {

    private final long id;
    private final String fullName;
    private final int publisherCount;

    public AuthorRankingDto(long id, String fullName, int publisherCount) {
        this.id = id;
        this.fullName = fullName;
        this.publisherCount = publisherCount;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getPublisherCount() {
        return publisherCount;
    }
}
