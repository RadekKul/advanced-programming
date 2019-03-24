package pl.rkul.advanced_programming.files;

import pl.rkul.advanced_programming.files.serializations.AnimalDeserializer;
import pl.rkul.advanced_programming.files.serializations.to_file.AnimalFileDeserializer;
import pl.rkul.advanced_programming.files.serializations.to_file.AnimalFileSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesExample {

    //TODO
    // stworz plik testowy
    // zapisz 5 zwierząt
    // format linii: imie liczba-nog licba-wlosow czy-zyje
    // odczytac wszystkie zwierzeta - uzyc Scanner'a dla linii
    // klasy do serializacji i deserializacji

    public static void main(String[] args) throws FileNotFoundException {

        String path = "D:\\programowanie\\Animals.txt";

        AnimalFileSerializer animalFileSerializer = new AnimalFileSerializer(path);
        AnimalFileDeserializer animalFileDeserializer = new AnimalFileDeserializer(path);

        File animalsFile = new File(path);

        List<Animal> animalsList = Arrays.asList(
        new Animal("Azor",4,1500000,true),
        new Animal("Burek",4,300000,false),
        new Animal("Bobek",4,3000,true),
        new Animal("Bimbek",2,3,false)
        );

        // Moglibysmy tak zrobic w mainie ale zrobimy to w oddzielnej klasie AnimalFileSerializer i tu tylko wywolamy
        /*for(Animal animal: animalsList){
            System.out.println(String.format("%s %s %s %s",animal.getName(),animal.getNumberOfLegs(),animal.getHairs(),animal.isAlive()));
        }*/

        animalFileSerializer.writeAnimals(animalsList);

        List <Animal> animalsList2 = animalFileDeserializer.readAnimals();      // wsyzstko dziala, nawet jak zakomentujemy serializator i wpiszemy cos z reki to to wczyta samo
        animalsList2.forEach(System.out::println);


    }
}
