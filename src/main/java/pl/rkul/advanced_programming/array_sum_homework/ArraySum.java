package pl.rkul.advanced_programming.array_sum_homework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class ArraySum {

    static long sumElements(int[] array){

        // odpali 2 watki
        // kazdy z watkow zwroci sume czesciowa
        // sumElements() poczeka na sumy czesciowe
        // doda je i zwroci

        AtomicLong result = new AtomicLong();   // bezpieczny wielowatkowy pojemnik na jedna wartosc, dlatego na koniec normalnie wyciagamy wartosc result.get()
        ExecutorService workers = Executors.newFixedThreadPool(2);
        Runnable job1 = new Sum(array,0,array.length/2,result);
        Runnable job2 = new Sum(array,array.length/2,array.length,result);
        workers.execute(job1);
        workers.execute(job2);
        workers.shutdown();

        while(!workers.isTerminated()){}    // czekamy mainem az sie skonczy liczenie i dopiero potem przekazujemy rezultat

        return result.get();
    }

    public static void main(String[] args) {
        int[] intArray = {1,2,3,4,5,6};
        long result = sumElements(intArray);
        System.out.println("Result: " + result);
    }
}
