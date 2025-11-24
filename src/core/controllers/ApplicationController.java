package core.controllers;

import core.controllers.handlers.ErrorHandler;
import core.controllers.handlers.FormHandler;
import core.controllers.handlers.MessageHandler;
import core.controllers.handlers.RefreshDataHandler;
import core.controllers.handlers.SelectionHandler;
import core.controllers.interfaces.IApplicationController;
import core.controllers.utils.DependencyInjector;
import core.views.MegaferiaFrame;
import core.controllers.ResponseProcessor;

/**
 * Orquesta la aplicación utilizando el inyector
 */
public class ApplicationController implements IApplicationController {

    private final DependencyInjector injector;

    public ApplicationController(DependencyInjector injector) {
        this.injector = injector;
    }

    @Override
    public void start() {
        java.awt.EventQueue.invokeLater(() -> injector.getMainFrame().setVisible(true));
    }

    @Override
    public MegaferiaFrame getMainFrame() {
        return injector.getMainFrame();
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return injector.getErrorHandler();
    }

    @Override
    public MessageHandler getMessageHandler() {
        return injector.getMessageHandler();
    }

    @Override
    public FormHandler getFormHandler() {
        return injector.getFormHandler();
    }

    @Override
    public RefreshDataHandler getRefreshDataHandler() {
        return injector.getRefreshDataHandler();
    }

    @Override
    public SelectionHandler getSelectionHandler() {
        return injector.getSelectionHandler();
    }

    public ResponseProcessor getResponseProcessor() {
        return injector.getResponseProcessor();
    }
}

