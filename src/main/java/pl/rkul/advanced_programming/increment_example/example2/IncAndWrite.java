package pl.rkul.advanced_programming.increment_example.example2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IncAndWrite {

    // 1 wątek inkrementuje 10000 razy
    // 2 wątek wypisuje tą zmienną

    public static void main(String[] args) {

        Holder four = new Holder(4);
      /*  ExecutorService workers = Executors.newFixedThreadPool(2);

        workers.execute(new Increment(four));
        workers.execute(new Write(four));

        workers.shutdown();*/

        Thread t1 = new Thread(new Write(four),"writer");
        Thread t2 = new Thread(new Increment(four),"incrementer");

        t1.start();
        t2.start();

        try {
            t1.join();  // tu trzeba zauwazy c ze join() jest wykonywana na obecnym watku czyli na MAINie, wiec Main czeka az t1 sie wykona
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
