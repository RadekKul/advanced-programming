package pl.rkul.advanced_programming.dead_lock;

import java.util.Queue;
import java.util.Random;

public class Producent implements Runnable {

    private final Queue<String> first;
    private final Queue<String> second;
    private Random random;

    public Producent(Queue<String> first, Queue<String> second, Random random) {
        this.first = first;
        this.second = second;
        this.random = random;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (first) {
                synchronized (second) {
                    first.offer("" + random.nextInt());
                    second.offer("" + random.nextInt());
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
