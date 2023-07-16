package sprint_2.task_tracker;

public interface TaskManager {
    void createRecord();

    void setStatusToEpic();

    void outputAllRecords();

    void deleteAllTasks();

    void getById();

    void updateRecords();

    void deleteRecordById();
}
