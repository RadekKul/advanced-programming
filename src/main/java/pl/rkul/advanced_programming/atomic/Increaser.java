package pl.rkul.advanced_programming.atomic;

import java.util.concurrent.atomic.AtomicLong;

public class Increaser implements Runnable {


    AtomicLong atomicLong;

    public Increaser(AtomicLong atomicLong) {
        this.atomicLong = atomicLong;
    }

    @Override
    public void run() {

        // 1 sposob lepszy
        // musimy przypisac wartosc jaka jest na poczatku zebysmy wiedzieli czego oczekujmy, poniewaz compareAndSet oczekauje longa a nie atomicLonga
        // jest to bezpieczenstwo, poniewaz sprawdzamy czy w momencie kiedy pobralismy poczatkowa wartosc nikt juz jej nie zwiekszyl, jezeli zwiekszyl to sie nie wykona
        long expected;

        do{
            expected = atomicLong.get();    // przypisujemy do expected atomicLong.get tylko wtedy kiedy porownywana wartosc expected jest taka jaka mamy obecnie w atomicLongu
        } while (!atomicLong.compareAndSet(expected, expected + 2));

            // Nie robimy tak bo to jest brak bezpieczenstwa przy wielowatkowosci, podczas gdy my pobieramy atomica, ktos moze go zmienic
            // atomicLong.compareAndSet(atomicLong.get(), expected + 2);

            System.out.println("Value of atomicLong = " + atomicLong.get());


        // 2 sposob
        //atomicLong.incrementAndGet();    // to jest to samo co ++atomicLong, zinkrementuj i pobierz
        //System.out.println("Value of atomicLong = " + atomicLong);
    }
}
