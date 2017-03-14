package track.lessons.lesson3;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * <p>
 * Должен иметь 2 конструктора
 * - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
 * - с аргументом - начальный размер массива
 */
public class MyArrayList extends List {
    private static int DEFAULT_CAPACITY = 100;
    private int[] values;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        values = new int[capacity];
    }

    @Override
    void add(int item) {
        if (size + 1 < values.length) {
            values[size] = item;
            ++size;
        } else {
            resize(values.length * 2);
            values[size] = item;
            ++size;
        }
    }

    private void resize(int newCapacity) {
        int[] newArray = new int[newCapacity];
        System.arraycopy(values, 0, newArray, 0, size);
        values = newArray;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        if (idx < size) {
            int returnValue = values[idx];
            if (size > idx + 1) {
                System.arraycopy(values, idx + 1, values, idx, size - idx - 1);
            }
            --size;
            return returnValue;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        if (idx < size) {
            return values[idx];
        } else {
            throw new NoSuchElementException();
        }
    }

}
