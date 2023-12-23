package sprint_2.task_tracker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static sprint_2.task_tracker.Node.first;
import static sprint_2.task_tracker.Node.last;

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
    public ArrayList<Task> getTasks() {
        Node f = first;
        ArrayList<Task> list = new ArrayList<>();
        while (f != null) {
            list.add(f.data);
            f = f.next;
        }
        return list;
    }

    @Override
    public void linkLast(Task task) {
        Node l = last;
        Node newNode = new Node(task, null, l);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
    }
}
