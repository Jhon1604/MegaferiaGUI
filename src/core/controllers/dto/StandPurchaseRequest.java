package core.controllers.dto;

import java.util.List;

public class StandPurchaseRequest {

    private final List<Long> standIds;
    private final List<String> publisherNits;

    public StandPurchaseRequest(List<Long> standIds, List<String> publisherNits) {
        this.standIds = standIds;
        this.publisherNits = publisherNits;
    }

    public List<Long> getStandIds() {
        return standIds;
    }

    public List<String> getPublisherNits() {
        return publisherNits;
    }
}
