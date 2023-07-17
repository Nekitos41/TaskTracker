package sprint_2.task_tracker;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    static ArrayList<Task> listOfHistory = new ArrayList<>();
    @Override
    public void add(Task task) {
        final int MAX_SIZE = 10;
        if (listOfHistory.size() == MAX_SIZE) {
            listOfHistory.remove(0);
        }
        listOfHistory.add(task);
    }

    @Override
    public void getHistory() {
        if (!(listOfHistory.isEmpty())) {
            for (Task elem : listOfHistory) {
                System.out.println(elem);
            }
        } else {
            System.out.println("List of history is empty.");
        }
    }
}
