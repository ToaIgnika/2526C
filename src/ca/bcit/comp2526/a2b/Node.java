package ca.bcit.comp2526.a2b;

/***
 * Node.java.
 * 
 *
 * "I made this code longer than usual because I lack time to make it short"
 * 
 * @author Yevhen
 * @version Nov 27, 2017
 *
 * @param <E>
 */
public class Node<E> {
    private Node<E> prev;
    private Node<E> next;
    private E data;

    /**
     * Default constructor for Node object.
     */
    Node() {
        prev = null;
        next = null;
        data = null;
    }

    /**
     * Constructor for Node.
     * 
     * @param data
     *            of type E.
     * @param prev
     *            of type Node.
     * @param next
     *            of type Node.
     */
    Node(E data, Node<E> prev, Node<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Getter for data.
     * 
     * @return data as E.
     */
    E getData() {
        return data;
    }

    /**
     * Getter for previous.
     * 
     * @return prev as Node.
     */
    Node<E> getPrev() {
        return prev;
    }

    /**
     * getter for next.
     * 
     * @return next as Node.
     */
    Node<E> getNext() {
        return next;
    }

    /**
     * setter for data.
     * 
     * @param data
     *            of type E.
     */
    void setData(E data) {
        this.data = data;
    }

    /**
     * setter for prev.
     * 
     * @param prev
     *            of type Node.
     */
    void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    /**
     * setter for next.
     * 
     * @param next
     *            of type Node.
     */
    void setNext(Node<E> next) {
        this.next = next;
    }

}
