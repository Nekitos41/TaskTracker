package sprint_2.task_tracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static sprint_2.task_tracker.InMemoryTaskManager.*;

class EpicTest {
    private static FileBackedTasksManager tasksManager;
    private static Epic epic;

    @BeforeAll
    public static void createManager() {
        tasksManager = new FileBackedTasksManager();
    }

    @BeforeEach
    public void createEpic() {
        epic = new Epic("epic1", "description epic", 1, Status.NEW);
    }

    @Test
    public void epicStatusShouldBeNewWithoutSubtasks() {
        mapOfEpics.put(1, epic);
        tasksManager.setStatusToEpic();
        Assertions.assertEquals(epic.getStatus(), Status.NEW);
    }

    @Test
    public void epicStatusShouldBeNewWithSubtasksStatusNew() {
        HashMap<Integer, Subtask> mapOfSubtasks = new HashMap<>();
        Subtask subtask1 = new Subtask("subtask1", "descripton1", 1, Status.NEW);
        Subtask subtask2 = new Subtask("subtask2", "descripton2", 2, Status.NEW);
        Subtask subtask3 = new Subtask("subtask3", "descripton3", 3, Status.NEW);
        Subtask subtask4 = new Subtask("subtask4", "descripton4", 4, Status.NEW);
        mapOfSubtasks.put(1, subtask1);
        mapOfSubtasks.put(2, subtask2);
        mapOfSubtasks.put(3, subtask3);
        mapOfSubtasks.put(4, subtask4);
        epic.setMapOfSubtasks(mapOfSubtasks);
        mapOfEpics.put(1, epic);
        tasksManager.setStatusToEpic();
        Assertions.assertEquals(epic.getStatus(), Status.NEW);
    }

    @Test
    public void epicStatusShouldBeDoneWithSubtasksStatusDone() {
        HashMap<Integer, Subtask> mapOfSubtasks = new HashMap<>();
        Subtask subtask1 = new Subtask("subtask1", "descripton1", 1, Status.DONE);
        Subtask subtask2 = new Subtask("subtask2", "descripton2", 2, Status.DONE);
        Subtask subtask3 = new Subtask("subtask3", "descripton3", 3, Status.DONE);
        Subtask subtask4 = new Subtask("subtask4", "descripton4", 4, Status.DONE);
        mapOfSubtasks.put(1, subtask1);
        mapOfSubtasks.put(2, subtask2);
        mapOfSubtasks.put(3, subtask3);
        mapOfSubtasks.put(4, subtask4);
        epic.setMapOfSubtasks(mapOfSubtasks);
        mapOfEpics.put(1, epic);
        tasksManager.setStatusToEpic();
        Assertions.assertEquals(epic.getStatus(), Status.DONE);
    }

    @Test
    public void epicStatusShouldBeInProgressWithSubtasksStatusDoneAndNew() {
        HashMap<Integer, Subtask> mapOfSubtasks = new HashMap<>();
        Subtask subtask1 = new Subtask("subtask1", "descripton1", 1, Status.DONE);
        Subtask subtask2 = new Subtask("subtask2", "descripton2", 2, Status.NEW);
        Subtask subtask3 = new Subtask("subtask3", "descripton3", 3, Status.DONE);
        Subtask subtask4 = new Subtask("subtask4", "descripton4", 4, Status.NEW);
        mapOfSubtasks.put(1, subtask1);
        mapOfSubtasks.put(2, subtask2);
        mapOfSubtasks.put(3, subtask3);
        mapOfSubtasks.put(4, subtask4);
        epic.setMapOfSubtasks(mapOfSubtasks);
        mapOfEpics.put(1, epic);
        tasksManager.setStatusToEpic();
        Assertions.assertEquals(epic.getStatus(), Status.IN_PROGRESS);
    }

    @Test
    public void epicStatusShouldBeDoneWithSubtasksStatusInProgress() {
        HashMap<Integer, Subtask> mapOfSubtasks = new HashMap<>();
        Subtask subtask1 = new Subtask("subtask1", "descripton1", 1, Status.IN_PROGRESS);
        Subtask subtask2 = new Subtask("subtask2", "descripton2", 2, Status.IN_PROGRESS);
        Subtask subtask3 = new Subtask("subtask3", "descripton3", 3, Status.IN_PROGRESS);
        Subtask subtask4 = new Subtask("subtask4", "descripton4", 4, Status.IN_PROGRESS);
        mapOfSubtasks.put(1, subtask1);
        mapOfSubtasks.put(2, subtask2);
        mapOfSubtasks.put(3, subtask3);
        mapOfSubtasks.put(4, subtask4);
        epic.setMapOfSubtasks(mapOfSubtasks);
        mapOfEpics.put(1, epic);
        tasksManager.setStatusToEpic();
        Assertions.assertEquals(epic.getStatus(), Status.IN_PROGRESS);
    }
}