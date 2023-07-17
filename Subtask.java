package sprint_2.task_tracker;

public class Subtask extends Task {
    @Override
    public String toString() {
        return "Subtask{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status='" + getStatus() + '\'' +
                '}';
    }

    public Subtask(String name, String description, int id, Status status) {
        super(name, description, id, status);
    }
}
