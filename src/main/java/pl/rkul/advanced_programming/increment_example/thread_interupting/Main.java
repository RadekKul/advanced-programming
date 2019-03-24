package pl.rkul.advanced_programming.increment_example.thread_interupting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    // 1 wątek cały czas pisze - pętrla nieskończona
    // 2 wątek śpi 5 sekund , pózniej przerywa dzialanie pierwszego

    public static void main(String[] args) throws InterruptedException {

        Holder holder = new Holder();

        StopThread stopper = new StopThread(holder);
        GogoThread writer = new GogoThread(holder);

        ExecutorService workers = Executors.newFixedThreadPool(2);

        workers.execute(stopper);
        workers.execute(writer);

        workers.shutdown();

        while (!workers.isTerminated()){
            Thread.sleep(1000);
        }

        System.out.println("Zakończono sprawdzanie");


    }

}
