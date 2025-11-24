package core.controllers.interfaces;

import core.controllers.dto.PersonCreationRequest;
import core.controllers.utils.Response;

/**
 * Controlador de personas, gestiona autores, gerentes y narradores
 */
public interface IPersonController extends IController {

    Response createPerson(PersonCreationRequest request);

    Response getAuthorOptions();

    Response getManagerOptions();

    Response getNarratorOptions();

    Response getPersonTable();
}
