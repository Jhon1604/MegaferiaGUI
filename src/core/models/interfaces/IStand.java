package core.models.interfaces;

import java.util.List;

public interface IStand {

    long getId();

    double getPrice();

    List<IPublisher> getPublishers();

    int getPublisherQuantity();
}
