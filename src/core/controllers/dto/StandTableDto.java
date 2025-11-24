package core.controllers.dto;

public class StandTableDto {

    private final long id;
    private final double price;
    private final boolean purchased;
    private final String publishers;

    public StandTableDto(long id, double price, boolean purchased, String publishers) {
        this.id = id;
        this.price = price;
        this.purchased = purchased;
        this.publishers = publishers;
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public String getPublishers() {
        return publishers;
    }
}
