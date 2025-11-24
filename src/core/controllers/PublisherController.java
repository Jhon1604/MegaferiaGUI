package core.controllers;

import core.controllers.dto.PublisherCreationRequest;
import core.controllers.dto.PublisherTableDto;
import core.controllers.dto.SelectionOptionDto;
import core.controllers.interfaces.IPublisherController;
import core.controllers.utils.Response;
import core.controllers.utils.ResponseKeys;
import core.controllers.utils.ValidationUtils;
import core.models.Manager;
import core.models.Publisher;
import core.models.factory.PublisherFactory;
import core.observer.DataChangeNotifier;
import core.observer.DataChangeType;
import core.repositories.ManagerRepository;
import core.repositories.PublisherRepository;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador para editoriales
 */
public class PublisherController implements IPublisherController {

    private final PublisherRepository publisherRepository;
    private final ManagerRepository managerRepository;
    private final PublisherFactory publisherFactory;
    private final DataChangeNotifier notifier;

    public PublisherController(PublisherRepository publisherRepository,
            ManagerRepository managerRepository,
            PublisherFactory publisherFactory,
            DataChangeNotifier notifier) {
        this.publisherRepository = publisherRepository;
        this.managerRepository = managerRepository;
        this.publisherFactory = publisherFactory;
        this.notifier = notifier;
    }

    @Override
    public Response createPublisher(PublisherCreationRequest request) {
        if (ValidationUtils.isBlank(request.getNit()) || !ValidationUtils.isValidNit(request.getNit())) {
            return Response.error("El NIT no cumple con el formato XXX.XXX.XXX-X");
        }
        if (ValidationUtils.isBlank(request.getName()) || ValidationUtils.isBlank(request.getAddress())) {
            return Response.error("Nombre y dirección son obligatorios");
        }
        if (publisherRepository.exists(request.getNit())) {
            return Response.error("Ya existe una editorial con ese NIT");
        }
        Manager manager = managerRepository.findById(request.getManagerId())
                .orElse(null);
        if (manager == null) {
            return Response.error("El gerente seleccionado no existe");
        }
        if (manager.getPublisher() != null) {
            return Response.error("Ese gerente ya está asignado a otra editorial");
        }
        Publisher publisher = publisherFactory.createPublisher(request, manager);
        publisherRepository.save(publisher);
        notifier.emit(DataChangeType.PUBLISHERS);
        return Response.success("Editorial creada correctamente");
    }

    @Override
    public Response getPublisherOptions() {
        List<SelectionOptionDto> options = publisherRepository.findAllOrdered()
                .stream()
                .map(publisher -> new SelectionOptionDto(
                publisher.getNit(),
                publisher.getName() + " (" + publisher.getNit() + ")"))
                .collect(Collectors.toList());
        HashMap<String, Object> data = new HashMap<>();
        data.put(ResponseKeys.OPTIONS, options);
        return Response.success("Editoriales disponibles", data);
    }

    @Override
    public Response getPublisherTable() {
        List<PublisherTableDto> rows = publisherRepository.findAllOrdered()
                .stream()
                .map(publisher -> new PublisherTableDto(
                publisher.getNit(),
                publisher.getName(),
                publisher.getAddress(),
                publisher.getManager().getFullname(),
                publisher.getStandQuantity()))
                .collect(Collectors.toList());
        HashMap<String, Object> data = new HashMap<>();
        data.put(ResponseKeys.ROWS, rows);
        return Response.success("Tabla de editoriales", data);
    }
}
