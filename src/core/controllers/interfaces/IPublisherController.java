package core.controllers.interfaces;

import core.controllers.dto.PublisherCreationRequest;
import core.controllers.utils.Response;

/**
 * Controlador de editoriales, gestiona el registro y consulta de editoriales
 */
public interface IPublisherController extends IController {

    Response createPublisher(PublisherCreationRequest request);

    Response getPublisherOptions();

    Response getPublisherTable();
}
