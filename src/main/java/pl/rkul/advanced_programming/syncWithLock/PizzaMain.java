package pl.rkul.advanced_programming.syncWithLock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class PizzaMain {

    public static void main(String[] args) {

        Queue<Pizza> pizzaList = new LinkedList<>();

        final ReentrantLock lock = new ReentrantLock();

        Producent pizzaMan1 = new Producent(pizzaList, lock);
        Producent pizzaMan2 = new Producent(pizzaList, lock);
        Consumer consumer1 = new Consumer(pizzaList, lock);
        Consumer consumer2 = new Consumer(pizzaList, lock);
        Consumer consumer3 = new Consumer(pizzaList, lock);
        Consumer consumer4 = new Consumer(pizzaList, lock);


        ExecutorService workers = Executors.newFixedThreadPool(6);

        workers.execute(pizzaMan1);
        workers.execute(pizzaMan2);
        workers.execute(consumer1);
        workers.execute(consumer2);
        workers.execute(consumer3);
        workers.execute(consumer4);

        workers.shutdown();
    }
}
