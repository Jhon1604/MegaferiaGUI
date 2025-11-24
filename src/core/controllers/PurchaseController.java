package core.controllers;

import core.controllers.dto.StandPurchaseRequest;
import core.controllers.interfaces.IPurchaseController;
import core.controllers.utils.Response;
import core.controllers.utils.ValidationUtils;
import core.models.Publisher;
import core.models.Stand;
import core.models.factory.PublisherAssignmentFactory;
import core.repositories.PublisherRepository;
import core.repositories.StandRepository;
import core.observer.DataChangeNotifier;
import core.observer.DataChangeType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controlador para la compra de stands por editoriales
 */
public class PurchaseController implements IPurchaseController {

    private final StandRepository standRepository;
    private final PublisherRepository publisherRepository;
    private final PublisherAssignmentFactory assignmentFactory;
    private final DataChangeNotifier notifier;

    public PurchaseController(StandRepository standRepository,
            PublisherRepository publisherRepository,
            PublisherAssignmentFactory assignmentFactory,
            DataChangeNotifier notifier) {
        this.standRepository = standRepository;
        this.publisherRepository = publisherRepository;
        this.assignmentFactory = assignmentFactory;
        this.notifier = notifier;
    }

    @Override
    public Response purchaseStands(StandPurchaseRequest request) {
        if (request.getStandIds() == null || request.getStandIds().isEmpty()) {
            return Response.error("Debes seleccionar al menos un stand");
        }
        if (request.getPublisherNits() == null || request.getPublisherNits().isEmpty()) {
            return Response.error("Debes seleccionar al menos una editorial");
        }
        if (!hasUniqueValues(request.getStandIds())) {
            return Response.error("No repitas stands en la misma compra");
        }
        if (!hasUniqueValues(request.getPublisherNits())) {
            return Response.error("No repitas editoriales en la misma compra");
        }
        List<Stand> stands = new ArrayList<>();
        for (Long standId : request.getStandIds()) {
            if (!ValidationUtils.isValidId(standId)) {
                return Response.error("Hay un ID de stand no válido");
            }
            Stand stand = standRepository.findById(standId).orElse(null);
            if (stand == null) {
                return Response.error("El stand " + standId + " no existe");
            }
            stands.add(stand);
        }
        List<Publisher> publishers = new ArrayList<>();
        for (String nit : request.getPublisherNits()) {
            if (!ValidationUtils.isValidNit(nit)) {
                return Response.error("Algunos NIT no son válidos");
            }
            Publisher publisher = publisherRepository.findByNit(nit).orElse(null);
            if (publisher == null) {
                return Response.error("La editorial con NIT " + nit + " no existe");
            }
            publishers.add(publisher);
        }
        for (Stand stand : stands) {
            for (Publisher publisher : publishers) {
                assignmentFactory.link(stand, publisher);
            }
        }
        notifier.emit(DataChangeType.PURCHASES);
        return Response.success("Compra registrada correctamente");
    }

    private <T> boolean hasUniqueValues(List<T> values) {
        Set<T> uniques = new HashSet<>(values);
        return uniques.size() == values.size();
    }
}
