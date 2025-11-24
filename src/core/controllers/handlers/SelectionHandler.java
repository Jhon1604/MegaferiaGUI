package core.controllers.handlers;

import core.controllers.dto.SelectionOptionDto;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * Maneja la lógica de combos y listas
 */
public class SelectionHandler {

    public void updateCombo(JComboBox<SelectionOptionDto> combo,
            List<SelectionOptionDto> options, String placeholder) {
        DefaultComboBoxModel<SelectionOptionDto> model = new DefaultComboBoxModel<>();
        if (placeholder != null) {
            model.addElement(new SelectionOptionDto("", placeholder));
        }
        if (options != null) {
            for (SelectionOptionDto option : options) {
                model.addElement(option);
            }
        }
        combo.setModel(model);
    }

    public void cleanupSelections(Set<String> selections, List<SelectionOptionDto> options) {
        if (selections == null || selections.isEmpty() || options == null) {
            return;
        }
        Set<String> available = new HashSet<>();
        for (SelectionOptionDto option : options) {
            if (option.getValue() != null) {
                available.add(option.getValue());
            }
        }
        selections.removeIf(value -> !available.contains(value));
    }

    public String buildSelectionText(Set<String> selections, List<SelectionOptionDto> options) {
        if (selections == null || selections.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String value : selections) {
            SelectionOptionDto option = findOptionByValue(options, value);
            if (option != null) {
                builder.append(option.getLabel()).append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    public SelectionOptionDto findOptionByValue(List<SelectionOptionDto> options, String value) {
        if (options == null || value == null) {
            return null;
        }
        for (SelectionOptionDto option : options) {
            if (value.equals(option.getValue())) {
                return option;
            }
        }
        return null;
    }
}

