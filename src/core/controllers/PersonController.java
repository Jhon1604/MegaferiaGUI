package core.controllers;

import core.controllers.dto.PersonCreationRequest;
import core.controllers.dto.PersonTableDto;
import core.controllers.dto.SelectionOptionDto;
import core.controllers.interfaces.IPersonController;
import core.controllers.utils.Response;
import core.controllers.utils.ResponseKeys;
import core.controllers.utils.ValidationUtils;
import core.models.Author;
import core.models.Manager;
import core.models.Narrator;
import core.models.factory.PersonFactory;
import core.observer.DataChangeNotifier;
import core.observer.DataChangeType;
import core.repositories.AuthorRepository;
import core.repositories.ManagerRepository;
import core.repositories.NarratorRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador para personas (autores, gerentes y narradores)
 */
public class PersonController implements IPersonController {

    private final AuthorRepository authorRepository;
    private final ManagerRepository managerRepository;
    private final NarratorRepository narratorRepository;
    private final PersonFactory personFactory;
    private final DataChangeNotifier notifier;

    public PersonController(
            AuthorRepository authorRepository,
            ManagerRepository managerRepository,
            NarratorRepository narratorRepository,
            PersonFactory personFactory,
            DataChangeNotifier notifier) {
        this.authorRepository = authorRepository;
        this.managerRepository = managerRepository;
        this.narratorRepository = narratorRepository;
        this.personFactory = personFactory;
        this.notifier = notifier;
    }

    @Override
    public Response createPerson(PersonCreationRequest request) {
        if (!ValidationUtils.isValidId(request.getId())) {
            return Response.error("El ID de la persona no es válido");
        }
        if (ValidationUtils.isBlank(request.getFirstName()) || ValidationUtils.isBlank(request.getLastName())) {
            return Response.error("El nombre y apellido son obligatorios");
        }
        if (existsInAnyRepository(request.getId())) {
            return Response.error("Ya existe alguien registrado con ese ID");
        }
        if (request.getRole() == null) {
            return Response.error("Debes seleccionar un rol válido");
        }
        switch (request.getRole()) {
            case AUTHOR -> authorRepository.save(personFactory.createAuthor(request));
            case MANAGER -> managerRepository.save(personFactory.createManager(request));
            case NARRATOR -> narratorRepository.save(personFactory.createNarrator(request));
            default -> {
                return Response.error("Rol no soportado");
            }
        }
        notifier.emit(DataChangeType.PERSONS);
        return Response.success("Persona creada correctamente");
    }

    @Override
    public Response getAuthorOptions() {
        List<SelectionOptionDto> options = authorRepository.findAllOrdered()
                .stream()
                .map(author -> new SelectionOptionDto(
                String.valueOf(author.getId()),
                author.getId() + " - " + author.getFullname()))
                .collect(Collectors.toList());
        return buildOptionsResponse("Autores disponibles", options);
    }

    @Override
    public Response getManagerOptions() {
        List<SelectionOptionDto> options = managerRepository.findAllOrdered()
                .stream()
                .map(manager -> new SelectionOptionDto(
                String.valueOf(manager.getId()),
                manager.getId() + " - " + manager.getFullname()))
                .collect(Collectors.toList());
        return buildOptionsResponse("Gerentes disponibles", options);
    }

    @Override
    public Response getNarratorOptions() {
        List<SelectionOptionDto> options = narratorRepository.findAllOrdered()
                .stream()
                .map(narrator -> new SelectionOptionDto(
                String.valueOf(narrator.getId()),
                narrator.getId() + " - " + narrator.getFullname()))
                .collect(Collectors.toList());
        return buildOptionsResponse("Narradores disponibles", options);
    }

    @Override
    public Response getPersonTable() {
        List<PersonTableDto> rows = new ArrayList<>();
        for (Author author : authorRepository.findAllOrdered()) {
            rows.add(new PersonTableDto(
                    author.getId(),
                    author.getFullname(),
                    "Autor",
                    "-",
                    author.getBookQuantity()
            ));
        }
        for (Manager manager : managerRepository.findAllOrdered()) {
            String detail = manager.getPublisher() != null ? manager.getPublisher().getName() : "-";
            rows.add(new PersonTableDto(
                    manager.getId(),
                    manager.getFullname(),
                    "Gerente",
                    detail,
                    0
            ));
        }
        for (Narrator narrator : narratorRepository.findAllOrdered()) {
            rows.add(new PersonTableDto(
                    narrator.getId(),
                    narrator.getFullname(),
                    "Narrador",
                    "-",
                    narrator.getBookQuantity()
            ));
        }
        rows.sort(Comparator.comparingLong(PersonTableDto::getId));
        HashMap<String, Object> data = new HashMap<>();
        data.put(ResponseKeys.ROWS, rows);
        return Response.success("Tabla de personas", data);
    }

    private Response buildOptionsResponse(String message, List<SelectionOptionDto> options) {
        HashMap<String, Object> data = new HashMap<>();
        data.put(ResponseKeys.OPTIONS, options);
        return Response.success(message, data);
    }

    private boolean existsInAnyRepository(long id) {
        return authorRepository.exists(id) || managerRepository.exists(id) || narratorRepository.exists(id);
    }
}
