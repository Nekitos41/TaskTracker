package sprint_2.task_tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Manager {
    static Scanner scanner = new Scanner(System.in);
    static HashMap<Integer, Task> mapOfTasks = new HashMap<>();
    static HashMap<Integer, Epic> mapOfEpics = new HashMap<>();
    static ArrayList<Task> listOfTasks = new ArrayList<>();
    static ArrayList<Epic> listOfEpics = new ArrayList<>();
    static ArrayList<Subtask> listOfSubtasks = new ArrayList<>();
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
        //System.out.println("3 - Get record by id.");
        //System.out.println("4 - Exit.");
    }

    private static void outputTypeOfRecords() {
        System.out.println("1 - Create a task.");
        System.out.println("2 - Create an epic.");
        System.out.println("3 - Create a subtask.");
        System.out.println("4 - Return to menu.");
    }

    private static void getUserChoice() {
        int num = 0;
        while (num != 4) {
            outputMenu();
            num = inputNumber(1, 4, "Input your choice: ");
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
            } else if (num == 2) {
                isCorrect = false;
                obj = getInfoFromUser("epic");
                mapOfEpics.put(idEpic, (Epic) obj);
            } else if (num == 3) {
                Epic epic = chooseEpic();
                if (epic != null) {
                    isCorrect = false;
                    obj = getInfoFromUser("subtask");
                    HashMap<Integer, Subtask> map = epic.getMapOfSubtasks();
                    if (map != null) {
                        int size = map.size();
                        map.put(size + 1, (Subtask) obj);
                        epic.setMapOfSubtasks(map);
                    } else {
                        map = new HashMap<>();
                        map.put(1, (Subtask) obj);
                        epic.setMapOfSubtasks(map);
                    }
                } else {
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
            listOfSubtasks.clear();
        }
    }

    private static Epic chooseEpic() {
        int size = mapOfEpics.size();
        if (size > 0) {
            outputAllEpics();
            int num = inputNumber(1, size, "Input your choice: ");
            return mapOfEpics.get(num);
        } else {
            System.out.println("Firstly add an epic.");
            return null;
        }
    }

    private static void outputAllRecords() {
        int num = inputNumber(1, 4, "Input your choice: ");
        if (num == 1) {
            outputAllTasks();
        } else if (num == 2) {
            outputAllEpics();
        } else if (num == 3) {
            //outputAllSubtasks();
        }
    }

    private static void outputAllTasks() {
        int i = 1;
        for (Task elem: mapOfTasks.values()) {
            System.out.println(i + " - " + elem.getName() + " " + elem.getDescription()
                    + " " + elem.getId() + " " + elem.getStatus());
            i++;
        }
    }

    private static void outputAllEpics() {
        int i = 1;
        for (Epic elem: mapOfEpics.values()) {
            System.out.println(i + " - " + elem.getName() + " " + elem.getDescription()
                    + " " + elem.getId() + " " + elem.getStatus());
            i++;
        }
    }

//    private static void outputAllSubtasks() {
//        for (int i = 0; i < listOfSubtasks.size(); i++) {
//            System.out.println((i + 1) + " - " + listOfSubtasks.get(i).getName() + " "
//                    + listOfSubtasks.get(i).getDescription() + " " + listOfSubtasks.get(i).getId()
//                    + " " + listOfSubtasks.get(i).getStatus());
//        }
//    }
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
        }
    }
}
