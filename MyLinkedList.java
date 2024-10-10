//Name: Yumna Khan ID: YK220040

public class MyLinkedList<E extends Comparable<E>> {
    private Node<E> head;
    private Node<E> tail;

    public MyLinkedList() {
        head = null;
        tail = null;
    }

    public MyLinkedList(Node<E> initialNode) {
        head = initialNode;
        tail = initialNode;
    }

    public void add(Node<E> node){
        if (head == null){
            head = node;
            tail = node;
        }
        else {
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
        }
    }

    private Node<E> findMiddle(Node<E> head){
        if (head == null){
            return null;
        }
        else {
            Node<E> walk = head;
            Node<E> run = head.getNext();
            
            while(run != null){
                run = run.getNext();
                if (run != null){
                    walk = walk.getNext();
                    run = run.getNext();
                }
            } return walk;
        } 
    }

    private Node<E> merge(Node<E> left,Node<E> right){
        if (left == null) {
            return right;
        }
        if (right == null){
            return left;
        }

        if (left.getPayload().compareTo(right.getPayload()) <= 0){
            left.setNext(merge(left.getNext(), right));
            left.getNext().setPrevious(left);
            left.setPrevious(null);
            return left;
        }
        else {
            right.setNext(merge(left, right.getNext()));
            right.getNext().setPrevious(right);
            right.setPrevious(null);
            return right;

        }
    }

    public Node<E> mergeSort(Node<E> head){
        if (head == null || head.getNext() == null) {
            return head;
        }

        Node<E> mid = findMiddle(head);
        Node<E> nextToMid = mid.getNext();
        mid.setNext(null);

        Node<E> left = mergeSort(head);
        Node<E> right = mergeSort(nextToMid);

        return merge(left, right);
    }
    
    public void sort() {
        if (head == null || head == tail){
            return;
        }
        else {
            head = mergeSort(head);
            Node<E> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            tail = current;
        }
    }

    public MyLinkedList<E> searchArea(double area){
        MyLinkedList<E> matchedDrivers = new MyLinkedList<>();
        Node<E> current = head;

        while (current != null){
            if (current.getPayload() instanceof Driver){
                Driver driver = (Driver) current.getPayload();
                if (Math.abs(driver.getArea() - area) < 0.02){
                    matchedDrivers.add(new Node<>(current.getPayload()));
                }
            }
            current = current.getNext();
        }
        return matchedDrivers;
    }

    public MyLinkedList<E> searchName(String name){
        MyLinkedList<E> matchedDrivers = new MyLinkedList<>();
        Node<E> current = head;

        while (current != null){
            if (current.getPayload() instanceof Driver){
                Driver driver = (Driver) current.getPayload();
                if (driver.getName().equalsIgnoreCase(name)){
                    matchedDrivers.add(current);
                }
            } current = current.getNext();
        } return matchedDrivers;
    }
    @Override
    public String toString() {
        String string = "";
        Node<E> current = head;
        while (current != null) {
            string += current.getPayload() + "\n";
            current = current.getNext();
        }
        return string;
    }
}




