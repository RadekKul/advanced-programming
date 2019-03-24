package pl.rkul.advanced_programming.notify;

public class Hop implements Runnable {

    Object o = new Object();

    public Hop(Object o) {
        this.o = o;
    }

    @Override
    public void run() { // w hip wszystko jest opisane

        while(true) {
            synchronized (o) {
                o.notify();
                System.out.println("Hop");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
