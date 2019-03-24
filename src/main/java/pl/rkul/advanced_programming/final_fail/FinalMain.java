package pl.rkul.advanced_programming.final_fail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FinalMain {

    public static void main(String[] args) {

        final Object lock = new Object();       // musimy zrobic final bo jak nie to cos nam moze zmienic referencje lock i stworzyc now obiekt i nam popsuje to dzialanie kolejki w watkach

        FinalFail job = new FinalFail(lock);
        FinalFail job2 = new FinalFail(lock);

        ExecutorService workers = Executors.newFixedThreadPool(4);

        workers.execute(job);
        workers.execute(job2);
        workers.execute(job);
        workers.execute(job2);
        //lock = new Object()   // teraz sie nie da bo jest final, bo tak to juz by nam to popsula watki i ich kolejnosci
        workers.execute(job);
        workers.execute(job2);


        workers.shutdown();
    }
}
