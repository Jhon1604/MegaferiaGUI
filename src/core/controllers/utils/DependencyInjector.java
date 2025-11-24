package core.controllers.utils;

import core.controllers.BookController;
import core.controllers.PersonController;
import core.controllers.PublisherController;
import core.controllers.PurchaseController;
import core.controllers.QueryController;
import core.controllers.StandController;
import core.controllers.ResponseProcessor;
import core.controllers.handlers.ErrorHandler;
import core.controllers.handlers.FormHandler;
import core.controllers.handlers.MessageHandler;
import core.controllers.handlers.RefreshDataHandler;
import core.controllers.handlers.SelectionHandler;
import core.controllers.interfaces.IBookController;
import core.controllers.interfaces.IPersonController;
import core.controllers.interfaces.IPublisherController;
import core.controllers.interfaces.IPurchaseController;
import core.controllers.interfaces.IQueryController;
import core.controllers.interfaces.IStandController;
import core.models.factory.ModelFactoryManager;
import core.observer.DataChangeNotifier;
import core.repositories.AuthorRepository;
import core.repositories.BookRepository;
import core.repositories.ManagerRepository;
import core.repositories.NarratorRepository;
import core.repositories.PublisherRepository;
import core.repositories.StandRepository;
import core.views.MegaferiaFrame;

/**
 * Configura y entrega las dependencias a la app
 */
public class DependencyInjector {

    private MegaferiaFrame mainFrame;
    private ErrorHandler errorHandler;
    private MessageHandler messageHandler;
    private FormHandler formHandler;
    private RefreshDataHandler refreshDataHandler;
    private SelectionHandler selectionHandler;
    private ResponseProcessor responseProcessor;

    public DependencyInjector initialize() {
        StandRepository standRepository = new StandRepository();
        AuthorRepository authorRepository = new AuthorRepository();
        ManagerRepository managerRepository = new ManagerRepository();
        NarratorRepository narratorRepository = new NarratorRepository();
        PublisherRepository publisherRepository = new PublisherRepository();
        BookRepository bookRepository = new BookRepository();
        DataChangeNotifier notifier = new DataChangeNotifier();

        ModelFactoryManager factoryManager = new ModelFactoryManager();
        
        // Controladores
        IStandController standController = createStandController(standRepository, factoryManager, notifier);
        IPersonController personController = createPersonController(authorRepository, managerRepository, narratorRepository, factoryManager, notifier);
        IPublisherController publisherController = createPublisherController(publisherRepository, managerRepository, factoryManager, notifier);
        IBookController bookController = createBookController(bookRepository, authorRepository, publisherRepository, narratorRepository, factoryManager, notifier);
        IPurchaseController purchaseController = createPurchaseController(standRepository, publisherRepository, factoryManager, notifier);
        IQueryController queryController = createQueryController(authorRepository, bookRepository);
        
        // Handlers UI
        initializeHandlers();
        
        // Vista principal
        this.mainFrame = new MegaferiaFrame(
                standController,
                personController,
                publisherController,
                bookController,
                purchaseController,
                queryController,
                notifier,
                errorHandler,
                messageHandler,
                formHandler,
                refreshDataHandler,
                responseProcessor,
                selectionHandler
        );
        
        return this;
    }

    /**
     * Crea el controlador de stands
     */
    private IStandController createStandController(StandRepository standRepository, 
            ModelFactoryManager factoryManager, DataChangeNotifier notifier) {
        return new StandController(standRepository, factoryManager.getStandFactory(), notifier);
    }

    /**
     * Crea el controlador de personas
     */
    private IPersonController createPersonController(AuthorRepository authorRepository,
            ManagerRepository managerRepository, NarratorRepository narratorRepository,
            ModelFactoryManager factoryManager, DataChangeNotifier notifier) {
        return new PersonController(authorRepository, managerRepository, narratorRepository, 
                factoryManager.getPersonFactory(), notifier);
    }

    /**
     * Crea el controlador de editoriales
     */
    private IPublisherController createPublisherController(PublisherRepository publisherRepository,
            ManagerRepository managerRepository, ModelFactoryManager factoryManager, 
            DataChangeNotifier notifier) {
        return new PublisherController(publisherRepository, managerRepository, 
                factoryManager.getPublisherFactory(), notifier);
    }

    /**
     * Crea el controlador de libros
     */
    private IBookController createBookController(BookRepository bookRepository,
            AuthorRepository authorRepository, PublisherRepository publisherRepository,
            NarratorRepository narratorRepository, ModelFactoryManager factoryManager,
            DataChangeNotifier notifier) {
        return new BookController(bookRepository, authorRepository, publisherRepository, 
                narratorRepository, factoryManager.getBookFactory(), notifier);
    }

    /**
     * Crea el controlador de compras
     */
    private IPurchaseController createPurchaseController(StandRepository standRepository,
            PublisherRepository publisherRepository, ModelFactoryManager factoryManager,
            DataChangeNotifier notifier) {
        return new PurchaseController(standRepository, publisherRepository, 
                factoryManager.getPublisherAssignmentFactory(), notifier);
    }

    /**
     * Crea el controlador de consultas
     */
    private IQueryController createQueryController(AuthorRepository authorRepository,
            BookRepository bookRepository) {
        return new QueryController(authorRepository, bookRepository);
    }

    /**
     * Inicializa todos los handlers de UI
     */
    private void initializeHandlers() {
        this.errorHandler = new ErrorHandler();
        this.messageHandler = new MessageHandler(errorHandler);
        this.formHandler = new FormHandler();
        this.refreshDataHandler = new RefreshDataHandler();
        this.selectionHandler = new SelectionHandler();
        this.responseProcessor = new ResponseProcessor(messageHandler);
    }

    public MegaferiaFrame getMainFrame() {
        return mainFrame;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public FormHandler getFormHandler() {
        return formHandler;
    }

    public RefreshDataHandler getRefreshDataHandler() {
        return refreshDataHandler;
    }

    public SelectionHandler getSelectionHandler() {
        return selectionHandler;
    }

    public ResponseProcessor getResponseProcessor() {
        return responseProcessor;
    }
}

