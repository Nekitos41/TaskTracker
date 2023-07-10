package sprint_2.task_tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Manager {
    static Scanner scanner = new Scanner(System.in);
    static HashMap<Integer, Task> mapOfTasks = new HashMap<>();
    static HashMap<Integer, Epic> mapOfEpics = new HashMap<>();
    static int idTask = 1;
    static int idEpic = 1;
    static int idSubtask = 1;

    public static void main(String[] args) {
        getUserChoice();
    }

    private static int inputNumber(int MIN_NUM, int MAX_NUM, String str) {
        int num = 0;
        boolean isIncorrect;
        do {
            isIncorrect = false;
            System.out.print(str);
            try {
                num = Integer.parseInt(scanner.nextLine());
            } catch (Exception q) {
                isIncorrect = true;
                System.out.println("You didn't enter number. Input must consist only digits.");
            }
            if (!isIncorrect & (num < MIN_NUM || num > MAX_NUM)) {
                isIncorrect = true;
                System.out.println("Invalid input range.");
            }
        } while (isIncorrect);
        return num;
    }

    private static void outputMenu() {
        System.out.println("1 - Create a record.");
        System.out.println("2 - Output list of records.");
        System.out.println("3 - Delete list of records.");
        System.out.println("4 - Get a record by id.");
        System.out.println("5 - Exit.");
    }

    private static void outputTypeOfRecords() {
        System.out.println("1 - Create a task.");
        System.out.println("2 - Create an epic.");
        System.out.println("3 - Create a subtask.");
        System.out.println("4 - Return to menu.");
    }

    private static void getUserChoice() {
        int num = 0;
        while (num != 5) {
            outputMenu();
            num = inputNumber(1, 5, "Input your choice: ");
            executeUserChoice(num);
        }
    }

    private static boolean checkSmth(String str, int num) {
        boolean isIncorrect = false;
        if (str.length() > num) {
            isIncorrect = true;
            System.out.println("Input less long information.");
        }
        return isIncorrect;
    }

    private static Task getInfoFromUser(String str) {
        Task obj = null;
        boolean isIncorrect;
        String description;
        String name;
        do {
            System.out.print("Input name of the " + str + ": ");
            name = scanner.nextLine();
            isIncorrect = checkSmth(name, 12);
        } while (isIncorrect);
        do {
            System.out.print("Input description of the " + str + ": ");
            description = scanner.nextLine();
            isIncorrect = checkSmth(description, 20);
        } while (isIncorrect);
        if (str.equals("task")) {
            obj = new Task(name, description, idTask, "NEW");
        } else if (str.equals("epic")) {
            obj = new Epic(name, description, idEpic, "NEW");
        } else if (str.equals("subtask")) {
            obj = new Subtask(name, description, idSubtask, "NEW");
        }
        return obj;
    }

    private static void createRecord() {
        int num;
        Task obj;
        boolean isCorrect = true;
        num = inputNumber(1, 4, "Input your choice: ");
        do {
            if (num == 1) {
                isCorrect = false;
                obj = getInfoFromUser("task");
                mapOfTasks.put(idTask, obj);
                idTask++;
                System.out.println("The task has been created.");
            } else if (num == 2) {
                isCorrect = false;
                obj = getInfoFromUser("epic");
                mapOfEpics.put(idEpic, (Epic) obj);
                idEpic++;
                System.out.println("The epic has been created.");
            } else if (num == 3) {
                Epic epic = chooseEpic();
                if (epic != null) {
                    isCorrect = false;
                    obj = getInfoFromUser("subtask");
                    HashMap<Integer, Subtask> mapOfSubtasks = epic.getMapOfSubtasks();
                    if (mapOfSubtasks != null) {
                        int size = mapOfSubtasks.size();
                        obj.setId(size + 1);
                        mapOfSubtasks.put(size + 1, (Subtask) obj);
                        epic.setMapOfSubtasks(mapOfSubtasks);
                        System.out.println("The subtask has been created.");
                    } else {
                        mapOfSubtasks = new HashMap<>();
                        mapOfSubtasks.put(1, (Subtask) obj);
                        epic.setMapOfSubtasks(mapOfSubtasks);
                    }
                } else {
                    System.out.println("Firstly add an epic.");
                    outputTypeOfRecords();
                    num = inputNumber(1, 4, "Input your choice: ");
                }
            } else {
                isCorrect = false;
            }
        } while (isCorrect);
    }

    private static void outputTypeOfTasks(String str) {
        System.out.println("1 - " + str + " all tasks.");
        System.out.println("2 - " + str + " all epics.");
        System.out.println("3 - " + str + " all subtasks.");
        System.out.println("4 - Return to menu.");
    }

    private static void deleteAllTasks() {
        int num = inputNumber(1, 4, "Input your choice: ");
        if (num == 1) {
            mapOfTasks.clear();
        } else if (num == 2) {
            mapOfEpics.clear();
        } else if (num == 3) {
            Epic epic = chooseEpic();
            if (epic != null) {
                epic.getMapOfSubtasks().clear();
            }
        }
    }

    private static Epic chooseEpic() {
        int size = mapOfEpics.size();
        if (size > 0) {
            outputAllRecords(mapOfEpics, "epics");
            int num = inputNumber(1, idEpic, "Choose epic by id: ");
            return mapOfEpics.get(num);
        } else {
            return null;
        }
    }

    private static void outputAllRecords() {
        int num = inputNumber(1, 4, "Input your choice: ");
        if (num == 1) {
            outputAllRecords(mapOfTasks, "tasks");
        } else if (num == 2) {
            outputAllRecords(mapOfEpics, "epics");
        } else if (num == 3) {
            Epic epic = chooseEpic();
            if (epic != null) {
                HashMap<Integer, Subtask> mapOfSubtasks = epic.getMapOfSubtasks();
                outputAllRecords(mapOfSubtasks,"subtasks");
            } else {
                System.out.println("List of subtasks of this epic is empty.");
            }
        }
    }

    private static <T> void outputAllRecords(HashMap<Integer, T> map, String str) {
        if (map.size() > 0) {
            map.values().forEach(System.out::println);
        } else {
            System.out.println("List of " + str + " is empty.");
        }
    }

    private static void outputTypeOfTasksToFind() {
        System.out.println("1 - Get a task by id.");
        System.out.println("2 - Get an epic by id.");
        System.out.println("3 - Get a subtask by id.");
        System.out.println("4 - Return to menu.");
    }

    private static void getById() {
        int num = inputNumber(1, 4, "Input your choice: ");
        if (num == 1) {
            getById(mapOfTasks, "task", idTask - 1);
        } else if (num == 2) {
            getById(mapOfEpics, "epic", idEpic - 1);
        } else if (num == 3) {
            Epic epic = chooseEpic();
            if (epic != null) {
                HashMap<Integer, Subtask> mapOfSubtasks = epic.getMapOfSubtasks();
                getById(mapOfSubtasks, "subtask", mapOfSubtasks.size());
            } else {
                System.out.println("List of subtasks of all epics is empty.");
            }
        }
    }

    private static <T> void getById(HashMap<Integer, T> map, String str, int max) {
        if (map.size() > 0) {
            int id = inputNumber(1, max, "Input " + str + "-id you want to find: ");
            if (map.get(id) == null) {
                System.out.println("There is no " + str + " with this id.");
            } else {
                System.out.println(map.get(id));
            }
        } else {
            System.out.println("List of " + str + "s is empty.");
        }
    }

    private static void executeUserChoice(int num) {
        switch (num) {
            case 1:
                outputTypeOfRecords();
                createRecord();
                break;
            case 2:
                outputTypeOfTasks("Output");
                outputAllRecords();
                break;
            case 3:
                outputTypeOfTasks("Delete");
                deleteAllTasks();
                break;
            case 4:
                outputTypeOfTasksToFind();
                getById();
                break;
        }
    }
}
