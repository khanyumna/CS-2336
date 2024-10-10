//Name: Yumna Khan ID: YK220040

public class Node<E extends Comparable<E>> {
    private E payload;
    private Node<E> next;
    private Node<E> previous;

    public Node(E payload){
        this.payload = payload;
        this.next = null;
        this.previous = null;
    }

    public E getPayload() {
        return payload;
    }

    public void setPayload(E payload){
        this.payload = payload;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next){
        this.next = next;
    }
    
    public Node<E> getPrevious() {
        return previous;
    }

    public void setPrevious (Node<E> previous){
        this.previous = previous;
    }

    @Override
    public String toString(){
        return payload.toString();
    }
}
