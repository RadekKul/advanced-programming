package pl.rkul.advanced_programming.array_sum_homework;

import java.util.concurrent.atomic.AtomicLong;

public class Sum implements Runnable {

    private final int[] array;
    private final int startIndexInclusive;
    private final int endIndexExclusive;
    private final AtomicLong sumToStore;    // pojemnik na sume, na pojedyncza wartosc

    public Sum(int[] array, int startIndexInclusive, int endIndexExclusive, AtomicLong sumToStore) { // musimy dac tu tablice jedno elementowa do sumy, zeby nie miec kopii tylko obiekt ktory mozna zmienaic
        this.array = array;
        this.startIndexInclusive = startIndexInclusive;
        this.endIndexExclusive = endIndexExclusive;
        this.sumToStore = sumToStore;
    }

    @Override
    public void run() {

        long sum = 0;

        for(int i = startIndexInclusive; i<endIndexExclusive;i++){
            sum += array[i];
        }

        long startResult = sumToStore.get();    // pobieramy to co jest na poczatku

        //musimy to sprawdzic zeby wiedziec czy inny watek w tym czasie nam nic nie wpisal
        while(!sumToStore.compareAndSet(startResult, startResult + sum )){  // chce ustawic to co bylo wczesniej + sum, czyli metoda zamieniajaca nam
            startResult = sumToStore.get(); // jesli sie nie uda tego zrobic to ustawiamy startResult na obecna wartosc sumToStore
        }



    }
}
