package core.models.factory;

/**
 * Central para acceder a las fábricas de modelos
 */
public class ModelFactoryManager {

    private final BookFactory bookFactory = new BookFactory();
    private final PersonFactory personFactory = new PersonFactory();
    private final StandFactory standFactory = new StandFactory();
    private final PublisherFactory publisherFactory = new PublisherFactory();
    private final PublisherAssignmentFactory publisherAssignmentFactory = new PublisherAssignmentFactory();

    public BookFactory getBookFactory() {
        return bookFactory;
    }

    public PersonFactory getPersonFactory() {
        return personFactory;
    }

    public StandFactory getStandFactory() {
        return standFactory;
    }

    public PublisherFactory getPublisherFactory() {
        return publisherFactory;
    }

    public PublisherAssignmentFactory getPublisherAssignmentFactory() {
        return publisherAssignmentFactory;
    }
}

