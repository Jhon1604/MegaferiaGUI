package core.controllers.interfaces;

import core.controllers.dto.StandPurchaseRequest;
import core.controllers.utils.Response;

/**
 * Controlador de compras, gestiona la asignación de stands a editoriales
 */
public interface IPurchaseController extends IController {

    Response purchaseStands(StandPurchaseRequest request);
}
