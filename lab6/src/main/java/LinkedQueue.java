import java.util.LinkedList;

public class LinkedQueue<E> implements Queue<E> {
    private final LinkedList<E> queue;
    private final int capacity;

    public LinkedQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    @Override
    public void enqueue(E item) {
        if (queue.size() >= capacity) {
            throw new IllegalStateException("Queue is full");
        }
        queue.addLast(item);
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }
}
