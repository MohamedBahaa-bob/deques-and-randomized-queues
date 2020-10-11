import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size = 0;
    private Node first, last;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = new Node();
        last = first;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("invalid entry");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty())
            last = first;
        else {
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("invalid entry");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else {
            last.prev = oldlast;
            oldlast.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item value = first.item;
        first = first.next;
        size--;
        if (isEmpty())
            last = first;
        else
            first.prev = null;
        return value;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item value = last.item;
        size--;
        last = last.prev;
        if (isEmpty())
            first = last;
        else
            last.next = null;
        return value;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private Node iterator = first;

        public boolean hasNext() {
            return iterator != null;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item value = iterator.item;
            iterator = iterator.next;
            return value;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deq = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            int input = StdIn.readInt();
            deq.addFirst(input);
            deq.addLast(input);
            if (i % 2 == 0)
                deq.removeLast();
            else
                deq.removeFirst();
        }
        Iterator<Integer> i = deq.iterator();
        while (i.hasNext()) {
            Integer n = i.next();
            StdOut.println(n);
        }
    }
}
