package pl.rkul.advanced_programming.syncWithLock;

import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class Producent implements Runnable {

    private Queue<Pizza> pizzaList; // tworzymy kolejke pizz
    private ReentrantLock lock;

    public Producent(Queue<Pizza> pizzaList, ReentrantLock lock) {
        this.pizzaList = pizzaList;
        this.lock = lock;
    }

    @Override
    public void run() { // zakladamy ze co 1s producent dorzuca jedna pizze

        //1s
        while (true) {

            Pizza pizzaToAdd = new Pizza("Hawajska", "Ser, Szynka, Ananas");
            System.out.println("Preparing new delicious Pizza " + pizzaToAdd);

            lock.lock();
            pizzaList.offer(pizzaToAdd);   // offer doklada zawsze na koniec kolejki, w tym przypadku bedzie dokladac pizzaToAdd
            lock.unlock();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
