import java.util.LinkedList;

//TODO make it so that when the queue is constructed there can only be 8 (or 7) slots.

class WaitQueue<E> {

    private LinkedList<E> list = new LinkedList<E>();

    public void enqueue(E item) {
        list.addLast(item);
    }

    public E dequeue() {
        return list.poll();
    }

    public boolean hasItems() {
        return !list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void addItems(WaitQueue<? extends E> q) {
        while (q.hasItems()) list.addLast(q.dequeue());
    }

    public E top() {
        return list.peek();
    }

    public void updateWaitTimes() {
        if(hasItems()) {
            //TODO make the queue iterable so we can go through and increment the wait times by one
        }
    }
}
