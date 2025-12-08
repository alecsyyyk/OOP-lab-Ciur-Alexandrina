import java.util.ArrayList;
import java.util.List;

public class ArrayQueue<E> implements Queue<E> {
    private final List<E> queue;
    private final int capacity;

    public ArrayQueue(int capacity) {
        this.queue = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    @Override
    public void enqueue(E item) {
        if (queue.size() >= capacity) {
            throw new IllegalStateException("Queue is full");
        }
        queue.add(item);
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.remove(0);
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
