package sprint_2.task_tracker;

import java.util.ArrayList;
import java.util.Set;

public interface HistoryManager {
    void add(Task task);
    void remove(int id);
    void linkLast(Task task);
    ArrayList<Task> getTasks();
}
