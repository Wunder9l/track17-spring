package track.lessons.lesson3;

import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class MyLinkedListTest {

    @Test(expected = NoSuchElementException.class)
    public void emptyList() throws Exception {
        List list = new MyLinkedList();
        Assert.assertTrue(list.size() == 0);
        list.get(0);
    }

    @Test
    public void listAdd() throws Exception {
        List list = new MyLinkedList();
        list.add(1);

        Assert.assertTrue(list.size() == 1);
    }

    @Test
    public void listAddRemove() throws Exception {
        List list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);

        Assert.assertEquals(3, list.size());

        Assert.assertEquals(1, list.get(0));
        Assert.assertEquals(2, list.get(1));
        Assert.assertEquals(3, list.get(2));

        list.remove(1);
        Assert.assertEquals(3, list.get(1));
        Assert.assertEquals(1, list.get(0));

        list.remove(1);
        list.remove(0);

        Assert.assertTrue(list.size() == 0);
    }

    @Test
    public void listRemove() throws Exception {
        List list = new MyLinkedList();
        list.add(1);
        list.remove(0);

        Assert.assertTrue(list.size() == 0);
    }

    @Test
    public void addRemoveValueCheck() throws Exception {
        List list = new MyLinkedList();
        for (int i = 1; i < 1000; ++i) {
            if (i % 3 == 0) {
                list.remove(list.size() - 1);
            } else {
                list.add(i);
            }
        }

        Random random = new Random();
        int index = random.nextInt(333);
        Assert.assertTrue(list.get(index) == 1 + 3 * index);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeOutBound() throws Exception {
        List list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        Assert.assertTrue(list.size() == 3);
        list.remove(3);
    }

    @Test
    public void listPushPop() throws Exception {
        MyLinkedList list = new MyLinkedList();
        for (int i = 0; i < 10000; ++i) {
            list.push(i);
        }
        int poppedValue;
        for (int i = 9999; i >= 0; --i) {
            poppedValue = list.pop();
            if (i != poppedValue) {
                Assert.assertTrue(poppedValue == i);
            }
        }
    }

    @Test
    public void listEnqueueDequeue() throws Exception {
        MyLinkedList list = new MyLinkedList();
        for (int i = 0; i < 10000; ++i) {
            list.enqueue(i);
        }
        int dequeueValue;
        for (int i = 0; i < 10000; ++i) {
            dequeueValue = list.dequeue();
            if (i != dequeueValue) {
                Assert.assertTrue(dequeueValue == i);
            }
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void listPopException() throws Exception {
        MyLinkedList list = new MyLinkedList();
        list.push(1);
        list.push(2);
        list.pop();
        Assert.assertTrue(1 == list.pop());
        list.pop();
    }

    @Test(expected = NoSuchElementException.class)
    public void listDequeueException() throws Exception {
        MyLinkedList list = new MyLinkedList();
        list.enqueue(1);
        list.enqueue(2);
        list.dequeue();
        Assert.assertTrue(2 == list.dequeue());
        list.dequeue();
    }
}