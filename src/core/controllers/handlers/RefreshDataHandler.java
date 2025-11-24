package core.controllers.handlers;

import core.observer.DataChangeType;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Refresca los datos en la UI
 */
public class RefreshDataHandler {

    private final Map<DataChangeType, List<Runnable>> refreshTasks = new EnumMap<>(DataChangeType.class);

    public void register(DataChangeType type, Runnable task) {
        if (type == null || task == null) {
            return;
        }
        refreshTasks.computeIfAbsent(type, key -> new ArrayList<>()).add(task);
    }

    public void dispatch(DataChangeType type) {
        if (type == null) {
            return;
        }
        List<Runnable> tasks = refreshTasks.get(type);
        if (tasks == null) {
            return;
        }
        for (Runnable task : new ArrayList<>(tasks)) {
            runAsync(task);
        }
    }

    public void runAsync(Runnable action) {
        if (action == null) {
            return;
        }
        java.awt.EventQueue.invokeLater(action);
    }
}

