package pl.rkul.advanced_programming.animal;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class ZooWorker implements Runnable {    // jako ze bedzie to watek to musi implementowac Runnable interfejs, wiec to jest nasz job!!!

    private static BigInteger foodIncreaseLevel = BigInteger.valueOf(250);  // zakladamy ze pracownicy zawsze dodaja tyle samo jedzenia i picia do kazdego zwierzatka
    private static BigInteger waterIncreaseLevel = BigInteger.valueOf(200);

    private List<Animal> animals;
    private long timeIntervalInSec;
    private String name;

    @SuppressWarnings("unchecked")  // dodajemy to zeby pokazac intellj ze celowo rzutujemy, bo inaczej rzuci tam ostrzezenie a my jestesmy pewni tego co tutaj robimy
    public ZooWorker(List<? extends Animal> animals, long timeIntervalInSec, String name){
        this.animals = (List<Animal>) Objects.requireNonNull(animals);
        this.timeIntervalInSec = timeIntervalInSec;
        this.name = Objects.requireNonNull(name);

    }

    private static void feedAnimal(Animal animal) {
        animal.eatAndDrink(foodIncreaseLevel, waterIncreaseLevel);
    }

    @Override
    public void run(){

        while (animals.stream().anyMatch(animal -> animal.isAlive)) {
            animals.forEach(ZooWorker::feedAnimal);     // aby zrobic to przez programowanie funkcyjne musimy stworzyc metode feedAnimal, i wtedy mozna ją wywolac na zooWorkerze i ladniej to wyglada
                                                        // ta metoda musi byc statyczna bo odwoluje sie obiektu z tej klasy, mozna tez wpisac zamiast ZooWorker this, i tez bedzie dzialac jako niestatyczne
            try {
                Thread.sleep(Utils.convertSecToMiliSec(timeIntervalInSec));   // tu musimy pomnozyc *1000 bo ma co 150 sec to robic wiec z milisec do sec trzeba przejsc, ale zrobilismy oddzielna
            } catch (InterruptedException e) {                                 // metode pomocznicza zeby sie nie powtarzac
                e.printStackTrace();
            }
        }
    }


}
