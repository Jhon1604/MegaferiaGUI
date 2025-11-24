package core.controllers.dto;

public class StandCreationRequest {

    private final long id;
    private final double price;

    public StandCreationRequest(long id, double price) {
        this.id = id;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}
