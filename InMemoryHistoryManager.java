package sprint_2.task_tracker;

import java.util.HashSet;
import java.util.Set;

public class InMemoryHistoryManager implements HistoryManager {
    static Set<Task> listOfHistory = new HashSet<>();

    @Override
    public void add(Task task) {
        final int MAX_SIZE = 10;
        if (listOfHistory.size() == MAX_SIZE) {
            listOfHistory.remove(0);
        }
        listOfHistory.add(task);
    }

    @Override
    public void remove(int id) {
        listOfHistory.removeIf(task -> {
            return task.getId() == id;
        });
    }

    @Override
    public Set<Task> getHistory() {
        return listOfHistory;
    }
}
