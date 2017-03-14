package track.lessons.lesson3;

import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List /*implements Queue */ {

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

    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        if (idx < size) {
            Node currentNode = first;
            for (int i = 0; i < idx; ++i) {
                currentNode = currentNode.next;
            }
            int returnValue = currentNode.val;
            for (int i = idx; i < size; ++i) {
                // TODO: перезаписать ссылки
            }
            --size;
            return returnValue;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        return 0;
    }

}
