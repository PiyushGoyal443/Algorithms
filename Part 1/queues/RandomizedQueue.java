import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] data;
    private int size;
    private int capacity;
    public RandomizedQueue() {
       size = 0;
       capacity = 1;
       data = (Item[]) new Object[capacity];
   }
   public boolean isEmpty() {
       return size == 0;
   }
   public int size() {
       return size;
   }
   public void enqueue(Item item) {
       if (item == null) throw new java.lang.NullPointerException();
       if (size + 1 > capacity) {
           resizePlus();
       }
       data[size++] = item;
   }
   private void resizePlus() {
       capacity *= 2;
       Item[] newQueue = (Item[]) new Object[capacity];
       int index = 0;
       for (Item i : data) {
           newQueue[index++] = i;
       }
       data = newQueue;
   }
   private void resizeMinus() {
       capacity /= 2;
       Item[] newQueue = (Item[]) new Object[capacity];
       int index = 0;
       for (int i = 0; i < capacity; i++) {
           newQueue[index++] = data[i];
       }
       data = newQueue;
   }
   public Item dequeue() {
       if (isEmpty()) throw new java.util.NoSuchElementException();
       int i = StdRandom.uniform(size);
       Item ret = data[i];
       data[i] = data[--size];
       data[size] = null;
       if (capacity / 4 > size) {
           resizeMinus();
       }
       return ret;
   }
   public Item sample() {
       if (isEmpty()) throw new java.util.NoSuchElementException();
       return data[StdRandom.uniform(size)];
   }
   public Iterator<Item> iterator() {
       return new ListIterator();
   }
   private class ListIterator implements Iterator<Item> {
       private int current = 0;
       private int[] shuffledIndexes = new int[size];
       
       public boolean hasNext() {
           if (current == 0) {
               for (int i = 0; i < size; i++)
                   shuffledIndexes[i] = i;
               StdRandom.shuffle(shuffledIndexes);
           }
           return current < size;
       }
       public Item next() {
           if (current == 0) {
               for (int i = 0; i < size; i++)
                   shuffledIndexes[i] = i;
               StdRandom.shuffle(shuffledIndexes);
           }
           if (current >= size || size() == 0) throw new java.util.NoSuchElementException();
           return data[shuffledIndexes[current++]];
       }
       public void remove() {
           throw new java.lang.UnsupportedOperationException();
       }
   }
}
