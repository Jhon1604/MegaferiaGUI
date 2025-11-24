package core.observer;

/**
 * Evento que indica qué tipo de dato cambió
 */
public class DataChangeEvent {

    private final DataChangeType type;

    public DataChangeEvent(DataChangeType type) {
        this.type = type;
    }

    public DataChangeType getType() {
        return type;
    }
}
