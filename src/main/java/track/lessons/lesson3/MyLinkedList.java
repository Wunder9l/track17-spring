package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List implements Stack, Queue {

    Node first;
    Node last;

    /**
     * private - используется для сокрытия этого класса от других.
     * Класс доступен только изнутри того, где он объявлен
     * <p>
     * static - позволяет использовать Node без создания экземпляра внешнего класса
     */
    private static class Node {
        Node prev;
        Node next;
        int val;

        Node(Node prev, Node next, int val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }

    public MyLinkedList() {
        first = last = null;
    }

    @Override
    void add(int item) {
        if (null == last) {
            first = new Node(null, null, item);
            last = first;
        } else {
            last.next = new Node(last, null, item);
            last = last.next;
        }
        ++size;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        if (idx == 0) {
            return pop();
        } else if (idx == size - 1) {
            return dequeue();
        } else {
            Node currentNode = getNode(idx);
            int returnValue = currentNode.val;
            for (int i = idx; i < size; ++i) {
                if (currentNode.prev != null) {
                    currentNode.prev.next = currentNode.next;
                }
                if (currentNode.next != null) {
                    currentNode.next.prev = currentNode.prev;
                }
            }
            --size;
            return returnValue;
        }
    }

    private Node getNode(int idx) throws NoSuchElementException {
        if ((idx >= 0) && (idx < size)) {
            Node currentNode = first;
            for (int i = 0; i < idx; ++i) {
                currentNode = currentNode.next;
            }
            return currentNode;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        return getNode(idx).val;
    }

    @Override
    public int pop() throws NoSuchElementException {
        if (first == null) {
            throw new NoSuchElementException();
        } else {
            int returnValue;
            returnValue = first.val;
            first = first.next;
            --size;
            if (size == 0) {
                last = first;
            } else {
                first.prev = null;
            }
            return returnValue;
        }
    }

    @Override
    public void push(int value) {
        Node node = new Node(null, first, value);
        if (null != first) {
            first.prev = node;
            first = node;
        } else {
            first = last = node;
        }
        ++size;
    }

    @Override
    public int dequeue() {
        if (last == null) {
            throw new NoSuchElementException();
        } else {
            int returnValue;
            returnValue = last.val;
            last = last.prev;
            --size;
            if (size == 0) {
                last = first = last;
            } else {
                last.next = null;
            }
            return returnValue;
        }
    }

    @Override
    public void enqueue(int value) {
        push(value);
    }
}
