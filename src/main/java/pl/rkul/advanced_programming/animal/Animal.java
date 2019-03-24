package pl.rkul.advanced_programming.animal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

public abstract class Animal {  // wybieramy klase abstrakcyjna zamiast interfejsu bo bedziemy mieli jakies wspolne pola dla wszystkich zwierzat , a nie tylko metody

    protected BigInteger waterQuantity;         // Big-i oznaczaja klase ktora ma prawie nieograniczona pojemnosc, ale dzialanie nie sa na + - itp tylko dziala sie na metodzie, ale sa duzo dokladniejsze
    protected BigInteger waterUsagePerCycle;    // niz zwykle float i double, dlatego powinno sie ich uzywac BigInteger BigDecimal

    protected BigInteger foodQuantity;
    protected BigInteger foodUsagePerCycle;

    protected boolean isAlive;
    protected BigDecimal weight;    //wymagane
    protected Sex sex;              //wymagane
    protected String name;      // Optional , tylko imie jest opcjonalne, reszte musim byc rozne od nulla

    protected Animal(BigDecimal weight, Sex sex) {
        isAlive = true;
        this.weight = Objects.requireNonNull(weight,
                "Weight cannot be null");
        this.sex = Objects.requireNonNull(sex,
                "Sex cannot be null");
    }

    public void eatAndDrink(BigInteger foodQuantityIncrease,    // metoda ktora bedzie dorzucała nam dana ilosc wody i jedzenia, musimy dodac to za pomoca metody bo operujemy na BigInteger
                            BigInteger waterQuantityIncrease){
        if(isAlive) {
            foodQuantity = foodQuantity.add(foodQuantityIncrease);  // do obecnej wartosci przypisujemy wartosc+to co w metode wpisalismy
            waterQuantity = waterQuantity.add(waterQuantityIncrease);   // musimy tak nadpisywac bo sa IMMUTABLE, i zeby nie tworzyc za kazdym razem nowego obiektu to nadpisujemy stary
        }
    }

    public void consumeCalories(){
        if(isAlive) {
            foodQuantity = foodQuantity.subtract(foodUsagePerCycle);
            waterQuantity = waterQuantity.subtract(waterUsagePerCycle);
        }
    }

    public Optional<String> getName() { // nie musimy robic validatora do czegos co jest opcjonalne, bo sami sprawdzimy czy to nie jest nullem.
        return Optional.ofNullable(name);   // Optional to jest pojemnik na jedna rzecz, albo jest rzecz-name albo null, jesli name to go zwracamy, jesli null to zwracamy EMPTY
    }

    public <T> T validate(T value, String failMessage){ // generyczny validator

        if (null==value){
            throw new NullPointerException(failMessage);
        }
        return value;
    }

    public void setWaterQuantity(BigInteger waterQuantity) {
        this.waterQuantity = validate(waterQuantity,
                "water quantity cannot be null");
    }

    public void setWaterUsagePerCycle(BigInteger waterUsagePerCycle) {
        this.waterUsagePerCycle = validate(waterUsagePerCycle,
                "water usage cannot be null");
    }

    public void setFoodQuantity(BigInteger foodQuantity) {
        this.foodQuantity = validate(foodQuantity,
                "food quantity cannot be null");
    }

    public void setFoodUsagePerCycle(BigInteger foodUsagePerCycle) {
        this.foodUsagePerCycle = validate(foodUsagePerCycle,
                "food usage cannot be null");
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "waterQuantity=" + waterQuantity +
                ", waterUsagePerCycle=" + waterUsagePerCycle +
                ", foodQuantity=" + foodQuantity +
                ", foodUsagePerCycle=" + foodUsagePerCycle +
                ", isAlive=" + isAlive +
                ", weight=" + weight +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }
}
