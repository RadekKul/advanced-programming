package pl.rkul.advanced_programming.priority;

public class Main{

    // 2 Runnable - kazdy wypisuje cos innego - lambda
    // po kazdym wypisaniu czeka 500ms
    // 2 Thread - skrajny priorytet 1 i 10
    // zaobserwujmy wyjscie
    // ...

    public static void main(String[] args) {
        Runnable firstJob = () -> {
            while (true){
                System.out.println("First Runnable job");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable secondJob = () -> {
            while (true){
                System.out.println("Second Runnable job");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread worker1 = new Thread(firstJob,"First Thread");
        worker1.setPriority(Thread.MIN_PRIORITY);
        Thread worker2 = new Thread(firstJob,"Second Thread");
        worker1.setPriority(Thread.MAX_PRIORITY);

        worker1.start();
        worker2.start();

        // zeby zobaczyc lepsza roznice pomiedzy priorytetami robimy wiecej watkow
        for (int i = 0; i<5 ; i++){
            Thread toStart = new Thread(firstJob);
            toStart.setPriority(Thread.MIN_PRIORITY);
            toStart.start();
        }
        for (int i = 0; i<5 ; i++){
            Thread toStart = new Thread(secondJob);
            toStart.setPriority(Thread.MAX_PRIORITY);
            toStart.start();
        }

    }
}
