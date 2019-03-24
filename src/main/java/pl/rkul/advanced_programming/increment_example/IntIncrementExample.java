package pl.rkul.advanced_programming.increment_example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IntIncrementExample {

    //TODO
    // 2 wątki  - Thread
    // kazdy incrementuje liczbe 1000 razy
    // wątek main wypisuje ją

    public static void main(String[] args) {

        Object o = new Object();

        incrementThreads Thread = new incrementThreads(o);  // tworzymy instancje klasy watkowej

        ExecutorService workers = Executors.newFixedThreadPool(2);  // tutaj mowimy ze beda dwa watki w workersach

        workers.execute(Thread);    // uruchamiamy te dwa watki, jak dodamy kolejny execute, to po prostu watek 1 i 2 zrobia to same, bez tworzenia watku 3, bo samo sie nie moze stworzyc
        workers.execute(Thread);

        workers.shutdown();

        // isTerminated i awayTerminated to sa metody ktore mozna uzyc dla executora zeby wybrac co pokolei ma zrobic. (ktory watek po sobie)
        // wywolujemy je koniecznie po shutdown, to jest warunek, bo inaczej to nie ma sensu

        // to jest bloker jezeli wszystkie watki skonczyly swoja prace to wtedy pusci maina dalej i to co napiszemy po tym, zostanie wykonane po tych watkach
        // sprawdzamy czy ma jeszcze jakas robote, jak nie jest wolny to wchodzimy caly czas do srodka i sie krecimy, dopiero jak sie stanie wolny to idziemy dalej
        while(!workers.isTerminated()) {
            System.out.println("workers are working....");
            try {
                java.lang.Thread.sleep(100);    // usypiamy zeby komputer nie byl zbyt oobciazony, wiec sprawdzamy to co 0,1s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 2 opcja na przeczekanie mainem az skonczy sie reszta watkow
        boolean interrupted = false;
        while(true) {
            // pierwszy parametr to timeout czyli Maxymalny czas jaki dajemy watką na robote - metoda jest mniej pewna, bo trzeba dobrze oszacowac czas, drugi parametr to jaki to typ czas np godzina
            try {
                interrupted = workers.awaitTermination(5, TimeUnit.SECONDS);    // czyli tu bedzie czekac 5 sekund i jezeli do tej pory workersi nie skoncza, to sie wybudzi
                if (interrupted) {  // tutaj sprawdzamy, minal timeout i watki sie sprawdzily to wchodzi tu i przerywa cala petle, jezeli minal timeout a sie nie skonczyly to leci drugi raz while
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Ostateczna wartość a = " + incrementThreads.getA());

    }
}
