package sprint_2.task_tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public interface HistoryManager {
    void add(Task task);
    void remove(int id);
    Set<Task> getHistory();

    void linkLast(Task task);
    ArrayList<Task> getTasks();
    void removeNode(Task task);
}
