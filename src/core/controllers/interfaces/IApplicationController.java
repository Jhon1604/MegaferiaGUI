package core.controllers.interfaces;

import core.controllers.handlers.ErrorHandler;
import core.controllers.handlers.FormHandler;
import core.controllers.handlers.MessageHandler;
import core.controllers.handlers.RefreshDataHandler;
import core.controllers.ResponseProcessor;
import core.controllers.handlers.SelectionHandler;
import core.views.MegaferiaFrame;

public interface IApplicationController {

    void start();

    MegaferiaFrame getMainFrame();

    ErrorHandler getErrorHandler();

    MessageHandler getMessageHandler();

    FormHandler getFormHandler();

    RefreshDataHandler getRefreshDataHandler();

    SelectionHandler getSelectionHandler();

    ResponseProcessor getResponseProcessor();
}

