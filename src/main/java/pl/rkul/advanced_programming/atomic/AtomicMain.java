package pl.rkul.advanced_programming.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicMain {

    // zmienna long
    // 5 wątków każdy zwiększa zmienną o 1
    // main czeka na wykonanie pracy i wypisuje finalną wartość zmiennej long

    public static void main(String[] args) {

        AtomicLong a = new AtomicLong(5);

        Increaser increaser = new Increaser(a);

        ExecutorService workers = Executors.newFixedThreadPool(5);  // towrzymy 5-ciu robotnikow
        for (int i = 0;i<10 ; i++){
            workers.execute(increaser); // nadajemy im prace increaser
        }

        workers.shutdown();

        // 1 metoda na blokowanie maina

        while(!workers.isTerminated()){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 2 metoda na blokowanie maina , jezeli awaitTemrination sie skonczy po minucie i nadal beda pracowac to zwroci
        // false i if nie breakuje petli, jezeli skoncza to wchodzi do if true i breakuje i puszcza maina dalej

        boolean shouldStop = false;
        while(true){
            try {
                shouldStop = workers.awaitTermination(1, TimeUnit.SECONDS);
                if(shouldStop){
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Zakończono. Value a = " +a.get());


/* // to jest takie bardziej prymitywne rozwiazanie ale raczej uzywa sie Executora do takich zadan, tak jak wyzej
        for(int i =0; i<5;i++){
            Thread worker = new Thread(increaser);
            worker.start();
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
*/
    }
}
