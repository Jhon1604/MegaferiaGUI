package core.models.interfaces;

import java.util.List;

public interface IAuthor extends IPerson {

    List<IBook> getBooks();

    int getBookQuantity();

    int getPublisherQuantity();
}
