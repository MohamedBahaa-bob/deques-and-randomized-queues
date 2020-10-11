import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int last = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return last == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return last;
    }

    private void resize(int capacity) {
        Item[] copy;
        copy = (Item[]) new Object[capacity];
        for (int i = 0; i < last; i++)
            copy[i] = queue[i];
        queue = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("invalid entry");
        if (last == queue.length)
            resize(queue.length * 2);
        queue[last++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        int i = StdRandom.uniform(last);
        Item remove = queue[i];
        queue[i] = queue[--last];
        return remove;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int i = StdRandom.uniform(last);
        return queue[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private boolean[] chosen = new boolean[queue.length + 1];

        public boolean hasNext() {
            for (int i = 0; i < last; i++)
                if (!chosen[i])
                    return true;
            return false;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            int i = StdRandom.uniform(last);
            while (chosen[i])
                i = StdRandom.uniform(last);
            chosen[i] = true;
            return queue[i];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> deq = new RandomizedQueue<Integer>();
        deq.enqueue(1);
        deq.enqueue(2);
        deq.enqueue(3);
        deq.enqueue(4);
        deq.enqueue(5);
        deq.enqueue(6);
        deq.enqueue(7);
        deq.enqueue(9);
        deq.enqueue(10);
        deq.enqueue(11);
        Iterator<Integer> i = deq.iterator();
        while (i.hasNext()) {
            int s = i.next();
            StdOut.println(s);
        }
        int s = deq.dequeue();
        StdOut.println(s);
    }
}
