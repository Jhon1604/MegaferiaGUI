package core.controllers.interfaces;

import core.controllers.dto.StandCreationRequest;
import core.controllers.utils.Response;

/**
 * Controlador de stands, gestiona la creación y consulta de stands
 */
public interface IStandController extends IController {

    Response createStand(StandCreationRequest request);

    Response getStandOptions();

    Response getStandTable();
}
