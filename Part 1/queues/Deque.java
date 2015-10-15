import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node front;
    private Node last;
    
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque() {
        front = null;
        last = null;
    }
    public boolean isEmpty() {
        if (front == null)
            return true;
        return false;
    }
    public int size() {
        if (isEmpty()) return 0;
        int count = 1;
        Node n = front;
        while (n != last) {
            count++;
            n = n.next;
        }
        return count;
    }
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        Node n = new Node();
        n.item = item;
        if (front != null) {
            n.next = front;
            front.prev = n;
            n.prev = null;
        front = n;
        } else {
            front = n;
            last = n;
            n.prev = null;
            n.next = null;
        }
    }    
    public void addLast(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        Node n = new Node();
        n.item = item;
        if (last != null) {
            n.prev = last;
            last.next = n;
            n.next = null;
            last = n;
        } else {
            front = n;
            last = n;
            n.prev = null;
            n.next = null;
        }
    }
    public Item removeFirst() {
        if (front == null) throw new java.util.NoSuchElementException();
        Item n = front.item;
        front = front.next;
        if (front != null) {
            front.prev = null;
        } else {
            last = null;
        }
        return n;
    }
    public Item removeLast() {  
        if (last == null) throw new java.util.NoSuchElementException();
        Item n = last.item;
        last = last.prev;
        if (last != null) {
            last.next = null;
        } else {
            front = null;
        }
        return n;
    }
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item> {
        private Node current = front;
        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            if (current == null) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
    public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
