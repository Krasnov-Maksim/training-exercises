package com.bobocode.cs.v1;

import com.bobocode.cs.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link ArrayList} is an implementation of {@link List} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @author Serhii Hryhus
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 5;
    private int size;
    private Object[] elementData;

    /**
     * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayList(int initCapacity) {
        if (initCapacity < 1) {
            throw new IllegalArgumentException("Illegal Capacity: " + initCapacity);
        }
        this.elementData = new Object[initCapacity];
    }

    /**
     * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates and returns an instance of {@link ArrayList} with provided elements
     *
     * @param elements to add
     * @return new instance
     */
    public static <T> List<T> of(T... elements) {
        List<Object> list = new ArrayList<>(elements.length);
        //List<T> list = (List<T>) Arrays.asList(elements);
        for (T element : elements) {
            list.add(element);
        }
        return (List<T>) list;
    }

    /**
     * Adds an element to the array.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        growIfArrayFull();
        elementData[size] = element;
        size++;
    }

    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size + 1);
        growIfArrayFull();
        if (index == size) {// array already full, index is equal to size
            System.arraycopy(elementData, index, elementData, index + 1, size - index + 1);
        } else {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
        }
        elementData[index] = element;
        size++;
    }

    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elementData[index];
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("No such element!");
        }
        Objects.checkIndex(0, size);
        return (T) elementData[0];
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException("No such element!");
        }
        Objects.checkIndex(size - 1, size);
        return (T) elementData[size - 1];
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
    @Override
    public void set(int index, T element) {
        Objects.checkIndex(index, size);
        elementData[index] = element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T deletedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return deletedElement;
    }

    /**
     * Checks for existing of a specific element in the list.
     *
     * @param element is element
     * @return If element exists method returns true, otherwise it returns false
     */
    @Override
    public boolean contains(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return amount of saved elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        for (Object obj : elementData) {
            obj = null;
        }
        size = 0;
    }

    private void growIfArrayFull() {
        if (elementData.length == size) {
            grow();
        }
    }

    private void grow() {
        int newCapacity = elementData.length + elementData.length / 2;
        increaseElementDataCapacity(newCapacity);
    }

    private void increaseElementDataCapacity(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, elementData.length);
        elementData = newArray;
    }
}
