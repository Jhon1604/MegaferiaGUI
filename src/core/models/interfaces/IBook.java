package core.models.interfaces;

import java.util.List;

public interface IBook {

    String getTitle();

    List<IAuthor> getAuthors();

    String getIsbn();

    String getGenre();

    String getFormat();

    double getValue();

    IPublisher getPublisher();
}
