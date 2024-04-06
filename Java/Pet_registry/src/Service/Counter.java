package Service;

public class Counter {
    private Integer count;

    public Counter() {
        count = 0;
    }

    public Integer getCount() {
        return count;
    }

    public void increment() {
        count++;
    }
}
