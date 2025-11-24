package core.observer;

/**
 * Notificador para eventos para refrescar info
 */
public class DataChangeNotifier extends AbstractObservable {

    public void emit(DataChangeType type) {
        notifyObservers(new DataChangeEvent(type));
    }
}
