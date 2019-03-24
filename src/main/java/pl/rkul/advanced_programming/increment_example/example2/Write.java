package pl.rkul.advanced_programming.increment_example.example2;

public class Write implements Runnable {

    private Holder holder;

    public Write(Holder holder) {
        this.holder = holder;
    }

    @Override
    public void run() {

        while(true){
            System.out.println("Current Value of i: " + holder.getI());
        }
    }
}
