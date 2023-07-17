package sprint_2.task_tracker;

public class Managers {
    TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
}