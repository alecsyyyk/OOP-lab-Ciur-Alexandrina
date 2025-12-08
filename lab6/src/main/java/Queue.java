public interface Queue<E> {
    void enqueue(E item);
    E dequeue();
    boolean isEmpty();
    int size();
}
