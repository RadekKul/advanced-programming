package pl.rkul.advanced_programming.final_fail;

public class FinalFail implements Runnable {

    // stworzymy klase ktora bedzie synchrozniowac dzialanie watkow na obkiecie lock.

    private final Object lock;    // tworzymy instancje Object, na ktorej bedziemy synchronizowac

    public FinalFail(Object lock){
        this.lock=lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            for (int i = 0; i<10; i++){
                System.out.println("Run by: " + Thread.currentThread().getName());      // wypiszemy imie tego pracownika ktory wywolal ta metode
            }
        }

    }
}
