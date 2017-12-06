package ca.bcit.comp2526.a2c;

import java.io.Serializable;
import java.util.Iterator;

/**
 * DoubleLinkedList.java.
 * 
 *
 * "I made this code longer than usual because I lack time to make it short"
 * 
 * @author Yevhen
 * @version Nov 27, 2017
 *
 * @param <E>
 */
public class DoubleLinkedList<E> implements Iterable<E> , Serializable{

    /**
     * serid.
     */
    private static final long serialVersionUID = 1L;

    private static final int MAX_LENGTH = 25;

    /**
     * node for head of the list.
     */
    private Node<E> head;
    /**
     * node for tail of the list.
     */
    private Node<E> tail;
    /**
     * node for current item of the list.
     */
    private Node<E> current;
    private int length;

    /**
     * Default constructor.
     */
    /*
     * DoubleLinkedList() { head = null; tail = null; current = null; length =
     * 0; }
     */


    /**
     * addToFront method.
     * 
     * @param e
     *            of type E.
     * @throws CouldNotAddException
     *             a.
     * @throws CouldNotRemoveException
     *             b.
     */
    public void addToFront2(E e)
            throws CouldNotAddException, CouldNotRemoveException {

        Node<E> temp = new Node<E>(e, null, null);
        if (length < MAX_LENGTH) {
            if (head == null) {
                head = temp;
                tail = temp;
                current = head;
                length++;
            } else {
                temp.setPrev(head);
                head.setNext(temp);
                head = temp;
                current = head;
                length++;
            }
        } else {
            removeFromEnd();
            temp.setPrev(head);
            head.setNext(temp);
            head = temp;
            current = head;
        }
    }

    /**
     * addToFront method.
     * 
     * @param e
     *            of type E.
     * @throws CouldNotAddException
     *             a.
     * @throws CouldNotRemoveException
     *             b.
     */
    public void addToFront(E e) throws CouldNotAddException {

        if (e == null) {
            throw new CouldNotAddException();
        }
        Node<E> temp = new Node<E>(e, null, null);

        if (head == null) {
            head = temp;
            tail = temp;
            current = head;
        } else {
            temp.setPrev(head);
            head.setNext(temp);
            head = temp;
            current = head;
        }
        length++;
    }

    /**
     * removeFromFront method.
     * 
     * @return removed object of type E.
     * @throws CouldNotRemoveException
     *             a.
     */
    public E removeFromFront() throws CouldNotRemoveException {
        if (head == null) {
            throw new CouldNotRemoveException();
        }
        E temp = head.getData();
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.getPrev();
            head.setNext(null);
        }
        length--;
        return temp;
    }

    /**
     * addToEnd method.
     * 
     * @param e
     *            of type E.
     * @throws CouldNotAddException
     *             a.
     */
    public void addToEnd(E e) throws CouldNotAddException {

        Node<E> temp = new Node<E>(e, null, null);
        if (e == null) {
            throw new CouldNotAddException();
        }
        if (tail == null) {
            tail = temp;
            head = temp;
        } else {
            temp.setNext(tail);
            tail.setPrev(temp);
            tail = temp;
        }
        length++;
    }

    /**
     * removeFromEnd method.
     * 
     * @return removed data of type E.
     * @throws CouldNotRemoveException
     *             a.
     */
    public E removeFromEnd() throws CouldNotRemoveException {
        if (tail == null) {
            throw new CouldNotRemoveException();
        }
        E temp = tail.getData();
        if (tail == head) {
            head = null;
            tail = null;
        } else {
            tail = tail.getNext();
            tail.setPrev(null);
        }
        length--;
        return temp;
    }

    /**
     * getter for head.
     * 
     * @return head of type Node.
     */
    public Node<E> getTop() {
        return head;
    }

    /**
     * getter for current.
     * 
     * @return current of type Node.
     */
    public Node<E> getCurrent() {
        return current;
    }

    /**
     * getter for previous current.
     * 
     * @return current of type Node.
     */
    public E prevCurrent() {
        if (current.getPrev() != null) {
            current = current.getPrev();
            return current.getData();
        } else {
            return current.getData();
        }
    }

    /**
     * getter for next current.
     * 
     * @return current of type Node.
     */
    public E nextCurrent() {
        if (current.getNext() != null) {
            current = current.getNext();
            return current.getData();
        } else {
            return current.getData();
        }
    }

    /**
     * method for clearing the list.
     */
    public void clearList() {
        head = null;
        tail = null;
        length = 0;
        System.out.println("cleared");
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public E next() {
                if (curr == null) {
                    return null;
                }
                E ret = curr.getData();
                curr = curr.getPrev();
                return ret;
            }
        };
    }

    /**
     * get first node.
     * @return head as Node.
     */
    public Node<E> getFirst() {
        return head;
    }

    /**
     * get last node.
     * @return tail as Node.
     */
    public Node<E> getLast() {
        return tail;
    }

    /**
     * getter for size.
     * @return return size of dll.
     */
    public int size() {
        return length;
    }
}
