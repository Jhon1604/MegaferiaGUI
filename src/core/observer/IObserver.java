package core.observer;

/**
 * Observador genérico para reaccionar a cambios de datos
 */
public interface IObserver {

    void update(DataChangeEvent event);
}
