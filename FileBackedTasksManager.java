package sprint_2.task_tracker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileBackedTasksManager extends InMemoryTaskManager {
    private static final String str = "file.txt";

    static <T> void save(TypeOfTask typeOfTask) {
        String separator = ",";
        StringBuilder sb = new StringBuilder();
        try (Writer fileWriter = new FileWriter(str, true)) {
            if (typeOfTask == TypeOfTask.TASK) {
                sb.append(mapOfTasks.get(idTask - 1).getId()).append(separator);
                sb.append(mapOfTasks.get(idTask - 1).getName()).append(separator);
                sb.append(mapOfTasks.get(idTask - 1).getDescription()).append(separator);
                sb.append(mapOfTasks.get(idTask - 1).getStatus());
                sb.append("\n");
                fileWriter.write(sb.toString());
            } else if (typeOfTask == TypeOfTask.EPIC) {
                sb.append(mapOfEpics.get(idEpic - 1).getId()).append(separator);
                sb.append(mapOfEpics.get(idEpic - 1).getName()).append(separator);
                sb.append(mapOfEpics.get(idEpic - 1).getDescription()).append(separator);
                sb.append(mapOfEpics.get(idEpic - 1).getStatus());
                sb.append("\n");
                fileWriter.write(sb.toString());
            } else if (typeOfTask == TypeOfTask.SUBTASK) {
                sb.append(mapOfEpics.get(idEpic - 1).getId()).append(separator);
                sb.append(mapOfEpics.get(idEpic - 1).getName()).append(separator);
                sb.append(mapOfEpics.get(idEpic - 1).getDescription()).append(separator);
                sb.append(mapOfEpics.get(idEpic - 1).getStatus());
                sb.append("\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public TypeOfTask createRecord() {
        TypeOfTask typeOfTask = super.createRecord();
        save(typeOfTask);
        return typeOfTask;
    }

}
