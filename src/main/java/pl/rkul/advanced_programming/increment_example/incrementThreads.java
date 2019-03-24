package pl.rkul.advanced_programming.increment_example;

public class incrementThreads implements Runnable {

    private static long a=5;    // jak bysmy zrobili long zamiast int to jest mozliwosc ze zle nam wypisze, ze na raz dwa watki zinkrementują 'a', bo watki super sie przeplataja na 32 bitach a na wiecej juz niekoniecznie

    Object o;

    public static long getA() {
        return a;
    }

    public incrementThreads(Object o) {
        this.o = o;
    }

    @Override
    public void run() {
        for(int i = 0 ; i<1000 ; i++) {
            // warto jest stworzyc sobie zmienna w ktora bedziemy wpisywac zeby watki sie nie przeplataly przypadkiem (bez synchro). Mozna tez to wpisac w soucie, wtedy bedzie najlepiej
            //int k = ++a;    // musi byc ++a, bo a++ zwieszky nam a, ale przypisze do k niezwiekszone, a my chcemy odrazu zacząc zwiekszac to a i juz zwiekszone przypisywac,
                System.out.println("Worker name: " + Thread.currentThread().getName() + "   Value of a = " + ++a);
             /*   try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
        }

    }

}
