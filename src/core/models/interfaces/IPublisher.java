package core.models.interfaces;

import java.util.List;

public interface IPublisher {

    String getNit();

    String getName();

    String getAddress();

    IManager getManager();

    List<IBook> getBooks();

    List<IStand> getStands();

    int getStandQuantity();
}
