package pl.rkul.advanced_programming.files;

public class Animal {

    private String name;
    private int numberOfLegs;
    private long hairs;
    private boolean isAlive;

    public Animal(String name, int numberOfLegs, long hairs, boolean isAlive) {
        this.name = name;
        this.numberOfLegs = numberOfLegs;
        this.hairs = hairs;
        this.isAlive = isAlive;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", numberOfLegs=" + numberOfLegs +
                ", hairs=" + hairs +
                ", isAlive=" + isAlive +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public long getHairs() {
        return hairs;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
