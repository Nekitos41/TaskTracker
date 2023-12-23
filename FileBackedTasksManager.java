package sprint_2.task_tracker;

import java.io.*;
import java.nio.file.Path;

public class FileBackedTasksManager extends InMemoryTaskManager {
    private static final String str = "file.txt";

    static void save(TypeOfTask typeOfTask) {
        String separator = ",";
        StringBuilder sb = new StringBuilder();
        try (Writer fileWriter = new FileWriter(str, true)) {
            if (typeOfTask == TypeOfTask.TASK) {
                sb.append(mapOfTasks.get(idTask - 1).getId()).append(separator);
                sb.append(mapOfTasks.get(idTask - 1).getName()).append(separator);
                sb.append(mapOfTasks.get(idTask - 1).getDescription()).append(separator);
                sb.append(mapOfTasks.get(idTask - 1).getStatus()).append(separator);
                sb.append(typeOfTask);
                sb.append("\n");
                fileWriter.write(sb.toString());
            } else if (typeOfTask == TypeOfTask.EPIC) {
                sb.append(mapOfEpics.get(idEpic - 1).getId()).append(separator);
                sb.append(mapOfEpics.get(idEpic - 1).getName()).append(separator);
                sb.append(mapOfEpics.get(idEpic - 1).getDescription()).append(separator);
                sb.append(mapOfEpics.get(idEpic - 1).getStatus()).append(separator);
                sb.append(typeOfTask);
                sb.append("\n");
                fileWriter.write(sb.toString());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static void loadFromFile() {
        String separator = ",";
        Status status = null;
        try (FileReader fileReader = new FileReader(str);
             BufferedReader reader = new BufferedReader(fileReader)) {
            while (reader.ready()) {
                String[] elem = reader.readLine().split(separator);
                if (elem[3].equals("NEW")) {
                    status = Status.NEW;
                } else if (elem[3].equals("IN_PROGRESS")) {
                    status = Status.IN_PROGRESS;
                } else if (elem[3].equals("DONE")) {
                    status = Status.DONE;
                }
                if (elem[4].equals("TASK")) {
                    Task task = new Task(elem[1], elem[2], Integer.parseInt(elem[0]), status);
                    mapOfTasks.put(idTask, task);
                    idTask++;
                } else if (elem[4].equals("EPIC")) {
                    Epic epic = new Epic(elem[1], elem[2], Integer.parseInt(elem[0]), status);
                    mapOfEpics.put(idEpic, epic);
                    idEpic++;
                }
            }
        } catch (IOException ex) {
            System.out.println("Impossible to read data from file.");
        }
    }

    @Override
    public TypeOfTask createRecord() {
        TypeOfTask typeOfTask = super.createRecord();
        save(typeOfTask);
        return typeOfTask;
    }

}
