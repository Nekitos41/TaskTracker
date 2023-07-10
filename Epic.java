package sprint_2.task_tracker;

import java.util.HashMap;

public class Epic extends Task {
    private HashMap<Integer, Subtask> mapOfSubtasks = new HashMap<>();

    public Epic(String name, String description, int id, String status) {
        super(name, description, id, status);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status='" + getStatus() + '\'' +
                '}';
    }

    public HashMap<Integer, Subtask> getMapOfSubtasks() {
        return mapOfSubtasks;
    }

    public void setMapOfSubtasks(HashMap<Integer, Subtask> mapOfSubtasks) {
        this.mapOfSubtasks = mapOfSubtasks;
    }
}
