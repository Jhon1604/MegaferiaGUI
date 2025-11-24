package core.models.factory;

import core.controllers.dto.PublisherCreationRequest;
import core.models.Manager;
import core.models.Publisher;

/**
 * Fábrica para crear editoriales
 */
public class PublisherFactory {

    public Publisher createPublisher(PublisherCreationRequest request, Manager manager) {
        return new Publisher(
                request.getNit(),
                request.getName(),
                request.getAddress(),
                manager
        );
    }
}

