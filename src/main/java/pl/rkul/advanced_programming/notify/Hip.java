package pl.rkul.advanced_programming.notify;

public class Hip implements Runnable {

    Object o  = new Object();   // jest to Object na ktorym bedziemy dzialac wszyscy, w mainie bedzie stworzony i bedzie Objectem z MONITOREM, na ktory bedziemy sie synchronizowac

    public Hip(Object o) {
        this.o = o;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (o) {  // musimy sie w obydwu przypadka zsynchronizowac na tym samym obiekcie (przypisanym w mainie), wtedy notfiy() budzi wszystkie watki ktore moga walczyc
                o.notify(); // tutaj moznaby dac notifyAll, zeby budzilo wszystkie watki ktore oczekuja na obiekcie o
                System.out.println("Hip");
                try {       // gdyby nie bylo waita, to moglby wskoczyc znow ten sam watek, bo nie poszedlby spac tylko java by sama decydowala komu dawac moniotr
                    o.wait();       // watek idzie oczekiwac i oddaje monitor do drugiego, tamten budzi sie na monitorze, wstaje, wypisuje i znow idzie wait i oddaje monitor do tego
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    // to bedzie ladnie dzialac przy dwoch watkach, przy trzech juz nie bylo by tak ladnie, bo jeden by spal a walka o wysiwetlenie bylaby pomiedzy dwoma innymi i one juz randomowo by wygrywaly
            }
        }
    }
}
