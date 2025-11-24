package core.controllers;

import core.controllers.dto.SelectionOptionDto;
import core.controllers.dto.StandCreationRequest;
import core.controllers.dto.StandTableDto;
import core.controllers.interfaces.IStandController;
import core.controllers.utils.Response;
import core.controllers.utils.ResponseKeys;
import core.controllers.utils.ValidationUtils;
import core.models.Stand;
import core.models.factory.StandFactory;
import core.observer.DataChangeNotifier;
import core.observer.DataChangeType;
import core.repositories.StandRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador para todo lo relacionado con los stands
 */
public class StandController implements IStandController {

    private final StandRepository standRepository;
    private final DataChangeNotifier notifier;
    private final StandFactory standFactory;

    public StandController(StandRepository standRepository, StandFactory standFactory, DataChangeNotifier notifier) {
        this.standRepository = standRepository;
        this.standFactory = standFactory;
        this.notifier = notifier;
    }

    @Override
    public Response createStand(StandCreationRequest request) {
        if (!ValidationUtils.isValidId(request.getId())) {
            return Response.error("El ID del stand no es válido");
        }
        if (!ValidationUtils.isPositive(request.getPrice())) {
            return Response.error("El precio del stand debe ser mayor que cero");
        }
        if (standRepository.exists(request.getId())) {
            return Response.error("Ya existe un stand con ese ID");
        }
        Stand stand = standFactory.createStand(request);
        standRepository.save(stand);
        notifier.emit(DataChangeType.STANDS);
        return Response.success("Stand creado correctamente");
    }

    @Override
    public Response getStandOptions() {
        List<SelectionOptionDto> options = standRepository.findAllOrdered()
                .stream()
                .map(stand -> new SelectionOptionDto(String.valueOf(stand.getId()), String.valueOf(stand.getId())))
                .collect(Collectors.toList());
        HashMap<String, Object> data = new HashMap<>();
        data.put(ResponseKeys.OPTIONS, options);
        return Response.success("Listado de stands", data);
    }

    @Override
    public Response getStandTable() {
        List<StandTableDto> table = new ArrayList<>();
        for (Stand stand : standRepository.findAllOrdered()) {
            String publishers = "";
            if (stand.getPublisherQuantity() > 0) {
                List<String> names = stand.getPublishers()
                        .stream()
                        .map(publisher -> publisher.getName())
                        .collect(Collectors.toList());
                publishers = String.join(", ", names);
            }
            table.add(new StandTableDto(
                    stand.getId(),
                    stand.getPrice(),
                    stand.getPublisherQuantity() > 0,
                    publishers
            ));
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put(ResponseKeys.ROWS, table);
        return Response.success("Listado de stands", data);
    }
}
