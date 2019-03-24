package pl.rkul.advanced_programming.increment_example.thread_interupting;

public class StopThread implements Runnable {

    private Holder holder;

    public StopThread(Holder holder) {
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            holder.setReadyToStop(true);
            System.out.println("I'm ready to stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
