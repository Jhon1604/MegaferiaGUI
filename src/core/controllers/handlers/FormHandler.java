package core.controllers.handlers;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 * Utilidades para manejar formularios
 */
public class FormHandler {

    /**
     * Limpia cualquier cantidad de campos de texto que se le pasen
     */
    public void resetTextFields(JTextField... fields) {
        if (fields == null) {
            return;
        }
        for (JTextField field : fields) {
            if (field != null) {
                field.setText("");
            }
        }
    }

    /**
     * Selecciona la primera opción disponible en el combo
     */
    public void selectFirstOption(JComboBox<?> comboBox) {
        if (comboBox != null && comboBox.getItemCount() > 0) {
            comboBox.setSelectedIndex(0);
        }
    }

    /**
     * Marca un botón dentro del grupo y limpia el resto
     */
    public void selectDefault(ButtonGroup group, JToggleButton defaultButton) {
        if (group == null || defaultButton == null) {
            return;
        }
        group.clearSelection();
        defaultButton.setSelected(true);
    }
}

