package pl.rkul.advanced_programming.syncWithLock;

import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer implements Runnable {

    private Queue<Pizza> pizzaList;
    private ReentrantLock lock;

    public Consumer(Queue<Pizza> pizzaList, ReentrantLock lock) {
        this.pizzaList = pizzaList;
        this.lock = lock;
    }

    public synchronized void showPizzas() {  // metoda sychnronizowana, po to zeby dwa watki na raz nie moglby wywolac, tylko jeden watek moze wykonac taka metode, reszta czeka i potem znow losowo ktos dostaje mozliwosc
        pizzaList.forEach(pizza -> System.out.println(pizza));
    }

    public void showPizza2() {       // ta metoda robi to samo co ta wyzej, tylko wybieramy w niej, co mamy synchornizowac!!! przez wpisanie this, tylko jeden watek moze wykonac ta metode
        synchronized (this) {       // tutaj wybieramy synchornizowanie na this, a mozemy dodac Object o, i na nim synchronizowac, czesto sie tak robi, to jest po to zeby tylko wpuszczac jeden watek do uzywania tego
            pizzaList.forEach(pizza -> System.out.println(pizza));
        }
    }

    @Override
    public void run() { // konsumer je po 1 sec

        Pizza pizzaToEat;
        while (true) {
            lock.lock();
            pizzaList.forEach(System.out::println); // iteruje po liscie pizz, pokazuje ile jest gotowych
            if (!pizzaList.isEmpty()) {

                pizzaToEat = pizzaList.poll(); // zabiera zawsze z poczatku kolejki pierwszy obiekt, w tym przypadku przypisujemy go do referencji pizzaToEat zeby nizej to wypisac
                System.out.println("Consuming nice Pizza with: " + pizzaToEat.getIngredients());
            }
            lock.unlock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
