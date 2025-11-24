package core.controllers.dto;

/**
 * Ppara llenar combos en la vista
 */
public class SelectionOptionDto {

    private final String value;
    private final String label;

    public SelectionOptionDto(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
