package pl.rkul.advanced_programming.files.serializations;

import pl.rkul.advanced_programming.files.Animal;

import java.util.List;

public interface AnimalDeserializer {

    List<Animal> readAnimals();
}
