package pl.rkul.advanced_programming.notify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotifyExample {

    public static void main(String[] args) {
        Object o  = new Object();

       /* synchronized (o) {  // musimy sie zsychronizowac z objectem, na ktorym wywolujemy wait, zeby  w ogole miec na czym waitowac
            try {
                o.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        Hip hip = new Hip(o);
        Hop hop = new Hop(o);

        ExecutorService workers = Executors.newFixedThreadPool(2);

        workers.execute(hop);
        workers.execute(hip);

    }
}
