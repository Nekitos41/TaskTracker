package sprint_2.task_tracker;

import java.util.Set;

public interface HistoryManager {
    void add(Task task);
    void remove(int id);
    Set<Task> getHistory();
}
