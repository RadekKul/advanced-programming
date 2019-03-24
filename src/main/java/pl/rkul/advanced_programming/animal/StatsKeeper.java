package pl.rkul.advanced_programming.animal;

import java.util.List;
import java.util.Objects;

public class StatsKeeper implements Runnable {

    private final List<Animal> animals;
    private final long timeIntervalInSec;

    private static final String messagePattern =
            "\tNumber of all animals: [%s]\n" +
            "\tNumber of dead ones: [%d]\n" +
            "\tNumber of alive ones: [%d]\n";

    @SuppressWarnings("Unchecked")
    public StatsKeeper(List<? extends Animal> animals, long timeIntervalInSec) {
        this.animals = (List<Animal>) Objects.requireNonNull(animals);
        this.timeIntervalInSec = timeIntervalInSec;
    }

    @Override
    public void run() {

        while (animals.stream().anyMatch(animal -> animal.isAlive)) {
            long aliveAnimals = animals     // obliczamy sobie ile jest zywych
                    .stream()
                    .filter(Animal::isAlive)    // wywola na kazdym obiekcie isAlive, czyli sprawdzi czy jego isAlive jest true czy false
                    .count();

            long deadAnimals = animals.size() - aliveAnimals; // obliczamy sobie ile jest martwych

            System.out.println(String.format(messagePattern, animals.size(), deadAnimals, aliveAnimals));

            animals.forEach(System.out::println);

            try {
                Thread.sleep(Utils.convertSecToMiliSec(timeIntervalInSec));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
