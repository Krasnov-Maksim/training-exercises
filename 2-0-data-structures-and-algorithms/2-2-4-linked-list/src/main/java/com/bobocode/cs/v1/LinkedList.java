package com.bobocode.cs.v1;


import com.bobocode.cs.List;
import java.util.NoSuchElementException;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {

    private int size = 0;
    private Node<T> first;
    private Node<T> last;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<Object> list = new LinkedList<>();
        for (T element : elements) {
            list.add(element);
        }
        return (LinkedList<T>) list;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        if (size == 0) {
            linkFirst(element);
        } else {
            linkLast(element);
        }
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        checkPositionIndex(index);
        if (index == 0) {
            linkFirst(element);
        } else if (index == size) {
            linkLast(element);
        } else {
            linkAtIndex(index, element);
        }
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        checkElementIndex(index);
        Node<T> node = getNode(index);
        node.item = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        checkElementIndex(index);
        return getNode(index).item;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        final Node<T> node = first;
        if (node == null)
            throw new NoSuchElementException();
        return node.item;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        final Node<T> node = last;
        if (node == null)
            throw new NoSuchElementException();
        return node.item;
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
        checkElementIndex(index);
        return unlink(index);
    }

    /**
     * Checks if a specific exists in the list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
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
     * Returns the number of elements in the list
     *
     * @return number of elements
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
        for (Node<T> node = first; node != null; ) {
            Node<T> next = node.next;
            node.item = null;
            node.next = null;
            node = next;
        }
        first = null;
        last = null;
        size = 0;
    }

    private int indexOf(T element) {
        int index = 0;
        if (element == null) {
            for (Node<T> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<T> x = first; x != null; x = x.next) {
                if (element.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    private void linkFirst(T element) {
        Node<T> oldFirst = first;
        first = new Node<>(element, oldFirst);
        if (size == 0) {
            last = first;
        }
        size++;
    }

    private void linkLast(T element) {
        Node<T> oldLast = last;
        last = new Node<>(element, null);
        oldLast.next = last;
        size++;
    }

    private void linkAtIndex(int index, T element) {
        Node<T> afterThis = getNode(index - 1);
        Node<T> beforeThis = getNode(index);
        Node<T> nodeToInsert = new Node<>(element, beforeThis);
        afterThis.next = nodeToInsert;
        nodeToInsert.next = beforeThis;
        size++;
    }

    private T unlink(int index) {
        Node<T> nodeToUnlink = getNode(index);
        if (index == 0) {
            Node<T> nodeAfter = getNode(index + 1);
            first = nodeAfter;
        } else if (index == size - 1) {
            Node<T> nodeBefore = getNode(index - 1);
            nodeBefore.next = null;
            last = nodeBefore;
        } else {
            Node<T> nodeBefore = getNode(index - 1);
            Node<T> nodeAfter = getNode(index + 1);
            nodeBefore.next = nodeAfter;
        }
        nodeToUnlink.next = null;
        size--;
        return nodeToUnlink.item;
    }

    private Node<T> getNode(int index) {
        checkElementIndex(index);
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        public Node() {
        }

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }
}
