package core.models.factory;

import core.controllers.dto.PersonCreationRequest;
import core.controllers.dto.PersonRole;
import core.models.Author;
import core.models.Manager;
import core.models.Narrator;

/**
 * Fábrica para crear personas según su rol
 */
public class PersonFactory {

    public Author createAuthor(PersonCreationRequest request) {
        return new Author(request.getId(), request.getFirstName(), request.getLastName());
    }

    public Manager createManager(PersonCreationRequest request) {
        return new Manager(request.getId(), request.getFirstName(), request.getLastName());
    }

    public Narrator createNarrator(PersonCreationRequest request) {
        return new Narrator(request.getId(), request.getFirstName(), request.getLastName());
    }

    public Object createByRole(PersonCreationRequest request) {
        PersonRole role = request.getRole();
        if (role == null) {
            throw new IllegalStateException("No se especificó el rol para la persona");
        }
        return switch (role) {
            case AUTHOR ->
                createAuthor(request);
            case MANAGER ->
                createManager(request);
            case NARRATOR ->
                createNarrator(request);
        };
    }
}

