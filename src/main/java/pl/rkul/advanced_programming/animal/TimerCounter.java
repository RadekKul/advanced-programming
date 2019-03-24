package pl.rkul.advanced_programming.animal;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class TimerCounter implements Runnable {

    private final List<Animal> animals;
    private final long timeIntervalInSec;


    @SuppressWarnings("Unchecked")
    public TimerCounter(List<? extends Animal> animals, long timeIntervalInSec){

        this.animals = (List<Animal>) Objects.requireNonNull(animals);
        this.timeIntervalInSec = timeIntervalInSec;
    }


    @Override
    public void run() {


        while (animals.stream().anyMatch(animal -> animal.isAlive)) {
            animals.forEach(Animal::consumeCalories);

            animals.stream()
                    .filter(animal -> ((animal.waterQuantity.intValue() < 0 || animal.foodQuantity.intValue() < 0)))
                    .forEach(animal -> animal.setAlive(false));

            try {
                Thread.sleep(Utils.convertSecToMiliSec(timeIntervalInSec));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
