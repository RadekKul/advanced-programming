package pl.rkul.advanced_programming.increment_example.thread_interupting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class GogoThread implements Runnable{

    private Holder holder;


    public GogoThread(Holder holder) {
        this.holder = holder;
    }

    @Override
    public void run() {
        while (!holder.isReadyToStop){
            try {
                Thread.sleep(500);  // co pol sekundy wypisuje date
                System.out.println(LocalDateTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
