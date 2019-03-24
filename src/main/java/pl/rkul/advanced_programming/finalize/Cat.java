package pl.rkul.advanced_programming.finalize;

public class Cat {

    private String name;
    private String color;

    public Cat(String name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
        System.out.println(this);   // wypisujemy sam obiekt
    }
}
