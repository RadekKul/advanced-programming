package pl.rkul.advanced_programming.increment_example.example2;

public class Holder {

    private volatile int i;     // volatile to zapewnienie ze java nie bedzie optymalizowac wartosci 'i' - za kazdym razem przy skorzystaniu z tego i , bedzie odczytana jego wartosc w tym momencie

    public Holder(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void incrementI(){
        i++;
    }
}
