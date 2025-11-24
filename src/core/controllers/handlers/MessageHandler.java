package core.controllers.handlers;

import core.controllers.utils.Response;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Centraliza los mensajes que se muestran al usuario
 */
public class MessageHandler {

    private final ErrorHandler errorHandler;

    public MessageHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    /**
     * Procesa una respuesta y muestra el mensaje correspondiente
     */
    public boolean handleResponse(Component parent, Response response, boolean notifySuccess) {
        if (response == null) {
            errorHandler.showWarning(parent, "No se recibió respuesta del controlador");
            return false;
        }
        boolean success = response.isSuccess();
        if (!success || notifySuccess) {
            JOptionPane.showMessageDialog(
                    parent,
                    response.getMessage(),
                    success ? "Operación exitosa" : "Error",
                    success ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE
            );
        }
        return success;
    }
}

