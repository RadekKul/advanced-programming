package pl.rkul.advanced_programming.increment_example.example2;

public class Increment implements Runnable {

   private Holder holder;

    public Increment(Holder holder) {
        this.holder = holder;
    }

    @Override
    public void run() {
        while (true){
                holder.incrementI();
        }

    }
}
