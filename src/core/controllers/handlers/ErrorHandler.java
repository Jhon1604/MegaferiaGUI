package core.controllers.handlers;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Maneja la visualización de errores
 */
public class ErrorHandler {

    /**
     * Muestra un mensaje de advertencia
     */
    public void showWarning(Component parent, String message) {
        JOptionPane.showMessageDialog(
                parent,
                message,
                "Dato inválido",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Log simple para errores inesperados
     */
    public void logUnexpected(Exception ex) {
        if (ex != null) {
            System.err.println("Algo raro pasó en la UI: " + ex.getMessage());
            ex.printStackTrace(System.err);
        }
    }
}

