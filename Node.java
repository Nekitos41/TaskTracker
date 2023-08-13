package sprint_2.task_tracker;

public class Node {
    Task data;
    Node next;
    Node prev;
    static Node first;
    static Node last;

    public Node(Task data, Node next, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}

