package core.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación base para observadores
 */
public abstract class AbstractObservable implements IObservable {

    private final List<IObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(IObserver observer) {
        if (observer == null || observers.contains(observer)) {
            return;
        }
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(DataChangeEvent event) {
        for (IObserver observer : new ArrayList<>(observers)) {
            observer.update(event);
        }
    }
}
