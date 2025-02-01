package edu.odu.cs.cs361;

import java.util.Iterator;

/**
 * A class for counting the number of distinct items added.
 */
public class CountedThings<T> implements Iterable<T> {

    /**
     * We'll use an array of these to track the items seen and how many times
     * we have seen each one.
     */
    private static class Item {
        public Object data;
        public int count;

        public Item (Object d) {
            data = d;
            count = 1;
        }
    }

    private Item[] items;
    private int theSize;

    /**
     * Create an empty collection.
     */
    public CountedThings() {
        items = new Item[60000];
        theSize = 0;
    }

    /**
     * The number of distinct items.
     * 
     * @return how many distinct items have been added?
     */
    public int size() {
        return theSize;
    }

    /**
     * Add an item to the collection.
     * 
     * @param item new item
     */
    public void add(T item) {
        boolean found = false;
        for (int i = 0; (!found) && i < theSize; ++i)
            if (item.equals(items[i].data)) {
                ++ items[i].count;
                found = true;
            }
        if (!found) {
            items[theSize] = new Item(item);
            ++theSize;
        }
    }

    /**
     * How many times has this item been added to the collection?
     * @param item an item to search for
     * @return the number of times that item has been added.
     */
    public int countOf(T item) {
        for (int i = 0; i < theSize; ++i)
            if (item.equals(items[i].data))
                return items[i].count;
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new CountedIterator();
    }
    private class CountedIterator implements Iterator<T> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < theSize;
        }

        @Override
        public T next() {
           @SuppressWarnings("unchecked")
        T value = (T)items[current].data;
            ++current;
            return value;
        }
    }



}
