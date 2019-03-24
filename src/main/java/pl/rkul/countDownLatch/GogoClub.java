package pl.rkul.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GogoClub {

    // Zadanie tzw Bramkarz, jest w stanie utrzymać 5 wątków, przy 6, wszystkie wątki już wchodzą. Pozniej 7,8,9,10 tez wchodza bez czekania
    // 10 watkow
    // kazdy ma referencje do CountDownLatcha
    // kazdy "uderz" (countDown()) raz i próbuje iśc dlaej ale nie może (await())
    // po piątym gongu wszyscy przechodzą dalej

    public static void main(String[] args) {

        CountDownLatch badGuy = new CountDownLatch(5);  // wskazujemy ile watkow bedzie zatrzymanych

        Runnable job = () -> {
            System.out.println(Thread.currentThread().getName() + " is here "); // sprawdzamy jakie watki przyszly
            badGuy.countDown(); // kazdy watek odlicza od tego wskazanego w CountDownLatch czyli od 5, jak dojdzie do 0 do ochorniaz przestaje dzialac
            try {
                badGuy.await(); // await to metoda ktora przy countDownLatch wiekszym od 0, karze watkowi czekac, jezeli zejdzie do 0 lub nizej, to puszcza wszystkie watki
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Wątek " + Thread.currentThread().getName() + " zakończył działanie");   // sprawdzamy sobie co przeszlo w jakim momencie przez bramkarza
            // i widzimy ze po 5 watku juz wszystko leci odrazu, nic nie czeka
        };

        ExecutorService customers = Executors.newFixedThreadPool(10);

        for (int i=0; i<10 ; i++){
            customers.execute(job);
        }

        customers.shutdown();

    }
}
