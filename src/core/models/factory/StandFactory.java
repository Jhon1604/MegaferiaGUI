package core.models.factory;

import core.controllers.dto.StandCreationRequest;
import core.models.Stand;

/**
 * Fábrica para crear stands
 */
public class StandFactory {

    public Stand createStand(StandCreationRequest request) {
        return new Stand(request.getId(), request.getPrice());
    }
}

