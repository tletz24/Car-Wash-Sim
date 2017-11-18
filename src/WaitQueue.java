import java.util.LinkedList;

public class WaitQueue<Car> extends LinkedList<Car> {

    private LinkedList<Car> list = new LinkedList<Car>();

    public void enqueue(Car item) {
        list.addLast(item);
    }

    public Car dequeue() {
        return list.poll();
    }

    public boolean hasItems() {
        return !list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void addItems(WaitQueue<? extends Car> q) {
        while (q.hasItems()) list.addLast(q.dequeue());
    }

    public Car top() {
        return list.peek();
    }

}
